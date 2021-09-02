package com.tis.in.BanX.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "q_user_type")
public class UserType {
	
	@Id
    @Column(name = "user_type_id")
	private long userTypeId;
	
	@Column(name = "user_type_name")
	private String userTypeName;

	public long getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(Integer userTypeId) {
		this.userTypeId = userTypeId;
	}

	public String getUserTypeName() {
		return userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}
	
}
