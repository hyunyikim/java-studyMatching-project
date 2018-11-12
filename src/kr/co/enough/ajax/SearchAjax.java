package kr.co.enough.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.enough.dao.SearchDao;

/**
 * Servlet implementation class SearchAjax
 */
@WebServlet("/search")
public class SearchAjax extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    public SearchAjax() {
        super();
    }
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doPost(request,response);
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("UTF-8");
      response.setContentType("text/html; charset=UTF-8");
      String value = request.getParameter("value");
      System.out.println(value);
      PrintWriter out = response.getWriter();
      try {
      SearchDao dao = new SearchDao();
      String result = dao.search(value);
      out.println(result);
      }catch(Exception e) {
         e.printStackTrace();
      }
      
   
   }

}