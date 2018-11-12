package kr.co.enough.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import org.json.JSONObject;

import kr.co.enough.dao.CommentsDao;
import kr.co.enough.dto.CommentsDto;

@WebServlet("/CommentsList")
public class CommentListAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CommentListAjax() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String mtemail = request.getParameter("mtemail");
		
		try {
			CommentsDao commentdao = new CommentsDao();
			ArrayList<CommentsDto> commentList = commentdao.getCommentsList(mtemail);
			
			JSONArray  jsonArr = JSONArray.fromObject(commentList);
			out.print(jsonArr);

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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
