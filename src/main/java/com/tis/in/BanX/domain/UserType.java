package com.tis.in.BanX.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "q_user_type")
public class UserType {

	@Id
	@Column(name = "user_type_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userTypeId;

	@Column(name = "user_type_name")
	@NotBlank(message = "USERTYPE_NAME_NOT_BLANK")
	private String userTypeName;

	@Embedded
	private AuditInfo auditInfo;
	
	public long getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(long userTypeId) {
		this.userTypeId = userTypeId;
	}

	public String getUserTypeName() {
		return userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	public AuditInfo getAuditInfo() {
		return auditInfo;
	}

	public void setAuditInfo(AuditInfo auditInfo) {
		this.auditInfo = auditInfo;
	}
	
}
