package com.tis.in.BanX.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="q_student-bank_exam")
public class StudentBankExam {

	
	@Id
	@Column(name="student_bank_exam_id")
	private int studentBankExamId;
	
	@Column(name="student_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long studentId;
	
	@Column(name="bank_exam_id")
	private long bankExamId;

	public int getStudentBankExamId() {
		return studentBankExamId;
	}

	public void setStudentBankExamId(int studentBankExamId) {
		this.studentBankExamId = studentBankExamId;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public long getBankExamId() {
		return bankExamId;
	}

	public void setBankExamId(long bankExamId) {
		this.bankExamId = bankExamId;
	}
}
