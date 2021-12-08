package com.tis.in.BanX.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "q_bank_exam")
public class BankExam {

	@Id
	@Column(name = "bank_exam_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long bankExamId;

	@Column(name = "bank_exam_name")
	@NotBlank(message = "BANK_EXAM_NAME_NOT_BLANK")
	private String bankExamName;

	@Embedded
	private AuditInfo auditInfo;

	public long getBankExamId() {
		return bankExamId;
	}

	public void setBankExamId(long bankExamId) {
		this.bankExamId = bankExamId;
	}

	public String getBankExamName() {
		return bankExamName;
	}

	public void setBankExamName(String bankExamName) {
		this.bankExamName = bankExamName;
	}

	public AuditInfo getAuditInfo() {
		return auditInfo;
	}

	public void setAuditInfo(AuditInfo auditInfo) {
		this.auditInfo = auditInfo;
	}

}
