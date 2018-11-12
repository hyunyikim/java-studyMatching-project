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

import kr.co.enough.dao.WishListDao;
import kr.co.enough.dto.CommentsDto;
import kr.co.enough.dto.WishListDto;
import net.sf.json.JSONArray;

@WebServlet("/WishList")
public class WishListAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public WishListAjax() {
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
		//System.out.println(mem_email+" / "+study_num);
		
		try {
			WishListDao dao = new WishListDao();
			ArrayList<WishListDto> wishList = new ArrayList<WishListDto>();			
			wishList = dao.getWishList(mem_email, study_num);
			
			if(wishList.isEmpty()) {
				out.println(false);
			} else {
				out.println(true);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
