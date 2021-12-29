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
import com.tis.in.BanX.domain.QuestionSubCategory;
import com.tis.in.BanX.exception.model.ResourceCreationException;
import com.tis.in.BanX.exception.model.ResourceNotFoundException;
import com.tis.in.BanX.handler.ResponseHandler;
import com.tis.in.BanX.service.QuestionSubCategoryService;

@RestController
public class QuestionSubCategoryController {

	@Autowired
	QuestionSubCategoryService questionSubCategoryService;

	@RequestMapping(value = "/createquestionsubcategory", name = "createQuestionSubCategory", method = RequestMethod.POST)
	private ResponseEntity<ResponseBuilder> createQuestionSubCategory(Principal principal,
			@RequestBody @Valid QuestionSubCategory questionSubCategory) throws ResourceCreationException {

		Optional<QuestionSubCategory> optionalQuestionSubCategory = questionSubCategoryService
				.getQuestionSubCategory(questionSubCategory.getQuestionSubCategoryName().trim());

		if (optionalQuestionSubCategory.isPresent()) {
			throw new ResourceCreationException(ErrorConstants.ERROR_QUESTION_SUB_CATEGORY_EXISTS);
		} else {

			AuditInfo auditInfo = new AuditInfo();

			auditInfo.setCreatedBy(principal.getName());
			auditInfo.setCreatedDate(Utility.getSQLDate());
			auditInfo.setModifiedBy(principal.getName());
			auditInfo.setModifiedDate(Utility.getSQLDate());

			questionSubCategory.setAuditInfo(auditInfo);
			QuestionSubCategory createQuestionSubCategory = questionSubCategoryService
					.addOrUpdateQuestionSubCategory(questionSubCategory);

			ResponseBuilder builder = Utility.responseBuilder(
					Utility.getLocalizedMessage(CommonMessageConstants.SUCCESS_QUESTION_SUB_CATEGORY_CREATION),
					HttpStatus.CREATED.value());

			return new ResponseEntity<>(builder, HttpStatus.CREATED);
		}

	}

	@RequestMapping(value = "/updatequestionsubcategory", name = "updateQuestionSubCategory", method = RequestMethod.PUT)
	private ResponseEntity<ResponseBuilder> updateQuestionSubCategory(Principal principal,
			@RequestBody @Valid QuestionSubCategory questionSubCategory)
			throws ResourceNotFoundException, ResourceCreationException {

		Optional<QuestionSubCategory> optionalQuestionSubCategory = questionSubCategoryService
				.getQuestionSubCategory(questionSubCategory.getQuestionSubCategoryId());

		if (optionalQuestionSubCategory.isPresent()) {

			if (!optionalQuestionSubCategory.get().getQuestionSubCategoryName()
					.equals(questionSubCategory.getQuestionSubCategoryName().trim())) {
				Optional<QuestionSubCategory> optionalQuestionSubCategoryName = questionSubCategoryService
						.getQuestionSubCategory(questionSubCategory.getQuestionSubCategoryName().trim());

				if (optionalQuestionSubCategoryName.isPresent()) {
					throw new ResourceCreationException(ErrorConstants.ERROR_QUESTION_SUB_CATEGORY_EXISTS);
				} else {
					return addOrUpdateQuestionSubCategory(principal, optionalQuestionSubCategory, questionSubCategory);

				}
			} else {
				return addOrUpdateQuestionSubCategory(principal, optionalQuestionSubCategory, questionSubCategory);
			}

		} else {
			throw new ResourceNotFoundException(ErrorConstants.ERROR_QUESTION_SUB_CATEGORY_NOT_EXISTS);
		}

	}

	private ResponseEntity<ResponseBuilder> addOrUpdateQuestionSubCategory(Principal principal,
			Optional<QuestionSubCategory> optionalQuestionSubCategory, @Valid QuestionSubCategory questionSubCategory) {
		AuditInfo auditInfo = optionalQuestionSubCategory.get().getAuditInfo();
		auditInfo.setModifiedBy(principal.getName());
		auditInfo.setModifiedDate(Utility.getSQLDate());

		questionSubCategory.setAuditInfo(auditInfo);
		questionSubCategory = questionSubCategoryService.addOrUpdateQuestionSubCategory(questionSubCategory);

		ResponseBuilder builder = Utility.responseBuilder(
				Utility.getLocalizedMessage(CommonMessageConstants.SUCCESS_QUESTION_SUB_CATEGORY_UPDATION),
				HttpStatus.CREATED.value());

		return new ResponseEntity<>(builder, HttpStatus.CREATED);

	}

	@RequestMapping(value = "/getquestionsubcategories", name = "getquestionsubcategories", method = RequestMethod.GET)
	public ResponseEntity<Object> getQuestionCategory() {

		List<QuestionSubCategory> questionSubCategorys = questionSubCategoryService.getAllQuestionSubCategory();
		return ResponseHandler.generateResponse("QuestionSubCategory Retrieved Successfully", HttpStatus.OK,
				questionSubCategorys);
	}

	@GetMapping(value = "/getquestionsubcategory/{id}", name = "getQuestionSubCategory")
	public ResponseEntity<Object> getidQuestionSubCategorybyid(@PathVariable long id) {
		Optional<QuestionSubCategory> questionSubCategory = questionSubCategoryService.getQuestionSubCategory(id);
		return ResponseHandler.generateResponse("QuestionSubCategory Retrieved Successfully", HttpStatus.OK,
				questionSubCategory.get());
	}

}
