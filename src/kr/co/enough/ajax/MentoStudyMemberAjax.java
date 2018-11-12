package kr.co.enough.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.enough.dao.StudyDao;
import kr.co.enough.dto.CommentsDto;
import kr.co.enough.dto.StudyMemberDto;
import net.sf.json.JSONArray;

@WebServlet("/MentoStudyMember")
public class MentoStudyMemberAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MentoStudyMemberAjax() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		int study_num = Integer.parseInt(request.getParameter("study_num"));
		String result = "";
		
		try {
			StudyDao dao = new StudyDao();
			ArrayList<StudyMemberDto> memberList = new ArrayList<StudyMemberDto>();
			memberList = dao.getMentoStudyMember(study_num);
			
			if(memberList.isEmpty()) {
				out.print("false");
			} else {
				JSONArray  jsonArr = JSONArray.fromObject(memberList);
				out.print(jsonArr);
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
