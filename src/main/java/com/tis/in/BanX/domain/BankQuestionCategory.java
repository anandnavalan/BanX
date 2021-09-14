package com.tis.in.BanX.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "q_bank_question_category")
public class BankQuestionCategory {

	@Id
	@Column(name = "bank_question_category_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long bankQuestionCategoryId;

	@Column(name = "bank_exam_id")
	private long bankExamId;

	@Column(name = "question_category_id")
	private long QuestionCategoryId;

	@Column(name = "bank_question_correct_answer_score")
	private float bankQuestionCorrectAnswerScore;

	@Column(name = "bank_question_wrong_answer_score")
	private float bankQuestionwrongAnswerScore;

	@Column(name = "bank_question_category_minutes")
	private int bankQuestionCategoryMinutes;

	public long getBankQuestionCategoryId() {
		return bankQuestionCategoryId;
	}

	public void setBankQuestionCategoryId(long bankQuestionCategoryId) {
		this.bankQuestionCategoryId = bankQuestionCategoryId;
	}

	public long getBankExamId() {
		return bankExamId;
	}

	public void setBankExamId(long bankExamId) {
		this.bankExamId = bankExamId;
	}

	public long getQuestionCategoryId() {
		return QuestionCategoryId;
	}

	public void setQuestionCategoryId(long questionCategoryId) {
		QuestionCategoryId = questionCategoryId;
	}

	public float getBankQuestionCorrectAnswerScore() {
		return bankQuestionCorrectAnswerScore;
	}

	public void setBankQuestionCorrectAnswerScore(float bankQuestionCorrectAnswerScore) {
		this.bankQuestionCorrectAnswerScore = bankQuestionCorrectAnswerScore;
	}

	public float getBankQuestionwrongAnswerScore() {
		return bankQuestionwrongAnswerScore;
	}

	public void setBankQuestionwrongAnswerScore(float bankQuestionwrongAnswerScore) {
		this.bankQuestionwrongAnswerScore = bankQuestionwrongAnswerScore;
	}

	public int getBankQuestionCategoryMinutes() {
		return bankQuestionCategoryMinutes;
	}

	public void setBankQuestionCategoryMinutes(int bankQuestionCategoryMinutes) {
		this.bankQuestionCategoryMinutes = bankQuestionCategoryMinutes;
	}

}
