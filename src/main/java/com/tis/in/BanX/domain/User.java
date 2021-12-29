package com.tis.in.BanX.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "q_user")
public class User {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Column(name = "user_name")
	@NotBlank(message = "USER_NAME_NOT_BLANK")
	@Size(min = 2, max = 255, message = "USER_NAME_SIZE_CRITERIA_ERROR")
	private String userName;

	@Column(name = "user_password")
	@NotBlank(message = "USER_PASSWORD_NOT_BLANK")
	@Size(min = 2, max = 255, message = "USER_PASSWORD_SIZE_CRITERIA_ERROR")
	private String userPassword;

	@Column(name = "user_type")
	@Min(value = 1, message = "USER_TYPE_NOT_BLANK")
	private Integer userType;

	@Column(name = "user_email")
	@NotBlank(message = "USER_EMAIL_NOT_BLANK")
	@Size(min = 2, max = 255, message = "USER_EMAIL_SIZE_CRITERIA_ERROR")
	private String userEmail;

	@Column(name = "user_mobile")
	@NotBlank(message = "USER_MOBILE_NOT_BLANK")

	private String userMobile;

	@Embedded
	private AuditInfo auditInfo;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
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

	public AuditInfo getAuditInfo() {
		return auditInfo;
	}

	public void setAuditInfo(AuditInfo auditInfo) {
		this.auditInfo = auditInfo;
	}

}