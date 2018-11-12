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

import kr.co.enough.dto.WishListDto;

public class WishListDao {

	DataSource datasource = null;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public WishListDao() throws NamingException {
		Context context = new InitialContext();
		datasource = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
	}

	public ArrayList<WishListDto> getWishList(String mem_email, int study_num) throws SQLException { // 멘토별 코멘트 목록 불러오기

		ArrayList<WishListDto> wishList = new ArrayList<WishListDto>();
		//System.out.println("wishlist 다오로 들어왔다");
		try {
			String sql = "select study_num, mem_email from wishlist where mem_email = ? and study_num = ?";

			conn = datasource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem_email);
			pstmt.setInt(2, study_num);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				WishListDto dto = new WishListDto();
				dto.setMem_email(rs.getString("mem_email"));
				dto.setStudy_num(rs.getInt("study_num"));
				wishList.add(dto);
				//System.out.println("다오에서 위시리스트 불러오기");
				//System.out.println(dto.getMem_email());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		return wishList;
	}

	public int wishListInsert(String mem_email, int study_num) throws SQLException {
		int resultrow = 0;
		try {
			String sql = "insert into wishlist values (?, ?)";
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem_email);
			pstmt.setInt(2, study_num);
			resultrow = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("Insert :" + e.getMessage());
		} finally {
			pstmt.close();
			conn.close();
		}
		return resultrow;
	}

	
	public int wishListDelete(String mem_email, int study_num) throws SQLException {
		int resultrow = 0;
		try {
			String sql = "delete from wishlist where study_num = ? and mem_email = ?";
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, study_num);
			pstmt.setString(2, mem_email);
			resultrow = pstmt.executeUpdate();
			// System.out.println("dao에서 comment insert 시킨 것 : "+resultrow);

		} catch (Exception e) {
			System.out.println("Insert :" + e.getMessage());
		} finally {
			pstmt.close();
			conn.close();
		}
		return resultrow;
	}

}
