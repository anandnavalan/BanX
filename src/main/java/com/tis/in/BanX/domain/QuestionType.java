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
@Table(name = "q_question_type")
public class QuestionType {
	@Id
	@Column(name = "question_type_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long questionTypeId;

	@Column(name = "question_type_name")
	@NotBlank(message = "QUESTION_TYPE_NAME_NOT_BLANK")
	@Size(min = 2, max = 255, message = "QUESTION_TYPE_NAME_SIZE_CRITERIA_ERROR")
	private String questionTypeName;

	@Column(name = "question_type_description")
	private String questionTypeDescription;

	@Embedded
	private AuditInfo auditInfo;

	public long getQuestionTypeId() {
		return questionTypeId;
	}

	public void setQuestionTypeId(long questionTypeId) {
		this.questionTypeId = questionTypeId;
	}

	public String getQuestionTypeName() {
		return questionTypeName;
	}

	public void setQuestionTypeName(String questionTypeName) {
		this.questionTypeName = questionTypeName;
	}

	public String getQuestionTypeDescription() {
		return questionTypeDescription;
	}

	public void setQuestionTypeDescription(String questionTypeDescription) {
		this.questionTypeDescription = questionTypeDescription;
	}

	public AuditInfo getAuditInfo() {
		return auditInfo;
	}

	public void setAuditInfo(AuditInfo auditInfo) {
		this.auditInfo = auditInfo;
	}

}
