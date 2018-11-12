package kr.co.enough.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.enough.action.Action;
import kr.co.enough.action.ActionForward;
import kr.co.enough.dao.StudyDao;
import kr.co.enough.dto.StudyDto;

public class StudyDetailService implements Action {

	private Object study_num;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward foward = null;
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=utf-8");

			StudyDao dao = new StudyDao();
			StudyDto study = new StudyDto();
			int study_num = Integer.parseInt(request.getParameter("study_num"));
			System.out.println(study_num);
			study = dao.StudyDetail(study_num);
			request.setAttribute("study", study);
			System.out.println("dao에 갔다 왔다, study 를 뷰단에 뿌려준다");

			String nextPage = request.getParameter("nextPage");
			System.out.println(nextPage);

			if (nextPage.equals("studyApply")) {
				System.out.println("studyApply로 경로 이동 ");
				foward = new ActionForward();
				foward.setRedirect(false);
				foward.setPath("/view/category/studyApply.jsp");
			} else if(nextPage.equals("studyDetail")){
				foward = new ActionForward();
				foward.setRedirect(false);
				foward.setPath("/view/category/studyDetail.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return foward;
	}
}
