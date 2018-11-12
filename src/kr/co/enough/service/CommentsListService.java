package kr.co.enough.service;

import java.util.ArrayList;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.enough.action.Action;
import kr.co.enough.action.ActionForward;
import kr.co.enough.dao.CommentsDao;
import kr.co.enough.dao.MentoDao;
import kr.co.enough.dto.CommentsDto;
import kr.co.enough.dto.MentoDto;

/*ajax로 댓글 리스트만 가지고 멘토 상세 목록에 뿌려준다 */

public class CommentsListService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		ActionForward foward = null;
		try {
			String mtemail = request.getParameter("mtemail");
			
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
