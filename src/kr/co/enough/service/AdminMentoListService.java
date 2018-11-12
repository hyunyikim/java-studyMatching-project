package kr.co.enough.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.enough.action.Action;
import kr.co.enough.action.ActionForward;
import kr.co.enough.dao.AdminDao;
import kr.co.enough.dao.LoginDao;
import kr.co.enough.dao.MyPageDao;
import kr.co.enough.dto.AdminMentoDto;
import kr.co.enough.dto.LoginDto;
import kr.co.enough.dto.MyStudyDto;
import kr.co.enough.dto.StudyDto;

public class AdminMentoListService implements Action{

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
      ActionForward forward = null;
      try {
             
           request.setCharacterEncoding("UTF-8");
           response.setContentType("text/html; charset=UTF-8");      
           HttpSession session = request.getSession();
           String email = (String)session.getAttribute("email");
           AdminDao dao = new AdminDao();
           String result = dao.getMentoTF();
           ArrayList<AdminMentoDto>MentoReadyList = dao.getAdminReadyMentoList();
           	System.out.println("<<<개졸려");
           	String msg = (String)request.getAttribute("msg");
           	System.out.println(msg+"되나");
           	request.setAttribute("result", result);
           	request.setAttribute("msg", msg);
           	request.setAttribute("MentoReadyList", MentoReadyList); 
             forward = new ActionForward();
             forward.setRedirect(false);
             forward.setPath("/view/mypage/admin/mentoList.jsp"); 
           }catch(Exception e) {
         e.printStackTrace();
      }
      return forward;
   }
}

           