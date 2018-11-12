package kr.co.enough.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import kr.co.enough.dto.MentoStudyDto;
import kr.co.enough.dto.StudyDto;
import kr.co.enough.dto.StudyMemberDto;

public class StudyDao {
	DataSource datasource;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public StudyDao() throws NamingException {
		Context context = new InitialContext();
		// JNDI
		// context : container(was) 안에서 이름기반으로 검색 제공
		datasource = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
	}

	// 스터디 전체목록 조회
	public ArrayList<StudyDto> getStudyList(String study_subject_code) throws SQLException {
		conn = datasource.getConnection();
		ArrayList<StudyDto> studyList = new ArrayList<StudyDto>();
		try {
			System.out.println("dao에서 찍힌 과목코드 : " + study_subject_code);
			String sql = "select s.study_num, s.mento_email, s.study_name, s.study_write, s.start_date, s.end_date, s.price, sub.subject_name, lev.level_name, loc.loc_name, m.member_img from study s join study_subject_code sub on s.study_subject_code = sub.subject_code join study_level_code lev on s.study_level_code = lev.level_code join study_loc_code loc on s.study_loc_code = loc.loc_code join member m on s.mento_email = m.email where s.STUDY_SUBJECT_CODE = ? ORDER by s.start_date DESC, s.study_num DESC";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, study_subject_code);
			rs = pstmt.executeQuery();
			System.out.println(sql);

			while (rs.next()) {
				StudyDto study = new StudyDto();
				study.setStudy_num(rs.getInt("study_num"));
				study.setMento_email(rs.getString("mento_email"));
				study.setStudy_name(rs.getString("study_name"));
				study.setStudy_write(rs.getString("study_write"));
				study.setStart_date(rs.getDate("start_date"));
				study.setEnd_date(rs.getDate("end_date"));
				study.setPrice(rs.getInt("price"));
				study.setStudy_subject_code(rs.getString("subject_name"));
				study.setStudy_level_code(rs.getString("level_name"));
				study.setStudy_loc_code(rs.getString("loc_name"));
				study.setMember_img(rs.getString("member_img"));
				studyList.add(study);
			}
			System.out.println(studyList.get(0).getMember_img());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
			conn.close(); // 반환하기
		}
		return studyList;
	}

	// 스터디 신청
	public int ApplyStudy(String mento_email, String study_subject_code, String study_level_code, String study_loc_code)
			throws SQLException {
		int resultrow = 0;
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {

			String sql = "insert into study(mento_email, study_subject_code, study_level_code, study_loc_code) values(?,?,?,?)";
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mento_email);
			pstmt.setString(2, study_subject_code);
			pstmt.setString(3, study_level_code);
			pstmt.setString(4, study_loc_code);

			resultrow = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("Insert :" + e.getMessage());
		} finally {
			pstmt.close();
			conn.close();
		}

		return resultrow;
	}

	// 스터디 지역, 과목종류, 레벨비교
	public ArrayList<StudyDto> SearchStudyCheck(String study_subject_code, String[] loc, String[] level)
			throws SQLException {
		conn = datasource.getConnection();
		ArrayList<StudyDto> studyList = new ArrayList<StudyDto>();
		String[] locArr = loc;
		String[] levelArr = level;
		String levelSql = "";
		String locSql = ""; /* STUDY_LEVEL_CODE='SL00' or STUDY_LEVEL_CODE='SL01' or study_loc_code='hd'; */

		for(int i=0; i<locArr.length; i++){
			if(locArr.length == 1){
				locSql += "(s.STUDY_LOC_CODE = '" + locArr[i] + "') and ";	
			} else {
				if(i == 0){
					locSql += "(s.STUDY_LOC_CODE = '" + locArr[i] + "' or ";
				} else if(i == (locArr.length-1)){
					locSql += "s.STUDY_LOC_CODE = '" + locArr[i] + "') and ";
				} else {
					locSql += "s.STUDY_LOC_CODE = '" + locArr[i] + "' or ";
				}
			}
		}

		for(int i=0; i<levelArr.length; i++){
			if(levelArr.length == 1){
				levelSql += "(s.STUDY_LEVEL_CODE = '" + levelArr[i] + "')";	
			} else {
				if(i == 0){
					levelSql += "(s.STUDY_LEVEL_CODE = '" + levelArr[i] + "' or ";
				} else if(i == (levelArr.length-1)){
					levelSql += "s.STUDY_LEVEL_CODE = '" + levelArr[i] + "')";
				} else {
					levelSql += "s.STUDY_LEVEL_CODE = '" + levelArr[i] + "' or ";
				}
			}
		}

		try {
			System.out.println("dao에서 찍힌 과목코드 : " + study_subject_code);
			String sql = "select s.study_num, s.mento_email, s.study_name, s.study_write, s.start_date, s.end_date, s.price, sub.subject_name, lev.level_name, loc.loc_name, m.member_img from study s join study_subject_code sub on s.study_subject_code = sub.subject_code join study_level_code lev on s.study_level_code = lev.level_code join study_loc_code loc on s.study_loc_code = loc.loc_code join member m on s.mento_email = m.email where s.STUDY_SUBJECT_CODE = ? and ";	
			sql += locSql;
			sql += levelSql;

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, study_subject_code);
			System.out.print("필터 검색한 sql 문 : " + sql);

			rs = pstmt.executeQuery();
			System.out.println("rs객체로 쿼리문 실행했다");

			while (rs.next()) {
				StudyDto study = new StudyDto();
				study.setStudy_num(rs.getInt("study_num"));
				study.setMento_email(rs.getString("mento_email"));
				study.setStudy_name(rs.getString("study_name"));
				study.setStudy_write(rs.getString("study_write"));
				study.setStart_date(rs.getDate("start_date"));
				study.setEnd_date(rs.getDate("end_date"));
				study.setPrice(rs.getInt("price"));
				study.setStudy_subject_code(rs.getString("subject_name"));
				study.setStudy_level_code(rs.getString("level_name"));
				study.setStudy_loc_code(rs.getString("loc_name"));
				study.setMember_img(rs.getString("member_img"));
				studyList.add(study);
			}
			System.out.println(studyList.get(0).getMento_email());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
			conn.close(); // 반환하기
		}
		return studyList;
	}

	public StudyDto StudyDetail(int study_num) throws SQLException {
		conn = datasource.getConnection();
		StudyDto study = new StudyDto();
		System.out.println("study_num을 받아서 dao 에 찍혔다 : " + study_num);
		try {
			String sql = "select s.study_img, s.study_num, s.mento_email, s.study_name, s.study_write, s.start_date, s.end_date, s.price, sub.subject_name, lev.level_name, loc.loc_name, m.member_img from study s join study_subject_code sub on s.study_subject_code = sub.subject_code join study_level_code lev on s.study_level_code = lev.level_code join study_loc_code loc on s.study_loc_code = loc.loc_code join member m on s.mento_email = m.email where study_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, study_num);
			System.out.println(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				study.setStudy_img(rs.getString("STUDY_IMG"));
				study.setStudy_num(rs.getInt("STUDY_NUM"));
				study.setMento_email(rs.getString("mento_email"));
				study.setStudy_name(rs.getString("study_name"));
				study.setStudy_write(rs.getString("study_write"));
				study.setStart_date(rs.getDate("start_date"));
				study.setEnd_date(rs.getDate("end_date"));
				study.setPrice(rs.getInt("price"));
				study.setStudy_subject_code(rs.getString("subject_name"));
				study.setStudy_level_code(rs.getString("level_name"));
				study.setStudy_loc_code(rs.getString("loc_name"));
				study.setMember_img(rs.getString("member_img"));
			}
			System.out.println("dto가 찍힐까 : "+study);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
			conn.close(); // 반환하기
		}
		return study;
}


	// 스터디 만들기
	public int InsertStudy(String mento_email, String study_name, String study_write, Date start_date, Date end_date,
			int price, String study_subject_code, String study_level_code, String study_loc_code, String study_img)
			throws SQLException {
		int resultrow = 0;
		System.out.println("study insert dao 에서 찍어봄 : " + price);
		try {
			String sql = "insert into study(mento_email, study_name, study_write, start_date, end_date, price, study_subject_code, study_level_code, study_loc_code, study_img) values(?,?,?,?,?,?,?,?,?,?)";
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, mento_email);
			pstmt.setString(2, study_name);
			pstmt.setString(3, study_write);
			pstmt.setDate(4, start_date);
			pstmt.setDate(5, end_date);
			pstmt.setInt(6, price);
			pstmt.setString(7, study_subject_code);
			pstmt.setString(8, study_level_code);
			pstmt.setString(9, study_loc_code);
			pstmt.setString(10, study_img);

			resultrow = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
			conn.close();
		}
		return resultrow;
	}

	public ArrayList<MentoStudyDto> getMentoStudyList(String mento_email) throws SQLException { /* 멘토가 생성한 스터디 목록 보기 */
		conn = datasource.getConnection();
		ArrayList<MentoStudyDto> studyList = new ArrayList<MentoStudyDto>();
		try {
			String sql = "select s.study_num, s.study_name, s.study_write, s.start_date, s.end_date, s.price, sub.subject_name, lev.level_name, loc.loc_name, s.study_img from study s join study_subject_code sub on s.study_subject_code = sub.subject_code join study_level_code lev on s.study_level_code = lev.level_code join study_loc_code loc on s.study_loc_code = loc.loc_code where s.mento_email = ? order by s.study_num desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mento_email);
			rs = pstmt.executeQuery();
			System.out.println(sql);

			while (rs.next()) {
				MentoStudyDto study = new MentoStudyDto();
				study.setStudy_num(rs.getInt("study_num"));
				study.setStudy_name(rs.getString("study_name"));
				study.setStudy_write(rs.getString("study_write"));
				study.setStart_date(rs.getDate("start_date"));
				study.setEnd_date(rs.getDate("end_date"));
				study.setPrice(rs.getInt("price"));
				study.setSubject_name(rs.getString("subject_name"));
				study.setLevel_name(rs.getString("level_name"));
				study.setLoc_name(rs.getString("loc_name"));
				study.setStudy_img(rs.getString("study_img"));
				studyList.add(study);
				//System.out.println("다오에서 찍힌 study 이름  : " + study.getStudy_name());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
			conn.close(); // 반환하기
		}
		return studyList;
	}

	public MentoStudyDto getMentoUpdateList(String mento_email, int study_num)
			throws SQLException { /* 멘토가 수정할 스터디 목록 보기 */
		conn = datasource.getConnection();
		MentoStudyDto study = new MentoStudyDto();
		System.out.println("dao 에서 멘토 이메일 : " + mento_email);
		System.out.println("dao 에서 스터디 번호 : " + study_num);

		try {
			String sql = "select study_num, study_name, study_write, start_date, end_date, price, study_subject_code, study_level_code, study_loc_code, study_img from study where mento_email = ? and study_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mento_email);
			pstmt.setInt(2, study_num);
			rs = pstmt.executeQuery();
			System.out.println(sql);

			while (rs.next()) {
				study.setStudy_num(rs.getInt("study_num"));
				study.setStudy_name(rs.getString("study_name"));
				study.setStudy_write(rs.getString("study_write"));
				study.setStart_date(rs.getDate("start_date"));
				study.setEnd_date(rs.getDate("end_date"));
				study.setPrice(rs.getInt("price"));
				study.setSubject_name(rs.getString("study_subject_code"));
				study.setLevel_name(rs.getString("study_level_code"));
				study.setLoc_name(rs.getString("study_loc_code"));
				study.setStudy_img(rs.getString("study_img"));
			}

			/* select 의 옵션은 value 값으로 뽑아야해서 study 테이블의 코드 값으로 조회했음 */

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
			conn.close(); // 반환하기
		}
		return study;
	}

	public int UpdateStudy(String study_name, String study_write, Date start_date, Date end_date, int price,
			String study_subject_code, String study_level_code, String study_loc_code, String study_img, int study_num)
			throws SQLException {
		int resultrow = 0;
		try {
			String sql = "update study set study_name = ?, study_write = ?, start_date = ?, end_date = ?, price = ?, study_subject_code = ?, study_level_code = ?, study_loc_code = ?, study_img = ? where study_num = ?";
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, study_name);
			pstmt.setString(2, study_write);
			pstmt.setDate(3, start_date);
			pstmt.setDate(4, end_date);
			pstmt.setInt(5, price);
			pstmt.setString(6, study_subject_code);
			pstmt.setString(7, study_level_code);
			pstmt.setString(8, study_loc_code);
			pstmt.setString(9, study_img);
			pstmt.setInt(10, study_num);

			resultrow = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
			conn.close();
		}
		return resultrow;
	}

	public int DeleteStudy(int study_num) throws SQLException { // 멘토가 자기 스터디 delete 하기
		int resultrow = 0;
		try {
			String sql = "delete from study where study_num = ?";
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, study_num);
			resultrow = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
			conn.close();
		}
		return resultrow;
	}

	public ArrayList<MentoStudyDto> getMentoStudy(String mento_email) throws SQLException { /* 멘토가 자기 스터디 회원 목록 보기 */
		conn = datasource.getConnection();
		ArrayList<MentoStudyDto> studyList = new ArrayList<MentoStudyDto>();
		try {
			String sql = "select study_num, study_name, study_write, start_date, end_date, price, study_subject_code, study_level_code, study_loc_code, study_img from study where mento_email = ? order by study_num desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mento_email);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MentoStudyDto study = new MentoStudyDto();
				study.setStudy_num(rs.getInt("study_num"));
				study.setStudy_name(rs.getString("study_name"));
				study.setStudy_write(rs.getString("study_write"));
				study.setStart_date(rs.getDate("start_date"));
				study.setEnd_date(rs.getDate("end_date"));
				study.setPrice(rs.getInt("price"));
				study.setSubject_name(rs.getString("study_subject_code"));
				study.setLevel_name(rs.getString("study_level_code"));
				study.setLoc_name(rs.getString("study_loc_code"));
				study.setStudy_img(rs.getString("study_img"));
				studyList.add(study);
				//System.out.println("다오에서 찍힌 studynum : "+study.getStudy_num());
			} 

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
			conn.close(); // 반환하기
		}
		return studyList;
	}

	public ArrayList<StudyMemberDto> getMentoStudyMember(int study_num) throws SQLException { /* 멘토가 자기 스터디 회원 목록 보기 */
		conn = datasource.getConnection();
		ArrayList<StudyMemberDto> studymember = new ArrayList<StudyMemberDto>();
		try {
			String sql = "select member.email, member.name, member.hp from member join study_member on member.email = study_member.mem_email where study_member.study_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, study_num);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				StudyMemberDto mem = new StudyMemberDto();
				mem.setEmail(rs.getString("email"));
				mem.setName(rs.getString("name"));
				mem.setHp(rs.getString("hp"));
				studymember.add(mem);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
			conn.close(); // 반환하기
		}
		return studymember;
	}

}
