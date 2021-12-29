package com.tis.in.BanX.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tis.in.BanX.domain.Assessment;
import com.tis.in.BanX.domain.Question;

public class AssessmentQuestionResponse {

	private long assessmentQuestionId;

	private Question question;

	private String userAnswer;

	private String correctAnswer;

	private boolean isAnswered;

	private boolean isAnsweredCorrectly;

	private long status;

	@JsonBackReference
	private Assessment assessment;

	public long getAssessmentQuestionId() {
		return assessmentQuestionId;
	}

	public void setAssessmentQuestionId(long assessmentQuestionId) {
		this.assessmentQuestionId = assessmentQuestionId;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public boolean isAnswered() {
		return isAnswered;
	}

	public void setAnswered(boolean isAnswered) {
		this.isAnswered = isAnswered;
	}

	public boolean isAnsweredCorrectly() {
		return isAnsweredCorrectly;
	}

	public void setAnsweredCorrectly(boolean isAnsweredCorrectly) {
		this.isAnsweredCorrectly = isAnsweredCorrectly;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public Assessment getAssessment() {
		return assessment;
	}

	public void setAssessment(Assessment assessment) {
		this.assessment = assessment;
	}

}
