package kr.co.enough.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import org.json.JSONObject;

import kr.co.enough.dao.CommentsDao;
import kr.co.enough.dto.CommentsDto;

@WebServlet("/CommentsDelete")
public class CommentDeleteAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CommentDeleteAjax() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			
			int comments_num = Integer.parseInt(request.getParameter("comments_num"));
			System.out.println("서비스에서 comments_num을 찍어봤다  : "+comments_num);
			
			CommentsDao dao = new CommentsDao();
			int resultRow = dao.commentsDelete(comments_num);
			System.out.println("비동기로 delete 성공했다. " + resultRow);
			out.print(resultRow);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
