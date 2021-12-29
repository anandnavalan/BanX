package com.tis.in.BanX.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tis.in.BanX.common.CommonMessageConstants;
import com.tis.in.BanX.common.ErrorConstants;
import com.tis.in.BanX.common.ResponseBuilder;
import com.tis.in.BanX.common.Utility;
import com.tis.in.BanX.domain.AuditInfo;
import com.tis.in.BanX.domain.Question;
import com.tis.in.BanX.domain.QuestionCategory;
import com.tis.in.BanX.exception.model.ResourceCreationException;
import com.tis.in.BanX.exception.model.ResourceNotFoundException;
import com.tis.in.BanX.handler.ResponseHandler;
import com.tis.in.BanX.model.QuestionsResponse;
import com.tis.in.BanX.service.QuestionService;

@RestController
public class QuestionController {

	@Autowired
	QuestionService questionService;

	@RequestMapping(value = "/createquestion", name = "createQuestion", method = RequestMethod.POST)
	private ResponseEntity<ResponseBuilder> createQuestion(Principal principal,
			@RequestBody @Valid Question question)
			throws ResourceCreationException {
		Optional<Question> optionalQuestion = questionService.getQuestion(question.getQuestionId());
		if (optionalQuestion.isPresent()) {
			throw new ResourceCreationException(ErrorConstants.ERROR_QUESTION_EXISTS);
		} else {

			if (!isValidOptions(question.getQuestionOptionA().trim(), question.getQuestionOptionB().trim(),
					question.getQuestionOptionC().trim(), question.getQuestionOptionD().trim(),
					question.getQuestionOptionE().trim())) {
				throw new ResourceCreationException(ErrorConstants.ERROR_INVALID_QUESTION_OPTIONS);
			} else {
				AuditInfo auditInfo = new AuditInfo();

				auditInfo.setCreatedBy(principal.getName());
				auditInfo.setCreatedDate(Utility.getSQLDate());
				auditInfo.setModifiedBy(principal.getName());
				auditInfo.setModifiedDate(Utility.getSQLDate());

				question.setAuditInfo(auditInfo);
				Question createQuestion = questionService.addOrUpdateQuestion(question);
//
				ResponseBuilder builder = Utility.responseBuilder(
						Utility.getLocalizedMessage(CommonMessageConstants.SUCCESS_QUESTION_CREATION),
						HttpStatus.CREATED.value());

				return new ResponseEntity<>(builder, HttpStatus.CREATED);
			}

		}

	}

	private boolean isValidOptions(String questionOptionA, String questionOptionB, String questionOptionC,
			String questionOptionD, String questionOptionE) {

		if (questionOptionA.equalsIgnoreCase(questionOptionB) || questionOptionA.equalsIgnoreCase(questionOptionC)
				|| questionOptionA.equalsIgnoreCase(questionOptionD)
				|| questionOptionA.equalsIgnoreCase(questionOptionE)) {
			return false;
		}
		else if (questionOptionB.equalsIgnoreCase(questionOptionA) || questionOptionB.equalsIgnoreCase(questionOptionC)
				|| questionOptionB.equalsIgnoreCase(questionOptionD)
				|| questionOptionB.equalsIgnoreCase(questionOptionE)) {
			return false;
		}
		else if (questionOptionC.equalsIgnoreCase(questionOptionA) || questionOptionC.equalsIgnoreCase(questionOptionB)
				|| questionOptionC.equalsIgnoreCase(questionOptionD)
				|| questionOptionC.equalsIgnoreCase(questionOptionE)) {
			return false;
		}
		else if (questionOptionD.equalsIgnoreCase(questionOptionA) || questionOptionD.equalsIgnoreCase(questionOptionB)
				|| questionOptionD.equalsIgnoreCase(questionOptionC)
				|| questionOptionD.equalsIgnoreCase(questionOptionE)) {
			return false;
		}
		else if (questionOptionE.equalsIgnoreCase(questionOptionA) || questionOptionE.equalsIgnoreCase(questionOptionB)
				|| questionOptionE.equalsIgnoreCase(questionOptionC)
				|| questionOptionE.equalsIgnoreCase(questionOptionD)) {
			return false;
		}
		
		
		
		return true;
	}

	@RequestMapping(value = "/updatequestion", name = "updateQuestion", method = RequestMethod.PUT)
	private ResponseEntity<ResponseBuilder> updateQuestion(Principal principal,
			@RequestBody @Valid Question question)
			throws ResourceNotFoundException {
		Optional<Question> optionalQuestion = questionService.getQuestion(question.getQuestionId());
		if (optionalQuestion.isPresent()) {
			AuditInfo auditInfo = new AuditInfo();
			auditInfo.setCreatedBy(principal.getName());
			auditInfo.setCreatedDate(Utility.getSQLDate());
			auditInfo.setModifiedBy(principal.getName());
			auditInfo.setModifiedDate(Utility.getSQLDate());

			question.setAuditInfo(auditInfo);
			question = questionService.addOrUpdateQuestion(question);

			ResponseBuilder builder = Utility.responseBuilder(
					Utility.getLocalizedMessage(CommonMessageConstants.SUCCESS_QUESTION_UPDATION),
					HttpStatus.CREATED.value());

			return new ResponseEntity<>(builder, HttpStatus.CREATED);
		} else {
			throw new ResourceNotFoundException(ErrorConstants.ERROR_QUESTION_NOT_EXISTS);
		}
	}

	@RequestMapping(value = "/getquestions", name = "getQuestions", method = RequestMethod.GET)
	public ResponseEntity<Object> getQuestion() {
		List<Question> question = questionService.getAllQuestion();
		return ResponseHandler.generateResponse("Question Retrieved Successfully", HttpStatus.OK, question);
	}

	@RequestMapping(value = "/v2/getquestions", name = "getQuestions", method = RequestMethod.GET)
	public ResponseEntity<Object> getQuestions() {
		List<QuestionsResponse> question = questionService.getAllQuestions();
		return ResponseHandler.generateResponse("Question Retrieved Successfully", HttpStatus.OK, question);
	}

	
	@GetMapping(value = "/getquestion/{id}", name = "getQuestion")
	public ResponseEntity<Object> getidQuestionbyid(@PathVariable long id) {
		Optional<Question> question = questionService.getQuestion(id);
		return ResponseHandler.generateResponse("Question Retrieved Successfully", HttpStatus.OK, question.get());
	}

}
