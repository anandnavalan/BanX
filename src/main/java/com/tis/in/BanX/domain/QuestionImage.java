package com.tis.in.BanX.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "q_question_images")
public class QuestionImage {

	@Id
	@Column(name = "question_image_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "q_question_images_sequence")
	@SequenceGenerator(name = "q_question_images_sequence", sequenceName = "q_question_images_sequence", allocationSize = 1)
	private long questionImageId;

	@Column(name = "question_Image_name")
	private String questionImageName;

	@Column(name = "question_Image_path")
	private String questionImagePath;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "question_id", nullable = false)
	@JsonBackReference
	private Question question;

	public long getQuestionImageId() {
		return questionImageId;
	}

	public void setQuestionImageId(long questionImageId) {
		this.questionImageId = questionImageId;
	}

	public String getQuestionImageName() {
		return questionImageName;
	}

	public void setQuestionImageName(String questionImageName) {
		this.questionImageName = questionImageName;
	}

	public String getQuestionImagePath() {
		return questionImagePath;
	}

	public void setQuestionImagePath(String questionImagePath) {
		this.questionImagePath = questionImagePath;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}
