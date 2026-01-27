package com.blog.member.vo;


public class MemberVO {
	private String id;
	private String pw; // 현재 비밀번호
	private String newPw; // 바꿀 비밀번호
	private String name;
	private String gender;
	private String birth;
	private String tel;
	private String email;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getNewPw() {
		return newPw;
	}
	public void setNewPw(String newPw) {
		this.newPw = newPw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", pw=" + pw + ", newPw=" + newPw + ", name=" + name + ", gender=" + gender
				+ ", birth=" + birth + ", tel=" + tel + ", email=" + email + ", regDate=" +  ", conDate="
				 + ", status=" + ", gradeNo=" +  ", gradeName=" + "]";
	}
	
}


