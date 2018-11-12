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


public class AdminMentoDeleteService implements Action{

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
      ActionForward forward = null;
      try {
             
           request.setCharacterEncoding("UTF-8");
           response.setContentType("text/html; charset=UTF-8");      
           /*HttpSession session = request.getSession();
           String email = (String)session.getAttribute("email");*/
           String mento_email = request.getParameter("mento_email");
           String msg = "";
           System.out.println(mento_email+"11");
           AdminDao dao = new AdminDao();
           int result = dao.getAdminStudyMentoList(mento_email);
           if(result == 1) {
        	   msg = "스터디목록을 먼저 삭제하세요.";
        	   request.setAttribute("msg", msg);
        	   forward = new ActionForward();
        	   forward.setRedirect(false);
        	   forward.setPath("/adminMentoList.admin");
           }else {
        		System.out.println("<<<개졸려");
        	dao.getAdminMentoNoList(mento_email);	
        	 forward = new ActionForward();
             forward.setRedirect(false);
             forward.setPath("/adminMentoList.admin"); 
           }
           }catch(Exception e) {
         e.printStackTrace();
      }
      return forward;
   }
}

           