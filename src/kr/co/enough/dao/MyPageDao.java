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

import kr.co.enough.dto.LoginDto;
import kr.co.enough.dto.MyStudyDto;
import kr.co.enough.dto.MyWishDto;


public class MyPageDao {

   DataSource datasource = null;
   Connection conn = null;
   PreparedStatement pstmt = null;
   ResultSet rs = null;
   
   public MyPageDao() throws NamingException {
      Context context = new InitialContext();
      //JNDI 
      //context : container(was) 안에서 이름기반으로 검색 제공
      datasource = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
      
      //datasource.getConnection() > POOL 에 있는 연결객체 얻어오기
      //다쓰면 반환 : datasource.close()
   }
   
   
   public ArrayList<LoginDto> getProfileList(String email) throws SQLException{
      conn= datasource.getConnection();
      pstmt = null;
      ArrayList<LoginDto> profileList = new ArrayList<LoginDto>();
      try {
      System.out.println(email);
      String sql = "select hp from member where email=?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, email);
      rs = pstmt.executeQuery();
      while(rs.next()) {
         LoginDto m = new LoginDto();
         m.setHp(rs.getString("hp"));
         profileList.add(m);
      }
      }catch(Exception e) {
         e.printStackTrace();
      }finally {
      rs.close();
      pstmt.close();
      conn.close(); //반환하기
   }
      return profileList;
}

   public int updateProfileList(LoginDto dto) throws SQLException{
      int resultrow=0;
      PreparedStatement pstmt = null;
      Connection conn = null;
      
      try {
         String sql = "update member set name=?, hp=?, member_img=? where email=?";
         conn = datasource.getConnection();
         pstmt = conn.prepareStatement(sql);
         System.out.println(dto.getMember_img());
         pstmt.setString(1, dto.getName());
         pstmt.setString(2, dto.getHp());
         pstmt.setString(3, dto.getMember_img());
         pstmt.setString(4, dto.getEmail());
         System.out.println("테스트테스트");
         resultrow = pstmt.executeUpdate();
         System.out.println(resultrow);
      }catch(Exception e) {
         e.printStackTrace();
      }finally {
         pstmt.close();
         conn.close();
      }
      return resultrow;
   }
   
   
   public int dropOut(String email) throws SQLException{
      int resultrow=0;
      PreparedStatement pstmt = null;
      Connection conn = null;
      
      try {
         String sql = "delete from member where email=?";
         conn = datasource.getConnection();
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, email);
         resultrow = pstmt.executeUpdate();
      }catch(Exception e) {
         e.printStackTrace();
      }finally {
         pstmt.close();
         conn.close();
      }
      return resultrow;
   }
   
   public int dropStudy(String email) throws SQLException{
	      int resultrow=0;
	      PreparedStatement pstmt = null;
	      Connection conn = null;
	      
	      try {
	         String sql = "delete from study where mento_email=?";
	         conn = datasource.getConnection();
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, email);
	         resultrow = pstmt.executeUpdate();
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {
	         pstmt.close();
	         conn.close();
	      }
	      return resultrow;
	   }

   
   public int dropPayment(String email) throws SQLException{
      int resultrow=0;
      PreparedStatement pstmt = null;
      Connection conn = null;
      
      try {
         String sql = "delete from payment where mem_email=?";
         conn = datasource.getConnection();
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, email);
         resultrow = pstmt.executeUpdate();
      }catch(Exception e) {
         e.printStackTrace();
      }finally {
         pstmt.close();
         conn.close();
      }
      return resultrow;
   } 
   
   
   
   
   public int dropOutMento(String email) throws SQLException{
      int resultrow=0;
      PreparedStatement pstmt = null;
      Connection conn = null;
      
      try {
         String sql = "delete from mento where mento_email=?";
         conn = datasource.getConnection();
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, email);
         resultrow = pstmt.executeUpdate();
      }catch(Exception e) {
         e.printStackTrace();
      }finally {
         pstmt.close();
         conn.close();
      }
      return resultrow;
   }
   
   public int dropOutWish(String email) throws SQLException{
      int resultrow=0;
      PreparedStatement pstmt = null;
      Connection conn = null;
      
      try {
         String sql = "delete from wishlist where mem_email=?";
         conn = datasource.getConnection();
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, email);
         resultrow = pstmt.executeUpdate();
      }catch(Exception e) {
         e.printStackTrace();
      }finally {
         pstmt.close();
         conn.close();
      }
      return resultrow;
   }
   
   
   public int dropOutStudyMember(String email) throws SQLException{
      int resultrow=0;
      PreparedStatement pstmt = null;
      Connection conn = null;
      
      try {
         String sql = "delete from study_member where mem_email=?";
         conn = datasource.getConnection();
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, email);
         resultrow = pstmt.executeUpdate();
      }catch(Exception e) {
         e.printStackTrace();
      }finally {
         pstmt.close();
         conn.close();
      }
      return resultrow;
   }
   
   
   
   
   
   
   
   public ArrayList<MyStudyDto> getMyStudyList(String email) throws SQLException{
      conn= datasource.getConnection();
      pstmt = null;
      ArrayList<MyStudyDto> myStudyList = new ArrayList<MyStudyDto>();
      try {
      System.out.println(email);
      String sql = "select study.study_num, study.study_name, study.start_date, "
            + "study.end_date,study_subject_code.subject_name, "
            + "study_level_code.level_name,study_loc_code.loc_name "
            + "from study inner join study_subject_code "
            + "on study.STUDY_SUBJECT_CODE = study_subject_code.SUBJECT_CODE "
            + "inner join STUDY_LEVEL_CODE on study.STUDY_LEVEL_CODE = STUDY_LEVEL_CODE.LEVEL_CODE "
            + "inner join STUDY_LOC_CODE on study.STUDY_LOC_CODE = STUDY_LOC_CODE.LOC_CODE "
            + "inner join study_member on study.STUDY_NUM = study_member.study_num "
            + "where study_member.MEM_EMAIL = ?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, email);
      rs = pstmt.executeQuery();
      while(rs.next()) {
         MyStudyDto m = new MyStudyDto();
         m.setStudy_num(rs.getInt("study_num"));
         m.setStudy_name(rs.getString("study_name"));
         m.setStart_date(rs.getDate("start_date"));
         m.setEnd_date(rs.getDate("end_date"));
         m.setSubject_name(rs.getString("subject_name"));
         m.setLevel_name(rs.getString("level_name"));
         m.setLoc_name(rs.getString("loc_name"));
         myStudyList.add(m);
      }
      }catch(Exception e) {
         e.printStackTrace();
      }finally {
      rs.close();
      pstmt.close();
      conn.close(); //반환하기
   }
      return myStudyList;
}
   
   
   
   public ArrayList<MyWishDto> getMyWishList(String email) throws SQLException{
      conn= datasource.getConnection();
      pstmt = null;
      ArrayList<MyWishDto> myWishList = new ArrayList<MyWishDto>();
      try {
      System.out.println(email);
     String sql = "select wishlist.study_num,study.study_name, study.start_date, " + 
      		"           study.end_date,study_subject_code.subject_name, " + 
      		"            study_level_code.level_name,study_loc_code.loc_name " + 
      		"            from study inner join study_subject_code " + 
      		"            on study.STUDY_SUBJECT_CODE = study_subject_code.SUBJECT_CODE " + 
      		"           inner join STUDY_LEVEL_CODE on study.STUDY_LEVEL_CODE = STUDY_LEVEL_CODE.LEVEL_CODE " + 
      		"            inner join STUDY_LOC_CODE on study.STUDY_LOC_CODE = STUDY_LOC_CODE.LOC_CODE " + 
      		"            inner join wishlist on wishlist.study_num = study.study_num" + 
      		"            where wishlist.MEM_EMAIL = ?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, email);
      rs = pstmt.executeQuery();
      while(rs.next()) {
         MyWishDto m = new MyWishDto();
         m.setStudy_num(rs.getInt("study_num"));
         m.setStudy_name(rs.getString("study_name"));
         m.setStart_date(rs.getDate("start_date"));
         m.setEnd_date(rs.getDate("end_date"));
         m.setSubject_name(rs.getString("subject_name"));
         m.setLevel_name(rs.getString("level_name"));
         m.setLoc_name(rs.getString("loc_name"));
         myWishList.add(m);
         System.out.println(myWishList.get(0).getStudy_name());
      }
      }catch(Exception e) {
         e.printStackTrace();
      }finally {
      rs.close();
      pstmt.close();
      conn.close(); //반환하기
   }
      return myWishList;
}
   
   
   public String getWishTF(String email) throws SQLException{
      conn= datasource.getConnection();
      pstmt = null;
      String TF="";
      try {
      String sql = "select mem_email from wishlist where mem_email=?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, email);
      rs = pstmt.executeQuery();
      if(rs.next()) {
         TF="true";
      
      }else {
         TF="false";
      }
      }catch(Exception e) {
         e.printStackTrace();
      }finally {
      rs.close();
      pstmt.close();
      conn.close(); //반환하기
   }
      return TF;
}
   
   public String getStudyTF(String email) throws SQLException{
      conn= datasource.getConnection();
      pstmt = null;
      String TF="";
      try {
      String sql = "select mem_email from study_member where mem_email=?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, email);
      rs = pstmt.executeQuery();
      if(rs.next()) {
         TF="true";
      
      }else {
         TF="false";
      }
      }catch(Exception e) {
         e.printStackTrace();
      }finally {
      rs.close();
      pstmt.close();
      conn.close(); //반환하기
   }
      return TF;
}
   
   
   
  public int deleteWish(int study_number) throws SQLException{
	      int resultrow=0;
	      PreparedStatement pstmt = null;
	      Connection conn = null;
	      
	      try {
	         String sql = "delete from wishlist where study_num=?";
	         conn = datasource.getConnection();
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, study_number);
	         resultrow = pstmt.executeUpdate();
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {
	         pstmt.close();
	         conn.close();
	      }
	      return resultrow;
	   }
   
   
   
   public int deleteMyStudyList(int study_number) throws SQLException{
	      int resultrow=0;
	      PreparedStatement pstmt = null;
	      Connection conn = null;
	      
	      try {
	         String sql = "delete from study_member where study_num=?";
	         conn = datasource.getConnection();
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, study_number);
	         pstmt.executeUpdate();
	         
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {
	         pstmt.close();
	         conn.close();
	      }
	      return resultrow;
	   }
   
   
}




