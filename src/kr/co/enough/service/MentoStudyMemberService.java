package kr.co.enough.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.enough.action.Action;
import kr.co.enough.action.ActionForward;
import kr.co.enough.dao.StudyDao;
import kr.co.enough.dto.MentoStudyDto;

public class MentoStudyMemberService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			HttpSession session = request.getSession();
			String mento_email = (String) session.getAttribute("email");
			StudyDao dao = new StudyDao();
			
			ArrayList<MentoStudyDto> studyList = dao.getMentoStudy(mento_email);
			if(studyList.isEmpty()) {
				//System.out.println("멘토가 등록한 스터디가 없다.");
				request.setAttribute("result", "false");
			} else {
				//System.out.println("멘토가 등록한 스터디가 있다.");
				request.setAttribute("result", "true");
				request.setAttribute("studyList", studyList);
			}
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/view/mypage/mento/studyMemberList.jsp");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}
}
