package kr.co.enough.dto;

import java.util.Date;

public class MyStudyDto {
   private int study_num;
   private String study_name;
   private Date start_date;
   private Date end_date;
   private String subject_name;
   private String level_name;
   private String loc_name;
   private String name;
   public int getStudy_num() {
		return study_num;
	}
	public void setStudy_num(int study_num) {
		this.study_num = study_num;
	}
   public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getStudy_name() {
      return study_name;
   }
   public void setStudy_name(String study_name) {
      this.study_name = study_name;
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
   public String getSubject_name() {
      return subject_name;
   }
   public void setSubject_name(String subject_name) {
      this.subject_name = subject_name;
   }
   public String getLevel_name() {
      return level_name;
   }
   public void setLevel_name(String level_name) {
      this.level_name = level_name;
   }
   public String getLoc_name() {
      return loc_name;
   }
   public void setLoc_name(String loc_name) {
      this.loc_name = loc_name;
   }
   
   
   
   
}