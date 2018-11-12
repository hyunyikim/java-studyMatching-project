package kr.co.enough.service;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.enough.action.Action;
import kr.co.enough.action.ActionForward;
import kr.co.enough.dao.LoginDao;
import kr.co.enough.dto.LoginDto;

public class LoginService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward foward = null;
		try {
			 
			  request.setCharacterEncoding("UTF-8");
			  response.setCharacterEncoding("UTF-8"); 
			  response.setContentType("text/html; charset=UTF-8");

			  String email = request.getParameter("email");
			  String pwd = request.getParameter("pwd");
			  System.out.println(email);
			  
			   LoginDao dao = new LoginDao();
			   String loginTF = dao.getloginTF(email, pwd);
			   if(loginTF=="true") {
			   ArrayList<LoginDto>loginlist = dao.getloginList(email, pwd);
			   String name = loginlist.get(0).getName();
			   String member_img = loginlist.get(0).getMember_img();
			   String member_grade_code = loginlist.get(0).getMember_grade_code();
			   HttpSession session = request.getSession();
			   session.setAttribute("email", email);
			   session.setAttribute("name", name);
			   session.setAttribute("member_img", member_img);
			   session.setAttribute("member_grade_code", member_grade_code);
			   foward = new ActionForward();
			   foward.setRedirect(false);
			   foward.setPath("/view/main.jsp");
			   }else {
				   System.out.println("zzzzzzzzzzzzzzzzzzzz");
				   PrintWriter out = response.getWriter();
				   out.println("<script>");
				   out.println("alert('아이디 혹은 비밀번호가 다릅니다.');");
				   out.println("history.back()");
				   out.println("</script>");
				   out.close();
				   System.out.println("zzzzzzzzzzzzzzzzzzzz");
				
			   }
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return foward;
	}
}
