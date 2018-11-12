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

public class StudyDeleteService implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward foward = null;
		HttpSession session = request.getSession();
		String mento_email = (String) session.getAttribute("email");

		try {
			int study_num = Integer.parseInt(request.getParameter("study_num"));
			
			StudyDao dao = new StudyDao();
			int result = dao.DeleteStudy(study_num);
			
			if (result > 0) {
				System.out.println("study Delete 성공");
			} else {
				System.out.println("study Delete 실패");
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

			foward = new ActionForward();
			foward.setRedirect(false);
			foward.setPath("/view/mypage/mento/myStudy.jsp"); 

		} catch (Exception e) {
			e.printStackTrace();
		}
		return foward;
	}
}
