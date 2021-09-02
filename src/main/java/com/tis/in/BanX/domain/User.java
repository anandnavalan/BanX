package com.tis.in.BanX.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "q_user")
public class User {

	@Id
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "user_password")
	private String userPassword;
	
	@Column(name = "user_type")
	private Integer userType;
	
	@Column(name = "user_email")
	@Email(message = "Email should be valid")
	private String userEmail;
	
	@Column(name = "user_mobile")
	private String userMobile;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return userPassword;
	}

	public void setPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword + ", userType="
				+ userType + ", userEmail=" + userEmail + ", userMobile=" + userMobile + "]";
	}

}
