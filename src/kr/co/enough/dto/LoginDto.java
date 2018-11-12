package kr.co.enough.dto;

public class LoginDto {
   private String email;
   private String pwd;
   private String name;
   private String hp;
   private String member_img;
   private String member_grade_code;
   
   public String getMember_img() {
      return member_img;
   }
   public void setMember_img(String member_img) {
      this.member_img = member_img;
   }
   public String getMember_grade_code() {
      return member_grade_code;
   }
   public void setMember_grade_code(String member_grade_code) {
      this.member_grade_code = member_grade_code;
   }
   public String getEmail() {
      return email;
   }
   public void setEmail(String email) {
      this.email = email;
   }
   public String getPwd() {
      return pwd;
   }
   public void setPwd(String pwd) {
      this.pwd = pwd;
   }
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public String getHp() {
      return hp;
   }
   public void setHp(String hp) {
      this.hp = hp;
   }

   
}