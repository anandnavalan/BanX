package com.tis.in.BanX.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "q_exam")
public class Exam {

	@Id
	@Column(name = "exam_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "q_exam_sequence")
	@SequenceGenerator(name = "q_exam_sequence", sequenceName = "q_exam_sequence", allocationSize = 1)
	private long examId;

	@Column(name = "bank_exam_id")
	private long bankExamId;

	@Column(name = "exam_date")
	private Date examDate;

	@Column(name = "total_questions")
	private int totalQuestions;

	private boolean retry;

	@Column(name = "negative_mark_applicable")
	private boolean negativeMarkApplicable;

	@Column(name = "negative_mark")
	private float negativeMark;

	@Column(name = "order_by")
	private int orderBy;

	@Column(name = "status")
	private int status;
	
	@Column(name = "difficulty")
	private Integer difficulty;


	@OneToMany(mappedBy = "exam", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<ExamPattern> examPatterns;

	@Embedded
	private AuditInfo auditInfo;

	public long getExamId() {
		return examId;
	}

	public void setExamId(long examId) {
		this.examId = examId;
	}

	public long getBankExamId() {
		return bankExamId;
	}

	public void setBankExamId(long bankExamId) {
		this.bankExamId = bankExamId;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public int getTotalQuestions() {
		return totalQuestions;
	}

	public void setTotalQuestions(int totalQuestions) {
		this.totalQuestions = totalQuestions;
	}

	public boolean isRetry() {
		return retry;
	}

	public void setRetry(boolean retry) {
		this.retry = retry;
	}

	public boolean isNegativeMarkApplicable() {
		return negativeMarkApplicable;
	}

	public void setNegativeMarkApplicable(boolean negativeMarkApplicable) {
		this.negativeMarkApplicable = negativeMarkApplicable;
	}

	public float getNegativeMark() {
		return negativeMark;
	}

	public void setNegativeMark(float negativeMark) {
		this.negativeMark = negativeMark;
	}

	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public Integer getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Integer difficulty) {
		this.difficulty = difficulty;
	}

	public AuditInfo getAuditInfo() {
		return auditInfo;
	}

	public void setAuditInfo(AuditInfo auditInfo) {
		this.auditInfo = auditInfo;
	}

	public Set<ExamPattern> getExamPatterns() {
		return examPatterns;
	}

	public void setExamPatterns(Set<ExamPattern> examPatterns) {
		this.examPatterns = examPatterns;
		for (ExamPattern examPattern : examPatterns) {
			examPattern.setExam(this);
		}
	}

}
