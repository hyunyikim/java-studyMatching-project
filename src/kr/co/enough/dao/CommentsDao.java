package kr.co.enough.dao;

import java.sql.Connection;
//import java.sql.Date;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import kr.co.enough.dto.CommentsDto;


public class CommentsDao {
	
	DataSource datasource = null;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public CommentsDao() throws NamingException {
		Context context = new InitialContext();		
		datasource = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
	}		
	
	public ArrayList<CommentsDto> getCommentsList(String mtemail) {  // 멘토별 코멘트 목록 불러오기 				
		
		System.out.println("comment dao 에서 받은 멘토 이메일 : "+mtemail);
		ArrayList<CommentsDto> commentList = new ArrayList<CommentsDto>();		
		
		try {
			String sql = "select c.COMMENTS_NUM num, c.MENTO_EMAIL mtemail, c.MEM_EMAIL mbemail, c.CONTENT content, c.COMMENTS_DATE cdate, m.member_img pimg, m.name mname from comments c join member m on c.mem_email = m.email \r\n" + 
					"where c.mento_email = ? order by num desc";
			
			conn = datasource.getConnection();	
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mtemail);
			rs = pstmt.executeQuery();
						 
			while(rs.next()) {
				CommentsDto m = new CommentsDto();
				m.setNum(rs.getString("num"));
				m.setMtemail(rs.getString("mtemail"));
				m.setMbemail(rs.getString("mbemail"));
				m.setContent(rs.getString("content"));
				m.setCdate(rs.getString("cdate"));
				m.setPimg(rs.getString("pimg"));
				m.setMname(rs.getString("mname"));
				commentList.add(m);				
			   }
			System.out.println("Comment Dao 에서 찍어봤다, 회원의 이름 : "+commentList.get(0).getMname());
			
		}catch(Exception e) {	         
	    } finally {	         
				 try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		         try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		         try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}         
	      }
		return commentList;
	}	
	
	public int commentsInsert(String email, String mtemail, String content) throws SQLException {
		int resultrow=0;
		System.out.print("comment dao에 도착 완료 ");
		System.out.println("회원 이메일 : "+email);
		System.out.println("멘토 이메일 : "+mtemail);
		System.out.println("코멘트 내용 : "+content);
		try {
			String sql = "insert into comments(MENTO_EMAIL, MEM_EMAIL, CONTENT) values (?, ?, ?)";
			   conn = datasource.getConnection();
			   pstmt = conn.prepareStatement(sql);
			   pstmt.setString(1, mtemail);
			   pstmt.setString(2, email);
			   pstmt.setString(3, content);  
			   resultrow = pstmt.executeUpdate();
			   System.out.println("dao에서 comment insert 시킨 것 : "+resultrow);
			
		}catch(Exception e) {
			System.out.println("Insert :" + e.getMessage());
		}finally {
			pstmt.close();
			conn.close();
		}
		return resultrow;
		
	}
	
	public int commentsDelete(int comments_num) throws SQLException {
		int resultrow=0;
		
		try {
			String sql = "delete from comments where comments_num = ?";
			   conn = datasource.getConnection();
			   pstmt = conn.prepareStatement(sql);
			   pstmt.setInt(1, comments_num);
			   resultrow = pstmt.executeUpdate();
			   //System.out.println("dao에서 comment insert 시킨 것 : "+resultrow);
			
		}catch(Exception e) {
			System.out.println("Insert :" + e.getMessage());
		}finally {
			pstmt.close();
			conn.close();
		}
		return resultrow;
	}
	
}






