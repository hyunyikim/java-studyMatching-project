<%@page import="net.sf.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, 
		javax.sql.*, 
		java.io.*, 
		javax.naming.InitialContext, 
		javax.naming.Context,
		java.sql.PreparedStatement,
		java.sql.ResultSet,
		java.util.ArrayList"%>
<%@page import="org.json.JSONObject"%>
<%@page import="net.sf.json.JSONArray"%>
<%@ page import="kr.co.enough.dto.StudyDto"%>
<% 
		String SUBJECT_CODE = request.getParameter("SUBJECT_CODE");    

		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<StudyDto> studyList=new ArrayList<StudyDto>();
		JSONArray jArray = new JSONArray();
		try {
			InitialContext initCtx = new InitialContext();
			DataSource ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/oracle"); 
			Connection conn = ds.getConnection(); 
			
			String sql = "select s.study_num, s.mento_email, s.study_name, s.study_write, s.start_date, s.end_date, s.price, s.study_img, sub.subject_name, lev.level_name, loc.loc_name, m.member_img" 
					+" from study s join study_subject_code sub on s.study_subject_code = sub.subject_code "
					+" join study_level_code lev on s.study_level_code = lev.level_code" 
					+" join study_loc_code loc on s.study_loc_code = loc.loc_code" 
					+" join member m on s.mento_email = m.email"
					+" where sub.SUBJECT_CODE=?"
					+" ORDER by s.start_date DESC, s.study_num DESC";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, SUBJECT_CODE);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				 StudyDto study = new StudyDto();
				study.setStudy_num(rs.getInt("study_num"));
				study.setStudy_name(rs.getString("study_name"));
				study.setStudy_write(rs.getString("study_write"));
				study.setMember_img(rs.getString("member_img"));
				study.setStudy_img(rs.getString("study_img"));
				
				studyList.add(study); 

				
			}
			for(StudyDto s:studyList){
				s.toString();
				System.out.println("aa"+s.toString());
			}
			jArray = JSONArray.fromObject(studyList);
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch(Exception e){
			out.println("<h3>연결 실패</h3>");
			out.println(e.getMessage());
		}
		%>
		<%=jArray%>