package kr.co.enough.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JoinDao {
	DataSource datasource = null;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public JoinDao() throws NamingException {
		Context context = new InitialContext();
		//JNDI 
		//context : container(was) 안에서 이름기반으로 검색 제공
		datasource = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
		
		//datasource.getConnection() > POOL 에 있는 연결객체 얻어오기
		//다쓰면 반환 : datasource.close()
	}
	
	
	public int insertJoin(String email, String pwd, String name, String hp) throws SQLException {
		//insert into memo(id,email,content) values(?,?,?)
		int resultrow=0;
		conn = null;
		pstmt = null;
		try {
			   String sql="insert into member(email,pwd,name,hp) values(?,?,?,?)";
			   conn= datasource.getConnection();
			   pstmt = conn.prepareStatement(sql);
			   pstmt.setString(1, email);
			   pstmt.setString(2, pwd);
			   pstmt.setString(3, name);
			   pstmt.setString(4, hp);
			   
			   resultrow = pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("Insert :" + e.getMessage());
		}finally {
			pstmt.close();
			conn.close();
		}
 
		return resultrow;
	}
	
	public String emailCheck(String email) throws SQLException {
		String emailCheck= null;
		conn = null;
		pstmt = null;
		rs = null;
		try {
			
				String sql = "select email from member where email=?";
				conn = datasource.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, email);
				rs = pstmt.executeQuery();
				if(rs.next()) {
						emailCheck = "false";
				}else {
						emailCheck = "true";
				}
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		return emailCheck;
	}
	
	
	
	
	
	
	
	
	
	
}
