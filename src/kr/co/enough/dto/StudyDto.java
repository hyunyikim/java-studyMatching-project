package kr.co.enough.dto;

import java.sql.Date;

/*스터디 dto에 member_img 변수 하나 추가했어요
 * db의 스터디 테이블이랑 변수가 다르다!!!*/

public class StudyDto {
	private int study_num;
	private String mento_email;
	private String study_name;
	private String study_write;
	private Date start_date;
	private Date end_date;
	private int price;
	private String study_subject_code;
	private String study_level_code;
	private String study_loc_code;
	private String member_img;
	private String study_img;
	
	public String getStudy_img() {
		return study_img;
	}
	public void setStudy_img(String study_img) {
		this.study_img = study_img;
	}
	public int getStudy_num() {
		return study_num;
	}
	public void setStudy_num(int study_num) {
		this.study_num = study_num;
	}
	public String getMento_email() {
		return mento_email;
	}
	public void setMento_email(String mento_email) {
		this.mento_email = mento_email;
	}
	public String getStudy_name() {
		return study_name;
	}
	public void setStudy_name(String study_name) {
		this.study_name = study_name;
	}
	public String getStudy_write() {
		return study_write;
	}
	public void setStudy_write(String study_write) {
		this.study_write = study_write;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getStudy_subject_code() {
		return study_subject_code;
	}
	public void setStudy_subject_code(String study_subject_code) {
		this.study_subject_code = study_subject_code;
	}
	public String getStudy_level_code() {
		return study_level_code;
	}
	public void setStudy_level_code(String study_level_code) {
		this.study_level_code = study_level_code;
	}
	public String getStudy_loc_code() {
		return study_loc_code;
	}
	public void setStudy_loc_code(String study_loc_code) {
		this.study_loc_code = study_loc_code;
	}
	public String getMember_img() {
		return member_img;
	}
	public void setMember_img(String member_img) {
		this.member_img = member_img;
	}
	
}