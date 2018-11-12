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
import kr.co.enough.dao.WishListDao;
import kr.co.enough.dto.CommentsDto;
import kr.co.enough.dto.WishListDto;

@WebServlet("/WishListDelete")
public class WishListDeleteAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public WishListDeleteAjax() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//System.out.println("wishList ajax로 들어왔다.");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String mem_email = (String) session.getAttribute("email");
		int study_num = Integer.parseInt(request.getParameter("study_num"));
		
		try {
			WishListDao dao = new WishListDao();
			ArrayList<WishListDto> wishList = new ArrayList<WishListDto>();			
			int result = dao.wishListDelete(mem_email, study_num);
			
			if(result > 0) {
				out.println("delete success");
			} else {
				out.println("delete fail");
			}
			
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
