package com.tis.in.BanX.domain;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "q_assessment_question")
public class AssessmentQuestion_Select {

	@Id
	@Column(name = "assessment_question_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long assessmentQuestionId;

	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "question_id", referencedColumnName = "question_id", nullable = false, insertable = true, foreignKey = @javax.persistence.ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private Question question;

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
	private Assessment_Select assessment;

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

	public long getstatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public Assessment_Select getAssessment() {
		return assessment;
	}

	public void setAssessment(Assessment_Select assessment) {
		this.assessment = assessment;
	}

}
