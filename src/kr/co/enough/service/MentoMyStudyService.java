package kr.co.enough.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.enough.action.Action;
import kr.co.enough.action.ActionForward;
import kr.co.enough.dao.StudyDao;
import kr.co.enough.dto.MentoStudyDto;

public class MentoMyStudyService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			HttpSession session = request.getSession();
			String mento_email = (String) session.getAttribute("email");
			StudyDao dao = new StudyDao();
			ArrayList<MentoStudyDto> myStudyList = new ArrayList<MentoStudyDto>();
			String result = "";

			myStudyList = dao.getMentoStudyList(mento_email);
			if (myStudyList.isEmpty()) {
				//System.out.println("myStudyList arrayList 에 값이 없다  : false");
				request.setAttribute("result", "false");
			} else {
				//System.out.println("myStudyList arrayList 에 값이  있다  : true");
				request.setAttribute("result", "true");
				request.setAttribute("myStudyList", myStudyList);
			}
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/view/mypage/mento/myStudy.jsp");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}
}
