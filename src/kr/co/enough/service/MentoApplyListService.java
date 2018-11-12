package kr.co.enough.service;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.enough.action.Action;
import kr.co.enough.action.ActionForward;
import kr.co.enough.dao.LoginDao;
import kr.co.enough.dao.MentoDao;
import kr.co.enough.dto.LoginDto;

public class MentoApplyListService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward foward = null;
		try {
			 
			  request.setCharacterEncoding("UTF-8");
			  response.setCharacterEncoding("UTF-8"); 
			  
			  String career = request.getParameter("mentoCareer");
			  System.out.println(career);
			  String msg="";
			   MentoDao dao = new MentoDao();
			   HttpSession session = request.getSession();
			   String email = (String)session.getAttribute("email");
			   String staus_code = "MS01";
			   System.out.println(email);
			   System.out.println(career);
			   System.out.println(staus_code);
			   
			   int result = dao.insertCareerList(email,career,staus_code);
			   if(result > 0) {
				   msg = "멘토신청완료";
			   }else {
				   msg = "멘토신청실패";
			   }
			   request.setAttribute("msg", msg);
			   foward = new ActionForward();
			   foward.setRedirect(false);
			   foward.setPath("/view/alert2.jsp");
			   }catch (Exception e) {
			e.printStackTrace();
		}
		
		return foward;
	}
}
