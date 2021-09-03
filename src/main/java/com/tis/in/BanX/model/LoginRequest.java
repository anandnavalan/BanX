package com.tis.in.BanX.model;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
	
	@NotBlank(message = "UserName is Mandatory")
	private String userName;
	
	@NotBlank(message = "Password is Mandatory")
	private String password;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
