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

public class MyWishService implements Action{

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
      ActionForward forward = null;
      try {
             
           request.setCharacterEncoding("UTF-8");
           response.setContentType("text/html; charset=UTF-8");      
           HttpSession session = request.getSession();
           String email = (String)session.getAttribute("email");
           MyPageDao dao = new MyPageDao();
           String result = dao.getWishTF(email);
           if(result == "false") {
              System.out.println("false");
              request.setAttribute("result", result);
              forward = new ActionForward();
              forward.setRedirect(false);
              forward.setPath("/view/mypage/member/wishList.jsp");
           }else {
           ArrayList<MyWishDto>myWishList = dao.getMyWishList(email);
           request.setAttribute("myWishList", myWishList);
           request.setAttribute("result", result);
           forward = new ActionForward();
           forward.setRedirect(false);
           forward.setPath("/view/mypage/member/wishList.jsp");
           System.out.println("위시");
           }
      }catch(Exception e) {
         e.printStackTrace();
      }
      return forward;
   }
}

           