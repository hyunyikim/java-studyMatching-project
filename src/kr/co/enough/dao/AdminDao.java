package kr.co.enough.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import kr.co.enough.dto.AdminMentoDto;
import kr.co.enough.dto.LoginDto;
import kr.co.enough.dto.MemberDto;
import kr.co.enough.dto.MyStudyDto;
import kr.co.enough.dto.StudyDto;

public class AdminDao {
	DataSource datasource = null;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public AdminDao() throws NamingException {
		Context context = new InitialContext();
		//JNDI 
		//context : container(was) 안에서 이름기반으로 검색 제공
		datasource = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
		
		//datasource.getConnection() > POOL 에 있는 연결객체 얻어오기
		//다쓰면 반환 : datasource.close()
	}
	
	
	 public ArrayList<MyStudyDto> getAdminStudyList() throws SQLException{
	      conn= datasource.getConnection();
	      pstmt = null;
	      ArrayList<MyStudyDto> studyList = new ArrayList<MyStudyDto>();
	      try {
	      
	      String sql = "select study.study_num, study.study_name, study.start_date, "
	      		+ "study.end_date, member.name, study_subject_code.subject_name, "
	      		+ "study_level_code.level_name, study_loc_code.loc_name "
	      		+ "from study inner join member on study.mento_email = member.email "
	      		+ "inner join study_subject_code on study.study_subject_code = study_subject_code.subject_code "
	      		+ "inner join study_level_code on study.study_level_code = study_level_code.level_code "
	      		+ "inner join study_loc_code on study.study_loc_code = study_loc_code.loc_code";
	      pstmt = conn.prepareStatement(sql);
	      
	      rs = pstmt.executeQuery();
	      while(rs.next()) {
	         MyStudyDto m = new MyStudyDto();
	         m.setStudy_num(rs.getInt("study_num"));
	         m.setStudy_name(rs.getString("study_name"));
	         m.setStart_date(rs.getDate("start_date"));
	         m.setEnd_date(rs.getDate("end_date"));
	         m.setName(rs.getString("name"));
	         m.setSubject_name(rs.getString("subject_name"));
	         m.setLevel_name(rs.getString("level_name"));
	         m.setLoc_name(rs.getString("loc_name"));
	         studyList.add(m);
	         System.out.println("<<<<<" + studyList.get(0).getStudy_name());
	         System.out.println("<<<<<" + studyList.get(0).getStart_date());
	         System.out.println(">>>>>" + studyList.get(0).getName());
	      }
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {
	      rs.close();
	      pstmt.close();
	      conn.close(); //반환하기
	   }
	      return studyList;
	}
	
	 public ArrayList<AdminMentoDto> getAdminReadyMentoList() throws SQLException{
	      conn= datasource.getConnection();
	      pstmt = null;
	      ArrayList<AdminMentoDto> mentoList = new ArrayList<AdminMentoDto>();
	      try {
	      
	      String sql = "select mento.mento_email, member.name, "
	      		+ "member.hp, mento.career, mento_staus_code.staus_name "
	      		+ "from mento inner join member on mento.mento_email = member.email "
	      		+ "inner join MENTO_STAUS_CODE on mento.staus_code = mento_staus_code.staus_code "
	      		/*+ "where mento_staus_code.staus_code = 'MS00'"*/;	      
	      pstmt = conn.prepareStatement(sql);
	      
	      rs = pstmt.executeQuery();
	      while(rs.next()) {
	         AdminMentoDto m = new AdminMentoDto();
	         m.setMento_email(rs.getString("mento_email"));
	         m.setName(rs.getString("name"));
	         m.setHp(rs.getString("hp"));
	         m.setCareer(rs.getString("career"));
	         m.setStaus_name(rs.getString("staus_name"));
	         mentoList.add(m);
	         System.out.println("<<<<<" + mentoList.get(0).getMento_email());
	         System.out.println("<<<<<" + mentoList.get(0).getName());
	         System.out.println(">>>>>" + mentoList.get(0).getHp());
	      }
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {
	      rs.close();
	      pstmt.close();
	      conn.close(); //반환하기
	   }
	      return mentoList;
	}
	 
	
	
	 public ArrayList<MemberDto> getAdminMemberList() throws SQLException{
	      conn= datasource.getConnection();
	      pstmt = null;
	      ArrayList<MemberDto> memberList = new ArrayList<MemberDto>();
	      try {
	      
	      String sql ="select member.email, member.name, member.hp,"
	      		+ " member_grade_code.grade_name from member inner join "
	      		+ "member_grade_code on member.member_grade_code = member_grade_code.grade_code"
	      		+ " where member_grade_code.grade_code = 'M01'";     
	      pstmt = conn.prepareStatement(sql);
	      
	      rs = pstmt.executeQuery();
	      while(rs.next()) {
	         MemberDto m = new MemberDto();
	         m.setEmail(rs.getString("email"));
	         m.setName(rs.getString("name"));
	         m.setHp(rs.getString("hp"));
	         m.setGrade_name(rs.getString("grade_name"));
	         memberList.add(m);
	      }
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {
	      rs.close();
	      pstmt.close();
	      conn.close(); //반환하기
	   }
	      return memberList;
	}
	
	 
	 
	 public int getAdminMentoOkList(String mento_email) throws SQLException{
	      int resultrow=0;
	      PreparedStatement pstmt = null;
	      Connection conn = null;
	      
	      try {
	         String sql = "update mento set staus_code='MS00' where mento_email=?";
	         conn = datasource.getConnection();
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, mento_email);
	         pstmt.executeUpdate();
	         pstmt.close();
	         
	         String sql2 = "update member set member_grade_code='M02' where email=?";
	         pstmt = conn.prepareStatement(sql2);
	         pstmt.setString(1, mento_email);
	         pstmt.executeUpdate();
	      }catch(Exception e) {
	         e.printStackTrace();
	      }
	      finally {
	         pstmt.close();
	         conn.close();
	      }
	      return resultrow;
	   }
	 	
	 
	 public int getAdminMentoNoList(String mento_email) throws SQLException{
	      int resultrow=0;
	      PreparedStatement pstmt = null;
	      Connection conn = null;
	      
	      try {
	         String sql = "delete from mento where mento_email=?";
	         conn = datasource.getConnection();
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, mento_email);
	         resultrow = pstmt.executeUpdate();
	         pstmt.close();
	         
	         String sql2 = "update member set member_grade_code='M01' where email=?";
	         pstmt = conn.prepareStatement(sql2);
	         pstmt.setString(1, mento_email);
	         pstmt.executeUpdate();
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {
	         pstmt.close();
	         conn.close();
	      }
	      return resultrow;
	   }
	 
	 
	 
	 public int getAdminStudyDeleteList(int study_num) throws SQLException{
	      int resultrow=0;
	      PreparedStatement pstmt = null;
	      Connection conn = null;
	      
	      try {
	         String sql = "delete from study where study_num=?";
	         conn = datasource.getConnection();
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, study_num);
	         resultrow = pstmt.executeUpdate();
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {
	         pstmt.close();
	         conn.close();
	      }
	      return resultrow;
	   }
	 
	 
	 
	 public int getAdminWishDelete(int study_num) throws SQLException{
	      int resultrow=0;
	      PreparedStatement pstmt = null;
	      Connection conn = null;
	      
	      try {
	         String sql = "delete from wishlist where study_num=?";
	         conn = datasource.getConnection();
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, study_num);
	         resultrow = pstmt.executeUpdate();
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {
	         pstmt.close();
	         conn.close();
	      }
	      return resultrow;
	   }
	 
	 
	 
	 

	 public int getAdminStudyMemberDeleteList(int study_num) throws SQLException{
	      int resultrow=0;
	      PreparedStatement pstmt = null;
	      Connection conn = null;
	      
	      try {
	         String sql = "delete from study_member where study_num=?";
	         conn = datasource.getConnection();
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, study_num);
	         resultrow = pstmt.executeUpdate();
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {
	         pstmt.close();
	         conn.close();
	      }
	      return resultrow;
	   }
	 
	 public int getAdminPaymentDeleteList(int study_num) throws SQLException{
	      int resultrow=0;
	      PreparedStatement pstmt = null;
	      Connection conn = null;
	      
	      try {
	         String sql = "delete from payment where study_num=?";
	         conn = datasource.getConnection();
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, study_num);
	         resultrow = pstmt.executeUpdate();
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {
	         pstmt.close();
	         conn.close();
	      }
	      return resultrow;
	   }
	 
	 
	 
	 
	 public int getAdminStudyMentoList(String mento_email) throws SQLException{
	      int resultrow=0;
	      PreparedStatement pstmt = null;
	      Connection conn = null;
	      ResultSet rs = null;
	      try {
	         String sql = "select mento_email from study where mento_email=?";
	         conn = datasource.getConnection();
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, mento_email);
	         rs = pstmt.executeQuery();
	         if(rs.next()) {
	        	 resultrow = 1;
	         }else {
	        	 resultrow = 0;
	         }
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {
	         pstmt.close();
	         conn.close();
	      }
	      return resultrow;
	   }
	 
	 
	 public int getAdminMemberNoList(String email) throws SQLException{
	      int resultrow=0;
	      PreparedStatement pstmt = null;
	      Connection conn = null;
	      ResultSet rs = null;
	      
	      try {
	    	  
	         String sql = "delete from study_member where mem_email=?";
	         conn = datasource.getConnection();
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, email);
	         pstmt.executeUpdate();
	         pstmt.close();
	         
	         String sql2 = "delete from payment where mem_email=?";
	         pstmt = conn.prepareStatement(sql2);
	         pstmt.setString(1, email);
	         pstmt.executeUpdate();
	         pstmt.close();
	         
	         String sql3 = "delete from wishlist where mem_email=?";
	         pstmt = conn.prepareStatement(sql3);
	         pstmt.setString(1, email);
	         pstmt.executeUpdate();
	         pstmt.close();
	         
	         String sql4 = "delete from member where email=?";
	         pstmt = conn.prepareStatement(sql4);
	         pstmt.setString(1, email);
	         pstmt.executeUpdate();
	         
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {
	         pstmt.close();
	         conn.close();
	      }
	      return resultrow;
	   }
	 
	 
	 
	 public int getAdminMeberCheck(String email) throws SQLException{
	      int resultrow=0;
	      PreparedStatement pstmt = null;
	      Connection conn = null;
	      ResultSet rs = null;
	      try {
	         String sql = "select mento_email from mento where mento_email=?";
	         conn = datasource.getConnection();
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, email);
	         rs = pstmt.executeQuery();
	         if(rs.next()) {
	        	 resultrow = 1;
	         }else {
	        	 resultrow = 0;
	         }
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {
	         pstmt.close();
	         conn.close();
	      }
	      return resultrow;
	   }
	 
	 
	 
	 public String getAdminStudyTF() throws SQLException{
	     String resultrow=""; 
		 PreparedStatement pstmt = null;
	      Connection conn = null;
	      ResultSet rs = null;
	      try {
	         String sql = "select mento_email from study";
	         conn = datasource.getConnection();
	         pstmt = conn.prepareStatement(sql);
	         rs = pstmt.executeQuery();
	         if(rs.next()) {
	        	 resultrow = "true";
	         }else {
	        	 resultrow = "false";
	         }
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {
	         pstmt.close();
	         conn.close();
	      }
	      return resultrow;
	   }
	 
	 
	 public String getMentoTF() throws SQLException{
	     String resultrow=""; 
		 PreparedStatement pstmt = null;
	      Connection conn = null;
	      ResultSet rs = null;
	      try {
	         String sql = "select mento_email from mento";
	         conn = datasource.getConnection();
	         pstmt = conn.prepareStatement(sql);
	         rs = pstmt.executeQuery();
	         if(rs.next()) {
	        	 resultrow = "true";
	         }else {
	        	 resultrow = "false";
	         }
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {
	         pstmt.close();
	         conn.close();
	      }
	      return resultrow;
	   }
	
}
