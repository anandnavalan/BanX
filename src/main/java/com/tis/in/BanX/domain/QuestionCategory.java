package com.tis.in.BanX.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "q_question_category")
public class QuestionCategory {

	@Id
	@Column(name = "question_category_id")
	private long questionCategoryId;

	@Column(name = "question_category_name")
	@NotBlank(message = "QuestionCategoryName is mandatory")
	private String questionCategoryName;

	@Embedded
	private AuditInfo auditInfo;

	public long getQuestionCategoryId() {
		return questionCategoryId;
	}

	public void setQuestionCategoryId(long questionCategoryId) {
		this.questionCategoryId = questionCategoryId;
	}

	public String getQuestionCategoryName() {
		return questionCategoryName;
	}

	public void setQuestionCategoryName(String questionCategoryName) {
		this.questionCategoryName = questionCategoryName;
	}

	public AuditInfo getAuditInfo() {
		return auditInfo;
	}

	public void setAuditInfo(AuditInfo auditInfo) {
		this.auditInfo = auditInfo;
	}

}
