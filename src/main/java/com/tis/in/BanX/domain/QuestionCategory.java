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
@Table(name = "q_question_category")
public class QuestionCategory {

	@Id
	@Column(name = "question_category_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long questionCategoryId;

	@Column(name = "question_category_name")
	@NotBlank(message = "QUESTION_CATEGOTY_NAME_NOT_BLANK")
	@Size(min = 2, max = 255, message = "QUESTION_CATEGORY_NAME_SIZE_CRITERIA_ERROR")
	private String questionCategoryName;

	@Column(name = "question_category_description")
	private String questionCategoryDescription;

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

	public String getQuestionCategoryDescription() {
		return questionCategoryDescription;
	}

	public void setQuestionCategoryDescription(String questionCategoryDescription) {
		this.questionCategoryDescription = questionCategoryDescription;
	}

	public AuditInfo getAuditInfo() {
		return auditInfo;
	}

	public void setAuditInfo(AuditInfo auditInfo) {
		this.auditInfo = auditInfo;
	}

}
