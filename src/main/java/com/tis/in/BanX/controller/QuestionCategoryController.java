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
import com.tis.in.BanX.exception.model.ResourceCreationException;
import com.tis.in.BanX.exception.model.ResourceNotFoundException;
import com.tis.in.BanX.handler.ResponseHandler;
import com.tis.in.BanX.service.QuestionCategoryService;

@RestController
public class QuestionCategoryController {

	@Autowired
	private QuestionCategoryService questionCategoryService;

	@RequestMapping(value = "/createquestioncategory", name = "createQuestionCategory", method = RequestMethod.POST)
	private ResponseEntity<ResponseBuilder> createQuestionCategory(Principal principal,
			@RequestBody @Valid QuestionCategory questionCategory) throws ResourceCreationException {
		Optional<QuestionCategory> optionalQuestionCategory = questionCategoryService
				.getQuestionCategory(questionCategory.getQuestionCategoryName().trim());
		if (optionalQuestionCategory.isPresent()) {
			throw new ResourceCreationException(ErrorConstants.ERROR_QUESTION_CATEGORY_EXISTS);
		} else {
			AuditInfo auditInfo = new AuditInfo();

			auditInfo.setCreatedBy(principal.getName());
			auditInfo.setCreatedDate(Utility.getSQLDate());
			auditInfo.setModifiedBy(principal.getName());
			auditInfo.setModifiedDate(Utility.getSQLDate());

			questionCategory.setAuditInfo(auditInfo);
			QuestionCategory createQuestionCategory = questionCategoryService
					.addOrUpdateQuestionCategory(questionCategory);

			ResponseBuilder builder = Utility.responseBuilder(
					Utility.getLocalizedMessage(CommonMessageConstants.SUCCESS_QUESTION_CATEGORY_CREATION),
					HttpStatus.CREATED.value());

			return new ResponseEntity<>(builder, HttpStatus.CREATED);
		}

	}

	@RequestMapping(value = "/updatequestioncategory", name = "updateQuestionCategory", method = RequestMethod.PUT)
	private ResponseEntity<ResponseBuilder> updateQuestionCategory(Principal principal,
			@RequestBody @Valid QuestionCategory questionCategory)
			throws ResourceNotFoundException, ResourceCreationException {

		System.out.println(questionCategory.getQuestionCategoryId());
		Optional<QuestionCategory> optionalQuestionCategory = questionCategoryService
				.getQuestionCategory(questionCategory.getQuestionCategoryId());

		if (optionalQuestionCategory.isPresent()) {

			if (!optionalQuestionCategory.get().getQuestionCategoryName()
					.equals(questionCategory.getQuestionCategoryName().trim())) {
				Optional<QuestionCategory> optionalQuestionCategoryName = questionCategoryService
						.getQuestionCategory(questionCategory.getQuestionCategoryName().trim());

				if (optionalQuestionCategoryName.isPresent()) {
					throw new ResourceCreationException(ErrorConstants.ERROR_QUESTION_CATEGORY_EXISTS);
				} else {
					return addOrUpdateQuestionCategory(principal, optionalQuestionCategory, questionCategory);
				}
			} else {
				return addOrUpdateQuestionCategory(principal, optionalQuestionCategory, questionCategory);
			}

		} else {
			throw new ResourceNotFoundException(ErrorConstants.ERROR_QUESTION_CATEGORY_NOT_EXISTS);
		}

	}

	private ResponseEntity<ResponseBuilder> addOrUpdateQuestionCategory(Principal principal,
			Optional<QuestionCategory> optionalQuestionCategory, QuestionCategory questionCategory) {
		AuditInfo auditInfo = optionalQuestionCategory.get().getAuditInfo();
		auditInfo.setModifiedBy(principal.getName());
		auditInfo.setModifiedDate(Utility.getSQLDate());

		questionCategory.setAuditInfo(auditInfo);
		questionCategory = questionCategoryService.addOrUpdateQuestionCategory(questionCategory);
		ResponseBuilder builder = Utility.responseBuilder(
				Utility.getLocalizedMessage(CommonMessageConstants.SUCCESS_QUESTION_CATEGORY_UPDATION),
				HttpStatus.CREATED.value());

		return new ResponseEntity<>(builder, HttpStatus.CREATED);

	}

	@RequestMapping(value = "/getquestioncategories", name = "getquestioncategories", method = RequestMethod.GET)
	public ResponseEntity<Object> getQuestionCategory() {

		List<QuestionCategory> questionCategorys = questionCategoryService.getAllQuestionCategory();
		return ResponseHandler.generateResponse("QuestionCategory Retrieved Successfully", HttpStatus.OK,
				questionCategorys);
	}

	@GetMapping(value = "/getquestioncategory/{id}", name = "getQuestionCategory")
	public ResponseEntity<Object> getidQuestionCategorybyid(@PathVariable long id) {
		Optional<QuestionCategory> questionCategory = questionCategoryService.getQuestionCategory(id);
		return ResponseHandler.generateResponse("QuestionCategory Retrieved Successfully", HttpStatus.OK,
				questionCategory.get());
	}


}
