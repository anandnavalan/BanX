package com.tis.in.BanX.model;

public class QuestionsResponse {

	private Long questionId;
	private Long questionCategoryId;
	private Long questionSubCategoryId;
	private Long questionTypeId;
	private String questionName;

	public QuestionsResponse(ResponseBuilder responseBuilder) {
		this.questionId = responseBuilder.questionId;
		this.questionCategoryId = responseBuilder.questionCategoryId;
		this.questionSubCategoryId = responseBuilder.questionSubCategoryId;
		this.questionTypeId = responseBuilder.questionTypeId;
		this.questionName = responseBuilder.questionName;
	}

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

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public static class ResponseBuilder {

		private Long questionId;
		private Long questionCategoryId;
		private Long questionSubCategoryId;
		private Long questionTypeId;
		private String questionName;
		
		public ResponseBuilder(Long questionId) {
			this.questionId = questionId;
		}

		public ResponseBuilder questionCategoryId(Long questionCategoryId) {
			this.questionCategoryId = questionCategoryId;
			return this;
		}
		
		public ResponseBuilder questionSubCategoryId(Long questionSubCategoryId) {
			this.questionSubCategoryId = questionSubCategoryId;
			return this;
		}
		
		public ResponseBuilder questionTypeId(Long questionTypeId) {
			this.questionTypeId = questionTypeId;
			return this;
		}
		
		public ResponseBuilder questionName(String questionName) {
			this.questionName = questionName;
			return this;
		}

		// Return the finally consrcuted User object
		public QuestionsResponse build() {
			QuestionsResponse question = new QuestionsResponse(this);
//			validateUserObject(user);
			return question;
		}

		private void validateUserObject(QuestionsResponse question) {
			// Do some basic validations to check
			// if user object does not break any assumption of system
		}
	}
}
