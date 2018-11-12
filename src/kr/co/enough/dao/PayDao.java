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

import kr.co.enough.dto.MentoDto;


public class PayDao {
	
	public PayDao() throws NamingException {
		Context context = new InitialContext();		
		datasource = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
	}
	
	DataSource datasource = null;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;	
			

	public int payInsert(int STUDY_NUM, String MEM_EMAIL, int PAYMENT_PRICE) throws SQLException {
		
		int resultrow = 0;		
		
		try {			   
			   String sql = "INSERT INTO PAYMENT(STUDY_NUM, MEM_EMAIL, PAYMENT_PRICE)" + 
			   		"VALUES (?,?,?)";
			   
			   conn = datasource.getConnection();
			   pstmt = conn.prepareStatement(sql);			   
			   pstmt.setInt(1, STUDY_NUM);
			   pstmt.setString(2, MEM_EMAIL);			   
			   pstmt.setInt(3, PAYMENT_PRICE);
			   
			   resultrow = pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("Insert :" + e.getMessage());
		}finally {
			pstmt.close();
			conn.close();
		} 
		return resultrow;
	}	
	
	public int payDelete(String MEM_EMAIL, int study_num){		
//		conn = null;
//		pstmt = null;
		int resultrow = 0;
		
		try{
			conn = datasource.getConnection();
			
			String sql = "delete from PAYMENT where MEM_EMAIL = ? and STUDY_NUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, MEM_EMAIL);				
			pstmt.setInt(2, study_num);				
			resultrow=pstmt.executeUpdate();		
			System.out.println("payDelet 성공");
			
		}catch( Exception e) { 
			e.printStackTrace();
		}finally{
			try {
				if( pstmt != null) pstmt.close();
				if( conn != null) conn.close();
				
			}catch (SQLException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultrow;
	}//end delete

	public int studyMemberInsert(int STUDY_NUM, String MEM_EMAIL) throws SQLException {
		
		int resultrow = 0;		
		try {
			String sql = "INSERT INTO STUDY_MEMBER(STUDY_NUM, MEM_EMAIL) VALUES (?,?)";
			
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement(sql);			   
			pstmt.setInt(1, STUDY_NUM);
			pstmt.setString(2, MEM_EMAIL);					   
			
			resultrow = pstmt.executeUpdate();
			System.out.println("studyMemberInsert 메서드 수행 : "+resultrow);
		}catch(Exception e) {
			System.out.println("Insert :" + e.getMessage());
		}finally {
			pstmt.close();
			conn.close();
		} 
		return resultrow;
	}	
	
	public int studyMemberDelete(String MEM_EMAIL, int study_num){
		System.out.println("email: "+MEM_EMAIL);
		int resultrow = 0;
		 
		try{
			conn = datasource.getConnection();
			
			String sql = "delete from STUDY_MEMBER where MEM_EMAIL = ? and STUDY_NUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, MEM_EMAIL);				
			pstmt.setInt(2, study_num);				
			resultrow=pstmt.executeUpdate();		
          	System.out.println("studyMemberDelete resultrow: "+resultrow);
		
		}catch( Exception e) { 
			e.printStackTrace();
		}finally{
			try {
				if( pstmt != null) pstmt.close();
				if( conn != null) conn.close();
				
			}catch (SQLException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultrow;
	}//end delete
	
	
}






