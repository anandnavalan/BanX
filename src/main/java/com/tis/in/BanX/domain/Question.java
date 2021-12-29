package com.tis.in.BanX.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.GroupSequence;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "q_question")
@GroupSequence(value = { Question.class })
public class Question {

	@Id
	@Column(name = "question_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long questionId;

	@Column(name = "question_category_id")
	@Min(value = 0, message = "QUESTION_CATEGORY_ID_NOT_VALID")
	private Long questionCategoryId;

	@Column(name = "question_subcategory_id")
	@Min(value = 0, message = "QUESTION_SUBCATEGORY_ID_NOT_VALID")
	private Long questionSubCategoryId;

	@Column(name = "question_type_id")
	@Min(value = 0, message = "QUESTION_TYPE_ID_NOT_VALID")
	private Long questionTypeId;

	@Column(name = "question_sequence_no")
	private Long questionSequenceNo;

	@Column(name = "question_name")
	@NotBlank(message = "QUESTION_NAME_NOT_BLANK")
	private String questionName;

//	@OneToMany(mappedBy = "question", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JsonManagedReference
//	private Set<QuestionOption> questionOptions = new HashSet<>();

	@Column(name = "question_option_a")
	@NotBlank(message = "QUESTION_OPTION_A_NOT_BLANK")
	private String questionOptionA;

	@Column(name = "question_option_b")
	@NotBlank(message = "QUESTION_OPTION_B_NOT_BLANK")
	private String questionOptionB;

	@Column(name = "question_option_c")
	@NotBlank(message = "QUESTION_OPTION_C_NOT_BLANK")
	private String questionOptionC;

	@Column(name = "question_option_d")
	@NotBlank(message = "QUESTION_OPTION_D_NOT_BLANK")
	private String questionOptionD;

	@Column(name = "question_option_e")
	@NotBlank(message = "QUESTION_OPTION_E_NOT_BLANK")
	private String questionOptionE;

	@Column(name = "question_correct_answer")
	@NotBlank(message = "QUESTION_CORRECT_ANSWER_NOT_BLANK")
	private String questionCorrectAnswer;

	@Column(name = "question_correct_option")
	private String questionCorrectOption;

	@Column(name = "question_notes")
	private String questionNotes;

	@Column(name = "question_references")
	private String questionReferences;

	@Column(name = "question_rating")
	private Integer questionRating;

	@Column(name = "question_status")
	private Long questionStatus;

	@Embedded
	private AuditInfo auditInfo;

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
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

	public Long getQuestionTypeId() {
		return questionTypeId;
	}

	public void setQuestionTypeId(Long questionTypeId) {
		this.questionTypeId = questionTypeId;
	}

	public Long getQuestionSequenceNo() {
		return questionSequenceNo;
	}

	public void setQuestionSequenceNo(Long questionSequenceNo) {
		this.questionSequenceNo = questionSequenceNo;
	}

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public String getQuestionOptionA() {
		return questionOptionA;
	}

	public void setQuestionOptionA(String questionOptionA) {
		this.questionOptionA = questionOptionA;
	}

	public String getQuestionOptionB() {
		return questionOptionB;
	}

	public void setQuestionOptionB(String questionOptionB) {
		this.questionOptionB = questionOptionB;
	}

	public String getQuestionOptionC() {
		return questionOptionC;
	}

	public void setQuestionOptionC(String questionOptionC) {
		this.questionOptionC = questionOptionC;
	}

	public String getQuestionOptionD() {
		return questionOptionD;
	}

	public void setQuestionOptionD(String questionOptionD) {
		this.questionOptionD = questionOptionD;
	}

	public String getQuestionOptionE() {
		return questionOptionE;
	}

	public void setQuestionOptionE(String questionOptionE) {
		this.questionOptionE = questionOptionE;
	}

	public String getQuestionCorrectAnswer() {
		return questionCorrectAnswer;
	}

	public void setQuestionCorrectAnswer(String questionCorrectAnswer) {
		this.questionCorrectAnswer = questionCorrectAnswer;
	}

	public String getQuestionCorrectOption() {
		return questionCorrectOption;
	}

	public void setQuestionCorrectOption(String questionCorrectOption) {
		this.questionCorrectOption = questionCorrectOption;
	}

	public String getQuestionNotes() {
		return questionNotes;
	}

	public void setQuestionNotes(String questionNotes) {
		this.questionNotes = questionNotes;
	}

	public String getQuestionReferences() {
		return questionReferences;
	}

	public void setQuestionReferences(String questionReferences) {
		this.questionReferences = questionReferences;
	}

	public Integer getQuestionRating() {
		return questionRating;
	}

	public void setQuestionRating(Integer questionRating) {
		this.questionRating = questionRating;
	}

	public Long getQuestionStatus() {
		return questionStatus;
	}

	public void setQuestionStatus(Long questionStatus) {
		this.questionStatus = questionStatus;
	}

	public AuditInfo getAuditInfo() {
		return auditInfo;
	}

	public void setAuditInfo(AuditInfo auditInfo) {
		this.auditInfo = auditInfo;
	}

//	public Set<QuestionOption> getQuestionOptions() {
//		return questionOptions;
//	}
//
//	public void setQuestionOptions(Set<QuestionOption> questionOptions) {
//		this.questionOptions = questionOptions;
//
//		for (QuestionOption questionOption : questionOptions) {
//			questionOption.setQuestion(this);
//		}
//	}

}
