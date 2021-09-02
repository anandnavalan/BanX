package com.tis.in.BanX.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "q_admin")
public class AdminUser {

	@Id
	@Column(name = "admin_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long adminId;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "admin_name")
	@NotBlank(message = "Admin Name is Mandatory")
	@Size(min = 2, max = 255, message = "Admin Name should be min of 2 chars and max of 255 chars")
	private String adminName;

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
}
