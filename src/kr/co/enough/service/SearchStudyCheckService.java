package kr.co.enough.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.enough.action.Action;
import kr.co.enough.action.ActionForward;
import kr.co.enough.dao.StudyDao;
import kr.co.enough.dto.StudyDto;

public class SearchStudyCheckService implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		String[] loc;
		String[] level;
		
		ArrayList<StudyDto> studyList;
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
		  	String study_subject_code = request.getParameter("STUDY_SUBJECT_CODE");
		  	
		  	System.out.println("service에서의 과목 코드 : "+study_subject_code);
			loc = request.getParameterValues("loc");
			level = request.getParameterValues("level");
			StudyDao dao = new StudyDao();
			System.out.println("study 필터 검색 후의 스터디 코드 : "+study_subject_code);
			studyList = dao.SearchStudyCheck(study_subject_code, loc, level);
			
			System.out.println(studyList);
			request.setAttribute("studyList", studyList);
			request.setAttribute("study_subject_code", study_subject_code);
			System.out.println("조건검색한 studyList request에 담았다. ");
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/view/category/studyList.jsp"); 

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("스터디 검색 오류 :" + e.getMessage());
		}

		
		return forward;
	}
}
