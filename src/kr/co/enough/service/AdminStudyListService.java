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
import kr.co.enough.dto.LoginDto;
import kr.co.enough.dto.MyStudyDto;
import kr.co.enough.dto.StudyDto;

public class AdminStudyListService implements Action{

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
      ActionForward forward = null;
      try {
             
           request.setCharacterEncoding("UTF-8");
           response.setContentType("text/html; charset=UTF-8");      
           HttpSession session = request.getSession();
           String email = (String)session.getAttribute("email");
           AdminDao dao = new AdminDao();
           String result = dao.getAdminStudyTF();
           ArrayList<MyStudyDto>StudyList = dao.getAdminStudyList();
           	System.out.println("졸려");
             request.setAttribute("StudyList", StudyList); 
             request.setAttribute("result", result);
             forward = new ActionForward();
             forward.setRedirect(false);
             forward.setPath("/view/mypage/admin/studyList.jsp"); 
           }catch(Exception e) {
         e.printStackTrace();
      }
      return forward;
   }
}

           