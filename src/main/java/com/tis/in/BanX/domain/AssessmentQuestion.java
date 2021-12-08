package com.tis.in.BanX.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "q_assessment_question")
public class AssessmentQuestion {

	@Id
	@Column(name = "assessment_question_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long assessmentQuestionId;

	@Column(name = "question_id")
	private long questionId;

	@Column(name = "user_answer")
	private String userAnswer;

	@Column(name = "correct_answer")
	private String correctAnswer;

	@Column(name = "is_answered")
	private boolean isAnswered;

	@Column(name = "is_answered_correctly")
	private boolean isAnsweredCorrectly;

	@Column(name = "status")
	private long status;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "assessment_id", nullable = false)
	@JsonBackReference
	private Assessment assessment;

	public long getAssessmentQuestionId() {
		return assessmentQuestionId;
	}

	public void setAssessmentQuestionId(long assessmentQuestionId) {
		this.assessmentQuestionId = assessmentQuestionId;
	}

//	public long getAssessmentId() {
//		return assessmentId;
//	}
//
//	public void setAssessmentId(long assessmentId) {
//		this.assessmentId = assessmentId;
//	}

	public long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
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

	public long getstatus() {
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
