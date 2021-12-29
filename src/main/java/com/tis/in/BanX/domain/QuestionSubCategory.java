package com.tis.in.BanX.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "q_question_sub_category")
public class QuestionSubCategory {

	@Id
	@Column(name = "question_sub_category_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_sub_category_sequence")
	@SequenceGenerator(name = "question_sub_category_sequence", sequenceName = "question_sub_category_sequence", allocationSize = 1)
	private long questionSubCategoryId;

	@Column(name = "question_category_id")
	private long questionCategoryId;

	@Column(name = "question_sub_category_name")
	@NotBlank(message = "QUESTION_SUB_CATEGOTY_NAME_NOT_BLANK")
	@Size(min = 2, max = 255, message = "QUESTION_SUB_CATEGORY_NAME_SIZE_CRITERIA_ERROR")
	private String questionSubCategoryName;

	@Column(name = "question_sub_category_description")
	private String questionSubCategoryDescription;

	@Embedded
	private AuditInfo auditInfo;

	public long getQuestionSubCategoryId() {
		return questionSubCategoryId;
	}

	public void setQuestionSubCategoryId(long questionSubCategoryId) {
		this.questionSubCategoryId = questionSubCategoryId;
	}

	public long getQuestionCategoryId() {
		return questionCategoryId;
	}

	public void setQuestionCategoryId(long questionCategoryId) {
		this.questionCategoryId = questionCategoryId;
	}

	public String getQuestionSubCategoryName() {
		return questionSubCategoryName;
	}

	public void setQuestionSubCategoryName(String questionSubCategoryName) {
		this.questionSubCategoryName = questionSubCategoryName;
	}

	public String getQuestionSubCategoryDescription() {
		return questionSubCategoryDescription;
	}

	public void setQuestionSubCategoryDescription(String questionSubCategoryDescription) {
		this.questionSubCategoryDescription = questionSubCategoryDescription;
	}

	public AuditInfo getAuditInfo() {
		return auditInfo;
	}

	public void setAuditInfo(AuditInfo auditInfo) {
		this.auditInfo = auditInfo;
	}

}
