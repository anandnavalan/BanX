package com.tis.in.BanX.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(name = "q_question_filter")
public class QuestionFilter {
	
	@Id
	@Column(name = "questionn_filter_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long questionFilterId;
	
	@Column(name = "question_category_id")
	private Long questionCategoryId;

	@Column(name = "questionsubcategory_id")
	private Long questionSubCategoryId;
	
	@Column(name = "question_filter_name")
	@NotBlank(message = "QUESTION_FILTER_NAME_NOT_BLANK")
	@Size(min = 2, max = 255, message = "QUESTION_FILTER_NAME_SIZE_CRITERIA_ERROR")
	private String questionFilterName;
	

	@Column(name = "question_filter_description")
	private String questionFilterDescription;

	@Embedded
	private AuditInfo auditInfo;

	public long getQuestionFilterId() {
		return questionFilterId;
	}

	public void setQuestionFilterId(long questionFilterId) {
		this.questionFilterId = questionFilterId;
	}

	public Long getQuestionCategoryId() {
		return questionCategoryId;
	}

	public void setQuestionCategoryId(Long questionCategoryId) {
		this.questionCategoryId = questionCategoryId;
	}

	public Long getQuestionSubCategoryId() {
		return questionSubCategoryId;
	}

	public void setQuestionSubCategoryId(Long questionSubCategoryId) {
		this.questionSubCategoryId = questionSubCategoryId;
	}
	
	public String getQuestionFilterName() {
		return questionFilterName;
	}

	public void setQuestionFilterName(String questionFilterName) {
		this.questionFilterName = questionFilterName;
	}

	public String getQuestionFilterDescription() {
		return questionFilterDescription;
	}

	public void setQuestionFilterDescription(String questionFilterDescription) {
		this.questionFilterDescription = questionFilterDescription;
	}

	public AuditInfo getAuditInfo() {
		return auditInfo;
	}

	public void setAuditInfo(AuditInfo auditInfo) {
		this.auditInfo = auditInfo;
	}

}
