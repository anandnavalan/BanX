package com.tis.in.BanX.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="q_bank_exam")
public class BankExam {
	
	@Id
	@Column(name="bank_exam_id")
	private long bankExamId;
	
	@Column(name="bank_exam_name")
	private String bankExamName;
	
	
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

	

}
