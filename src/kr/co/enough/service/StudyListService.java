package kr.co.enough.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.enough.action.Action;
import kr.co.enough.action.ActionForward;
import kr.co.enough.dao.StudyDao;
import kr.co.enough.dto.StudyDto;


public class StudyListService implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		try {
			  	request.setCharacterEncoding("UTF-8");
			  	response.setContentType("text/html; charset=UTF-8");
			  	String study_subject_code = request.getParameter("STUDY_SUBJECT_CODE");
			  	System.out.println("service에서의 과목 코드 : "+study_subject_code);
			   StudyDao dao = new StudyDao();
       	       ArrayList<StudyDto> studyList = dao.getStudyList(study_subject_code);
       	       System.out.println(studyList);
       	       request.setAttribute("studyList", studyList);
       	       request.setAttribute("study_subject_code", study_subject_code);
       	       
       	       forward = new ActionForward();
       	       forward.setRedirect(false);
       	       forward.setPath("/view/category/studyList.jsp");   
       	       System.out.println("forward로 받았다") ;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return forward;
	}
}
