package com.tis.in.BanX.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "q_question_complexity")
public class QuestionComplexity {

	@Id
	@Column(name = "question_complexity_id")
	private long questionComplexityId;

	@Column(name = "question_complexity_name")
	@NotBlank(message = "questionComplexityName is mandatory")
	private String questionComplexityName;

	public long getQuestionComplexityId() {
		return questionComplexityId;
	}

	public void setQuestionComplexityId(long questionComplexityId) {
		this.questionComplexityId = questionComplexityId;
	}

	public String getQuestionComplexityName() {
		return questionComplexityName;
	}

	public void setQuestionComplexityName(String questionComplexityName) {
		this.questionComplexityName = questionComplexityName;
	}

}
