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


public class AdminMemberDeleteService implements Action{

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
      ActionForward forward = null;
      try {
             
           request.setCharacterEncoding("UTF-8");
           response.setContentType("text/html; charset=UTF-8");      
           /*HttpSession session = request.getSession();
           String email = (String)session.getAttribute("email");*/
           String email = request.getParameter("email");
           String msg = "";
           System.out.println(email+"11");
           AdminDao dao = new AdminDao();
           System.out.println("<<<개졸려");
           int result = dao.getAdminMeberCheck(email);
           if(result > 0) {
        	   msg = "멘토목록을 확인해주세요.";
        	   request.setAttribute("msg", msg);
           }else {
           dao.getAdminMemberNoList(email);	
           }
           forward = new ActionForward();
           forward.setRedirect(false);
           forward.setPath("/adminMemberList.admin"); 
           }catch(Exception e) {
         e.printStackTrace();
      }
      return forward;
   }
}

           