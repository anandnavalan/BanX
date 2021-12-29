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
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "q_assessment")
public class Assessment_Select {

	@Id
	@Column(name = "assessment_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long assessmentId;

	@Column(name = "bank_exam_id")
	private long bankExamId;

	@Column(name = "user_id")
	private long userId;

	@Column(name = "assessment_date")
	private Date assesmentDate;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "status")
	private long status;

	@OneToMany(mappedBy = "assessment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	@OrderBy(value = "assessmentQuestionId")
	private Set<AssessmentQuestion_Select> assessmentQuestions;

	@Embedded
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

	public Set<AssessmentQuestion_Select> getAssessmentQuestions() {
		return assessmentQuestions;
	}

	public void setAssessmentQuestions(Set<AssessmentQuestion_Select> assessmentQuestions) {
		this.assessmentQuestions = assessmentQuestions;

		for (AssessmentQuestion_Select assessmentQuestion : assessmentQuestions) {
			assessmentQuestion.setAssessment(this);
		}
	}

	public AuditInfo getAuditInfo() {
		return auditInfo;
	}

	public void setAuditInfo(AuditInfo auditInfo) {
		this.auditInfo = auditInfo;
	}

}