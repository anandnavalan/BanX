package com.tis.in.BanX.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "q_user")
public class User {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;

	@Column(name = "user_name")
	@NotBlank(message = "UserName is mandatory")
	@Size(min = 6, max = 50,message = "UserName should be minimum of 6 chars and maximum of 50 chars")
	private String userName;

	@Column(name = "user_password")
	@NotBlank(message = "UserPassword is mandatory")
	@Size(min = 8, max = 50, message = "Userpassword should be minimum of 8 chars and maximum of 50 chars")
	private String userPassword;

	@Column(name = "user_type")
	private Integer userType;

	@Column(name = "user_email")
	@NotBlank(message = "UserEmail is mandatory")
	@Email(message = "Email should be valid")
	private String userEmail;

	@Column(name = "user_mobile")
	@NotBlank(message = "UserMobile is mandatory")
	private String userMobile;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
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
