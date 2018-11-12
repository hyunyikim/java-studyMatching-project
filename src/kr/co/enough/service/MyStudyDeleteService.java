package kr.co.enough.service;


import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.enough.action.Action;
import kr.co.enough.action.ActionForward;

import kr.co.enough.dao.MyPageDao;

import kr.co.enough.dto.MyStudyDto;
import kr.co.enough.dto.MyWishDto;

public class MyStudyDeleteService implements Action{

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
      ActionForward forward = null;
      try {
             
           request.setCharacterEncoding("UTF-8");
           response.setContentType("text/html; charset=UTF-8");      
           HttpSession session = request.getSession();
           String email = (String)session.getAttribute("email");
           int study_num = Integer.parseInt(request.getParameter("study_num"));
           MyPageDao dao = new MyPageDao();
           dao.deleteMyStudyList(study_num);
           forward = new ActionForward();
           forward.setRedirect(false);
           forward.setPath("/myStudy.mypage");
           System.out.println("위시");
           
      }catch(Exception e) {
         e.printStackTrace();
      }
      return forward;
   }
}

           