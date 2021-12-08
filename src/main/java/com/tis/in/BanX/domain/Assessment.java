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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "q_assessment")
public class Assessment {

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
	private Set<AssessmentQuestion> assessmentQuestions;

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

	public Set<AssessmentQuestion> getAssessmentQuestions() {
		return assessmentQuestions;
	}

//	public void setQuestionOptions(Set<QuestionOption> questionOptions) {
//	this.questionOptions = questionOptions;
//
//	for (QuestionOption questionOption : questionOptions) {
//		questionOption.setQuestion(this);
//	}
//}

	public void setAssessmentQuestions(Set<AssessmentQuestion> assessmentQuestions) {
		this.assessmentQuestions = assessmentQuestions;

		for (AssessmentQuestion assessmentQuestion : assessmentQuestions) {
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