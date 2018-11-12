package kr.co.enough.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.enough.action.Action;
import kr.co.enough.action.ActionForward;
import kr.co.enough.dao.JoinDao;
import kr.co.enough.dao.LoginDao;
import kr.co.enough.dao.MyPageDao;

public class WishDeleteService implements Action{
	@Override
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");
		int study_number = Integer.parseInt(request.getParameter("study_num"));
		System.out.println("<<<<<<<" + study_number);
		try {
			MyPageDao dao = new MyPageDao();
			dao.deleteWish(study_number);
		}catch(Exception e) {
			System.out.println("ID 검증 오류 :" + e.getMessage());
		}
		
		forward  = new ActionForward();
		forward.setRedirect(false); //forward방식
		forward.setPath("/myWish.mypage");
		return 	forward;
	}
}
