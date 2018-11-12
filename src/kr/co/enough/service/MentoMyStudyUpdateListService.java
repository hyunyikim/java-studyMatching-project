package kr.co.enough.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.enough.action.Action;
import kr.co.enough.action.ActionForward;
import kr.co.enough.dao.StudyDao;
import kr.co.enough.dto.MentoStudyDto;
import kr.co.enough.dto.MyStudyDto;

public class MentoMyStudyUpdateListService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		try {
			System.out.println("스터디 수정 서비스 들어왔음");
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			HttpSession session = request.getSession();
			String mento_email = (String) session.getAttribute("email");
			int study_num = Integer.parseInt(request.getParameter("study_num"));
			
			StudyDao dao = new StudyDao();
			MentoStudyDto study = new MentoStudyDto();
			study = dao.getMentoUpdateList(mento_email, study_num);
			request.setAttribute("study", study);
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/view/category/studyUpdate.jsp");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}
}
