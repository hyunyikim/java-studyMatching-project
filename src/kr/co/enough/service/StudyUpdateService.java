package kr.co.enough.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.enough.action.Action;
import kr.co.enough.action.ActionForward;
import kr.co.enough.dao.StudyDao;
import kr.co.enough.dto.MentoStudyDto;

public class StudyUpdateService implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("study Add 서비스로 들어왔다.");
		
		ActionForward foward = null;
		String path = request.getRealPath("/img/studyImg");
		System.out.println("리얼 패스 주소  : "+path);
		int size = 10 * 1024 * 1024;
		String fileName = ""; // 실제파일이름
		String oriFileName = ""; // 업로드시 변경되지 않는 이름
		HttpSession session = request.getSession();

		try {
			MultipartRequest multi = new MultipartRequest(request, path, size, "utf-8", new DefaultFileRenamePolicy());
			request.setCharacterEncoding("UTF-8");
			// MultipartRequest객체 생성 (request객체 지정, 위치, 크기, 언어, 파일이름 중복되지 않게 자동으로 이름 변경)
			
			String mento_email = (String) session.getAttribute("email");
			
			int study_num = Integer.parseInt(multi.getParameter("study_num"));
			String study_name = (String) multi.getParameter("study_name");
			String study_write = (String) multi.getParameter("study_write");
			Date start_date = Date.valueOf(multi.getParameter("start_date"));
			Date end_date = Date.valueOf(multi.getParameter("end_date"));
			int price = Integer.parseInt(multi.getParameter("price"));
			String study_subject_code = (String) multi.getParameter("study_subject_code");
			String study_level_code = (String) multi.getParameter("study_level_code");
			String study_loc_code = (String) multi.getParameter("study_loc_code");
			String study_img = (String)multi.getFilesystemName("study_img");
			
			StudyDao dao = new StudyDao();
			int result = dao.UpdateStudy(study_name, study_write, start_date, end_date, price,
					study_subject_code, study_level_code, study_loc_code, study_img, study_num);
			
			if (result > 0) {
				System.out.println("study Update 성공");
			} else {
				System.out.println("study Update 실패");
			}
			
			ArrayList<MentoStudyDto> myStudyList = new ArrayList<MentoStudyDto>();
			myStudyList = dao.getMentoStudyList(mento_email);
			
			if (myStudyList.isEmpty()) {
				System.out.println("myStudyList arrayList 에 값이 없다  : false");
				request.setAttribute("result", "false");
			} else {
				System.out.println("myStudyList arrayList 에 값이  있다  : true");
				request.setAttribute("result", "true");
				request.setAttribute("myStudyList", myStudyList);
			}
			
			Enumeration files = multi.getFileNames();
			// 폼에서 전송되어온 파일 타입의 입력상자의 이름을 반환
			String str = (String) files.nextElement(); // 입력 상자의 이름을 변수에 저장
			fileName = multi.getFilesystemName(str); // fileName
			oriFileName = multi.getOriginalFileName(fileName);// oriFileName

			foward = new ActionForward();
			foward.setRedirect(false);
			foward.setPath("/view/mypage/mento/myStudy.jsp"); 

		} catch (Exception e) {
			e.printStackTrace();
		}
		return foward;
	}
}
