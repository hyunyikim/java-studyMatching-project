package kr.co.enough.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.enough.action.Action;
import kr.co.enough.action.ActionForward;
import kr.co.enough.dao.CommentsDao;
import kr.co.enough.dao.MentoDao;
import kr.co.enough.dto.CommentsDto;
import kr.co.enough.dto.MentoDto;

public class MentoDetailService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		ActionForward foward = null;
		try {
			String mtemail = request.getParameter("mtemail");
			MentoDao dao = new MentoDao();
			MentoDto mentodetail = new MentoDto();
			mentodetail = dao.getMentoDetailService(mtemail);
			System.out.println("mento Detail : dao 갔다가 service로 왔다 ");
			request.setAttribute("mentodetail", mentodetail);

			CommentsDao commentdao = new CommentsDao();
			ArrayList<CommentsDto> commentList = commentdao.getCommentsList(mtemail);
			request.setAttribute("commentList", commentList);

			foward = new ActionForward();
			foward.setRedirect(false);
			foward.setPath("/view/mento/mentoDetail.jsp");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return foward;
	}

}
