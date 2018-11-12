package kr.co.enough.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.enough.action.Action;
import kr.co.enough.action.ActionForward;
import kr.co.enough.dao.PayDao;


public class PaymentAddService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		ActionForward foward = null;
		
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			HttpSession session = request.getSession();
			
			int num = Integer.parseInt(request.getParameter("study_num"));
			String email = (String) session.getAttribute("email");
			int price = Integer.parseInt(request.getParameter("price"));						
			
			PayDao dao = new PayDao();
			int resultRow = dao.payInsert(num, email, price);
			
			
			if(resultRow > 0) {
				System.out.println("결제 insert 성공 : "+resultRow);
				int studyResultRow = dao.studyMemberInsert(num, email);
				System.out.println("스터디 멤버 resultRow : "+studyResultRow);
				
				if(studyResultRow > 0) {
					System.out.println("스터디멤버 insert 성공");
					
				} else {
					System.out.println("스터디멤버 insert 실패");
				}
			} else {
				System.out.println("결제 insert 실패");
			}
	
			foward = new ActionForward();
			foward.setRedirect(false);
			foward.setPath("/myStudy.mypage");   
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return foward;
	}
	
	

}
