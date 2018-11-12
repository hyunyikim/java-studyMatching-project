package kr.co.enough.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import kr.co.enough.dto.LoginDto;

public class LoginDao {
	DataSource datasource = null;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public LoginDao() throws NamingException {
		Context context = new InitialContext();
		//JNDI 
		//context : container(was) 안에서 이름기반으로 검색 제공
		datasource = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
		
		//datasource.getConnection() > POOL 에 있는 연결객체 얻어오기
		//다쓰면 반환 : datasource.close()
	}
	
	public String getloginTF(String email, String pwd) throws SQLException{
		conn= datasource.getConnection();
		pstmt = null;
		String TF="";
		try {
		String sql = "select email,pwd from member where email=? and pwd=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, email);
		pstmt.setString(2, pwd);
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
	
	public ArrayList<LoginDto> getloginList(String email, String pwd) throws SQLException{
		conn= datasource.getConnection();
		pstmt = null;
		ArrayList<LoginDto> loginlist = new ArrayList<LoginDto>();
		try {
		String sql = "select email,name,member_img,member_grade_code from member where email=? and pwd=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, email);
		pstmt.setString(2, pwd);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			LoginDto m = new LoginDto();
			m.setEmail(rs.getString("email"));
			m.setName(rs.getString("name"));
			m.setMember_img(rs.getString("member_img"));
			m.setMember_grade_code(rs.getString("member_grade_code"));
			loginlist.add(m);
		}
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
		rs.close();
		pstmt.close();
		conn.close(); //반환하기
	}
		return loginlist;
}
		
}
