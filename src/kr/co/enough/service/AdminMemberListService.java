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
import kr.co.enough.dto.MemberDto;
import kr.co.enough.dto.MyStudyDto;
import kr.co.enough.dto.StudyDto;

public class AdminMemberListService implements Action{

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
      ActionForward forward = null;
      try {
             
           request.setCharacterEncoding("UTF-8");
           response.setContentType("text/html; charset=UTF-8");      
           HttpSession session = request.getSession();
           String email = (String)session.getAttribute("email");
           AdminDao dao = new AdminDao();
           String msg = (String)request.getAttribute("msg");
           System.out.println(msg);
           request.setAttribute("msg", msg);
           ArrayList<MemberDto>MemberList = dao.getAdminMemberList();
           	System.out.println("미친ㅁ 너무졸려 미친개졸려");
             request.setAttribute("MemberList", MemberList); 
             forward = new ActionForward();
             forward.setRedirect(false);
             forward.setPath("/view/mypage/admin/memberList.jsp"); 
           }catch(Exception e) {
         e.printStackTrace();
      }
      return forward;
   }
}

           