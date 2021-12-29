package com.tis.in.BanX.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "q_exam_pattern")
public class ExamPattern {

	@Id
	@Column(name = "exam_pattern_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "q_exam_pattern_sequence")
	@SequenceGenerator(name = "q_exam_pattern_sequence", sequenceName = "q_exam_pattern_sequence", allocationSize = 1)
	private long examPatternId;

	@Column(name = "question_category_id")
	private long questionCategoryId;

	@Column(name = "question_sub_category_id")
	private long questionSubCategoryId;

	@Column(name = "no_of_questions")
	private int noOfQuestions;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "exam_id", nullable = false)
	@JsonBackReference
	private Exam exam;

	public long getExamPatternId() {
		return examPatternId;
	}

	public void setExamPatternId(long examPatternId) {
		this.examPatternId = examPatternId;
	}

	public long getQuestionCategoryId() {
		return questionCategoryId;
	}

	public void setQuestionCategoryId(long questionCategoryId) {
		this.questionCategoryId = questionCategoryId;
	}

	public long getQuestionSubCategoryId() {
		return questionSubCategoryId;
	}

	public void setQuestionSubCategoryId(long questionSubCategoryId) {
		this.questionSubCategoryId = questionSubCategoryId;
	}

	public int getNoOfQuestions() {
		return noOfQuestions;
	}

	public void setNoOfQuestions(int noOfQuestions) {
		this.noOfQuestions = noOfQuestions;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

}
