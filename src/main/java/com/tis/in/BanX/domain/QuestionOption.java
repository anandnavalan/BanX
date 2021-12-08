package com.tis.in.BanX.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "q_question_options")
public class QuestionOption {

	@Id
	@Column(name = "question_option_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long questionOptionId;

	@Column(name = "question_option")
	private String questionOption;

	@Column(name = "is_answer")
	private boolean isAnswer;

	@ManyToOne(optional = false)
	@JoinColumn(name = "question_id", nullable = false)
	@JsonBackReference
	private Question question;

	public Long getQuestionOptionId() {
		return questionOptionId;
	}

	public void setQuestionOptionId(Long questionOptionId) {
		this.questionOptionId = questionOptionId;
	}

	public String getQuestionOption() {
		return questionOption;
	}

	public void setQuestionOption(String questionOption) {
		this.questionOption = questionOption;
	}

	public boolean getIsAnswer() {
		return isAnswer;
	}

	public void setIsAnswer(boolean isAnswer) {
		this.isAnswer = isAnswer;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}
