package kr.co.enough.service;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.enough.action.Action;
import kr.co.enough.action.ActionForward;
import kr.co.enough.dao.JoinDao;
import kr.co.enough.dao.LoginDao;
import kr.co.enough.dao.MyPageDao;
import kr.co.enough.dto.LoginDto;

public class AdminProfileService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		try {
			 
			  request.setCharacterEncoding("UTF-8");
			  response.setContentType("text/html; charset=UTF-8");		
			  HttpSession session = request.getSession();
			  String email = (String)session.getAttribute("email");
			  String name = (String)session.getAttribute("name");
			  MyPageDao dao = new MyPageDao();
			  ArrayList<LoginDto>profileList = dao.getProfileList(email);
			  System.out.println("22222222222222222222");
			  System.out.println(profileList.get(0).getHp());
			  String hp = profileList.get(0).getHp();
			  request.setAttribute("hp", hp);
			  forward = new ActionForward();
			  forward.setRedirect(false);
			  forward.setPath("/view/mypage/admin/adminpage.jsp");
			  
		}catch(Exception e) {
			e.printStackTrace();
		}
		return forward;
	}
}

			  