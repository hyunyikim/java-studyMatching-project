package kr.co.enough.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.enough.action.Action;
import kr.co.enough.action.ActionForward;
import kr.co.enough.dao.JoinDao;
import kr.co.enough.dao.LoginDao;

public class EmailCheckService implements Action{
	@Override
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		String emailCheck = null;
		String email = request.getParameter("email");
		try {
			  JoinDao dao = new JoinDao();
			  emailCheck =dao.emailCheck(email);
			  System.out.println(emailCheck);
			  request.setAttribute("message",emailCheck);
			
		}catch(Exception e) {
			System.out.println("ID 검증 오류 :" + e.getMessage());
		}
		
		forward  = new ActionForward();
		forward.setRedirect(false); //forward방식
		forward.setPath("/view/emailCheck.jsp");
		return 	forward;
	}
}
