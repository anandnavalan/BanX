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
import com.tis.in.BanX.domain.QuestionCategory;
import com.tis.in.BanX.domain.QuestionType;
import com.tis.in.BanX.exception.model.ResourceCreationException;
import com.tis.in.BanX.exception.model.ResourceNotFoundException;
import com.tis.in.BanX.handler.ResponseHandler;
import com.tis.in.BanX.service.QuestionTypeService;

@RestController
public class QuestionTypeController {

	@Autowired
	QuestionTypeService questionTypeService;

	@RequestMapping(value = "/createquestiontype", name = "createQuestionType", method = RequestMethod.POST)
	private ResponseEntity<ResponseBuilder> createQuestionType(Principal principal,
			@RequestBody @Valid QuestionType questionType) throws ResourceCreationException {
		Optional<QuestionType> optionalQuestionType = questionTypeService
				.getQuestionType(questionType.getQuestionTypeName());
		if (optionalQuestionType.isPresent()) {
			throw new ResourceCreationException(ErrorConstants.ERROR_QUESTION_TYPE_EXISTS);
		} else {
			AuditInfo auditInfo = new AuditInfo();

			auditInfo.setCreatedBy(principal.getName());
			auditInfo.setCreatedDate(Utility.getSQLDate());
			auditInfo.setModifiedBy(principal.getName());
			auditInfo.setModifiedDate(Utility.getSQLDate());

			questionType.setAuditInfo(auditInfo);
			QuestionType createQuestionType = questionTypeService.addOrUpdateQuestionType(questionType);

			ResponseBuilder builder = Utility.responseBuilder(
					Utility.getLocalizedMessage(CommonMessageConstants.SUCCESS_QUESTION_TYPE_CREATION),
					HttpStatus.CREATED.value());

			return new ResponseEntity<>(builder, HttpStatus.CREATED);
		}

	}

	@RequestMapping(value = "/updatequestiontype", name = "updateQuestionType", method = RequestMethod.PUT)
	private ResponseEntity<ResponseBuilder> updateQuestionType(Principal principal,
			@RequestBody @Valid QuestionType questionType)
			throws ResourceNotFoundException, ResourceCreationException {

		Optional<QuestionType> optionalQuestionType = questionTypeService
				.getQuestionType(questionType.getQuestionTypeId());

		if (optionalQuestionType.isPresent()) {

			if (!optionalQuestionType.get().getQuestionTypeName()
					.equals(questionType.getQuestionTypeName().trim())) {
				Optional<QuestionType> optionalQuestionTypeName = questionTypeService
						.getQuestionType(questionType.getQuestionTypeName().trim());

				if (optionalQuestionTypeName.isPresent()) {
					throw new ResourceCreationException(ErrorConstants.ERROR_QUESTION_TYPE_EXISTS);
				} else {
					return addOrUpdateQuestionType(principal, optionalQuestionType, questionType);
				}
			} else {
				return addOrUpdateQuestionType(principal, optionalQuestionType, questionType);
			}

		} else {
			throw new ResourceNotFoundException(ErrorConstants.ERROR_QUESTION_TYPE_NOT_EXISTS);
		}

	}

	private ResponseEntity<ResponseBuilder> addOrUpdateQuestionType(Principal principal,
			Optional<QuestionType> optionalQuestionType, QuestionType questionType) {
		AuditInfo auditInfo = optionalQuestionType.get().getAuditInfo();
		auditInfo.setModifiedBy(principal.getName());
		auditInfo.setModifiedDate(Utility.getSQLDate());

		questionType.setAuditInfo(auditInfo);
		questionType = questionTypeService.addOrUpdateQuestionType(questionType);
		ResponseBuilder builder = Utility.responseBuilder(
				Utility.getLocalizedMessage(CommonMessageConstants.SUCCESS_QUESTION_TYPE_UPDATION),
				HttpStatus.CREATED.value());

		return new ResponseEntity<>(builder, HttpStatus.CREATED);

	}


	@RequestMapping(value = "/getquestiontypes", name = "getquestiontypes", method = RequestMethod.GET)
	public ResponseEntity<Object> getQuestionType() {

		List<QuestionType> questionTypes = questionTypeService.getAllQuestionType();
		return ResponseHandler.generateResponse("QuestionType Retrieved Successfully", HttpStatus.OK, questionTypes);
	}

	@GetMapping(value = "/getquestiontype/{id}", name = "getQuestionType")
	public ResponseEntity<Object> getidQuestionTypebyid(@PathVariable long id) {
		Optional<QuestionType> questionType = questionTypeService.getQuestionType(id);
		return ResponseHandler.generateResponse("QuestionType Retrieved Successfully", HttpStatus.OK,
				questionType.get());
	}

}
