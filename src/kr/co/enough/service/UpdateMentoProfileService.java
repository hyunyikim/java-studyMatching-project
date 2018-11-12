package kr.co.enough.service;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.enough.action.Action;
import kr.co.enough.action.ActionForward;
import kr.co.enough.dao.MyPageDao;
import kr.co.enough.dto.LoginDto;


public class UpdateMentoProfileService implements Action{

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
      ActionForward foward = null;
      String path = request.getRealPath("/img/profie");
      int size = 10 * 1024 * 1024; 
      String fileName=""; // 실제파일이름
      String oriFileName = ""; // 업로드시 변경되지 않는 이름
      HttpSession session = request.getSession();
      
      LoginDto dto = new LoginDto();
      String msg="";
      try {
         MultipartRequest multi = new MultipartRequest(request,path,size,"utf-8",new DefaultFileRenamePolicy());
          //MultipartRequest객체 생성 (request객체 지정, 위치, 크기, 언어, 파일이름 중복되지 않게 자동으로 이름 변경)
         String name = (String)multi.getParameter("name");
         String hp = (String)multi.getParameter("hp");
         String member_img = (String)multi.getFilesystemName("member_img");
         System.out.println("member_img: "+member_img);
         String email = (String)session.getAttribute("email");
         if(member_img == null) {
            member_img = (String)session.getAttribute("member_img");
         }
         System.out.println("zzzzzzzzzzzzzzzzz");
           if(hp.length()==11) {
               hp = new StringBuilder(hp).insert(hp.length()-4, "-").toString();
               hp = new StringBuilder(hp).insert(hp.length()-9, "-").toString();
              }else if(hp.length()==10) {
               hp = new StringBuilder(hp).insert(hp.length()-4, "-").toString();
               hp = new StringBuilder(hp).insert(hp.length()-8, "-").toString();
              }
           dto.setName(name);
           dto.setHp(hp);
           dto.setMember_img(member_img);
           dto.setEmail(email);
           MyPageDao dao = new MyPageDao();
           int profileList = dao.updateProfileList(dto);
           if(profileList > 0) {
              msg="success";
           }else {
              msg="fail";
           }
           
           Enumeration files = multi.getFileNames();
            //폼에서 전송되어온 파일 타입의 입력상자의 이름을 반환
           String str = (String)files.nextElement(); //입력 상자의 이름을 변수에 저장
           fileName=multi.getFilesystemName(str); //fileName
           oriFileName = multi.getOriginalFileName(fileName);//oriFileName
           
           session.setAttribute("member_img", member_img);
           session.setAttribute("name", name);
           String test = (String)session.getAttribute("name");
           //System.out.println("<<<<<" + test);
           request.setAttribute("hp",hp);
           
           foward = new ActionForward();
           foward.setRedirect(false);
           foward.setPath("/view/mypage/mento/mentopage.jsp");
         } catch(Exception e) {
        	 e.printStackTrace();
      }
      return foward;
   }
}


           