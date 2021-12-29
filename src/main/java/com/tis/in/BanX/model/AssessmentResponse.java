package com.tis.in.BanX.model;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tis.in.BanX.domain.AuditInfo;

public class AssessmentResponse {

	private long assessmentId;

	private long bankExamId;

	private long userId;

	private Date assesmentDate;

	private Date startDate;

	private Date endDate;

	private long status;

	@JsonManagedReference
	private Set<AssessmentQuestionResponse> assessmentQuestions;

	private AuditInfo auditInfo;

	public long getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(long assessmentId) {
		this.assessmentId = assessmentId;
	}

	public long getBankExamId() {
		return bankExamId;
	}

	public void setBankExamId(long bankExamId) {
		this.bankExamId = bankExamId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Date getAssesmentDate() {
		return assesmentDate;
	}

	public void setAssesmentDate(Date assesmentDate) {
		this.assesmentDate = assesmentDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public Set<AssessmentQuestionResponse> getAssessmentQuestions() {
		return assessmentQuestions;
	}

	public void setAssessmentQuestions(Set<AssessmentQuestionResponse> assessmentQuestions) {
		this.assessmentQuestions = assessmentQuestions;
	}

	public AuditInfo getAuditInfo() {
		return auditInfo;
	}

	public void setAuditInfo(AuditInfo auditInfo) {
		this.auditInfo = auditInfo;
	}

}