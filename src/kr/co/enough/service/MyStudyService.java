package kr.co.enough.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.enough.action.Action;
import kr.co.enough.action.ActionForward;
import kr.co.enough.dao.LoginDao;
import kr.co.enough.dao.MyPageDao;
import kr.co.enough.dto.LoginDto;
import kr.co.enough.dto.MyStudyDto;

public class MyStudyService implements Action{

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
      ActionForward forward = null;
      try {
             
           request.setCharacterEncoding("UTF-8");
           response.setContentType("text/html; charset=UTF-8");      
           HttpSession session = request.getSession();
           String email = (String)session.getAttribute("email");
           MyPageDao dao = new MyPageDao();
           String result = dao.getStudyTF(email);
           if(result == "false") {
              System.out.println("false");
             request.setAttribute("result", result); 
             forward = new ActionForward();
             forward.setRedirect(false);
             forward.setPath("/view/mypage/member/myStudy.jsp"); 
           }else {
           ArrayList<MyStudyDto>myStudyList = dao.getMyStudyList(email);
           System.out.println("<<<<<<<<<<<<<<" + myStudyList + "<<<<<<<<<<<<<<<");
           System.out.println(myStudyList.get(0).getStudy_name());
           request.setAttribute("myStudyList", myStudyList);
           forward = new ActionForward();
           forward.setRedirect(false);
           forward.setPath("/view/mypage/member/myStudy.jsp");
           }
      }catch(Exception e) {
         e.printStackTrace();
      }
      return forward;
   }
}

           