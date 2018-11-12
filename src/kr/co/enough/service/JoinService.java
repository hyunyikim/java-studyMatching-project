package kr.co.enough.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.enough.action.Action;
import kr.co.enough.action.ActionForward;
import kr.co.enough.dao.JoinDao;
import kr.co.enough.dao.LoginDao;


public class JoinService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward foward = null;
		try {
			 
			  request.setCharacterEncoding("UTF-8");
			
			  String email = request.getParameter("email");
			  String pwd = request.getParameter("pwd");
			  String name = request.getParameter("name");
			  String hp = request.getParameter("hp");
			  if(hp.length()==11) {
				hp = new StringBuilder(hp).insert(hp.length()-4, "-").toString();
				hp = new StringBuilder(hp).insert(hp.length()-9, "-").toString();
			  }
			 
			  
			   JoinDao dao = new JoinDao();
			   int result = dao.insertJoin(email,pwd,name,hp);
			   response.setContentType("text/html; charset=UTF-8");
			   PrintWriter out = response.getWriter();
			   System.out.println(result);
			   String msg ="";
			   String url ="";
			   
			      if(result > 0){
					  msg = "가입성공";
					  url ="../form/loginForm.jsp";
					  System.out.println(url);
			      }else{
					  msg = "가입실패";
					  url ="../form/signUpForm.jsp";
				  }
				  request.setAttribute("msg", msg);
				  request.setAttribute("url", url);    
			      
			      
			   foward = new ActionForward();
			   foward.setRedirect(false);
			   foward.setPath("/view/alert.jsp");
			   
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return foward;
	}
	
	
	
	
	
	
	
	
	
}
