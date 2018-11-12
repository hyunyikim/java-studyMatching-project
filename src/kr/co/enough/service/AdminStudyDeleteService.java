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


public class AdminStudyDeleteService implements Action{

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
      ActionForward forward = null;
      try {
             
           request.setCharacterEncoding("UTF-8");
           response.setContentType("text/html; charset=UTF-8");      
           /*HttpSession session = request.getSession();
           String email = (String)session.getAttribute("email");*/
           int study_num = Integer.parseInt(request.getParameter("study_num"));
           
           System.out.println(study_num);
           AdminDao dao = new AdminDao();
           dao.getAdminStudyMemberDeleteList(study_num);
           dao.getAdminPaymentDeleteList(study_num);
           dao.getAdminWishDelete(study_num);
           int result = dao.getAdminStudyDeleteList(study_num);
           
           	System.out.println("<<<개졸려");
             forward = new ActionForward();
             forward.setRedirect(false);
             forward.setPath("/adminStudyList.admin"); 
           }catch(Exception e) {
         e.printStackTrace();
      }
      return forward;
   }
}

           