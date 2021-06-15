package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="Member : 회원 정보")
public class Member {
	@ApiModelProperty(value = "아이디")
	private String userId;
	@ApiModelProperty(value = "비밀번호")
	private String userPwd;
	@ApiModelProperty(value = "닉네임")
	private String userName;
	@ApiModelProperty(value = "전화번호")
	private String phoneNum;
	@ApiModelProperty(value = "이메일")
	private String email;
	private String salt;
	

	public Member() {}
	
	public Member(String userId, String userPwd, String userName, String phoneNum, String email) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.phoneNum = phoneNum;
		this.email = email;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public String toString() {
		return "Member [userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName + ", phoneNum=" + phoneNum
				+ ", email=" + email + "]";
	}
	
}
