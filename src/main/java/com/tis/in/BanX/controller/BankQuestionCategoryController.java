package com.tis.in.BanX.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tis.in.BanX.common.CommonMessageConstants;
import com.tis.in.BanX.common.ErrorConstants;
import com.tis.in.BanX.common.ResponseBuilder;
import com.tis.in.BanX.common.Utility;
import com.tis.in.BanX.domain.AuditInfo;
import com.tis.in.BanX.domain.BankQuestionCategory;
import com.tis.in.BanX.domain.QuestionCategory;
import com.tis.in.BanX.exception.model.ResourceCreationException;
import com.tis.in.BanX.exception.model.ResourceNotFoundException;
import com.tis.in.BanX.handler.ResponseHandler;
import com.tis.in.BanX.service.BankQuestionCategoryService;

@RestController
public class BankQuestionCategoryController {

	@Autowired
	BankQuestionCategoryService bankQuestionCategoryService;

	@RequestMapping(value = "/createbankquestioncategory", name = "createBankQuestionCategory", method = RequestMethod.POST)
	private ResponseEntity<ResponseBuilder> createBankQuestionCategory(
			@RequestBody @Valid BankQuestionCategory bankQuestionCategory) throws ResourceCreationException {

		Optional<BankQuestionCategory> optionalBankQuestionCategory = bankQuestionCategoryService
				.getBankQuestionCategory(bankQuestionCategory.getBankExamId(),
						bankQuestionCategory.getQuestionCategoryId());

		if (optionalBankQuestionCategory.isPresent()) {
			throw new ResourceCreationException(ErrorConstants.ERROR_BANK_QUESTION_CATEGORY_EXISTS);
		} else {
			AuditInfo auditInfo = new AuditInfo();

			auditInfo.setCreatedBy("system");
			auditInfo.setCreatedDate(Utility.getSQLDate());
			auditInfo.setModifiedBy("system");
			auditInfo.setModifiedDate(Utility.getSQLDate());

			bankQuestionCategory.setAuditInfo(auditInfo);
			BankQuestionCategory createBankQuestionCategory = bankQuestionCategoryService
					.addOrUpdateBankQuestionCategory(bankQuestionCategory);

			ResponseBuilder builder = Utility.responseBuilder(
					Utility.getLocalizedMessage(CommonMessageConstants.SUCCESS_BANK_QUESTION_CATEGORY_CREATION),
					HttpStatus.CREATED.value());

			return new ResponseEntity<>(builder, HttpStatus.CREATED);
		}

	}


	@RequestMapping(value = "/updatebankquestioncategory", name = "updateBankQuestionCategory", method = RequestMethod.PUT)
	private ResponseEntity<Object> updateBankQuestionCategory(
			@RequestBody @Valid BankQuestionCategory bankQuestionCategory) throws ResourceNotFoundException {

		Optional<BankQuestionCategory> optionalBankQuestionCategory = bankQuestionCategoryService
				.getBankQuestionCategory(bankQuestionCategory.getBankQuestionCategoryId());

		if (optionalBankQuestionCategory.isPresent()) {
			AuditInfo auditInfo = new AuditInfo();
			auditInfo.setModifiedBy("system");
			auditInfo.setModifiedDate(Utility.getSQLDate());

			bankQuestionCategory.setAuditInfo(auditInfo);
			bankQuestionCategory = bankQuestionCategoryService.addOrUpdateBankQuestionCategory(bankQuestionCategory);

			ResponseBuilder builder = Utility.responseBuilder(
					Utility.getLocalizedMessage(CommonMessageConstants.SUCCESS_BANK_QUESTION_CATEGORY_UPDATION),
					HttpStatus.CREATED.value());

			return new ResponseEntity<>(builder, HttpStatus.CREATED);
		} else {
			throw new ResourceNotFoundException(ErrorConstants.ERROR_BANK_QUESTION_CATEGORY_NOT_EXISTS);
		}
	}

	@RequestMapping(value = "/getbankquestioncategories", name = "getBankQuestionCategories", method = RequestMethod.GET)
	public ResponseEntity<Object> getBankQuestionCategory() {

		List<BankQuestionCategory> bankQuestionCategorys = bankQuestionCategoryService.getAllBankQuestionCategory();
		return ResponseHandler.generateResponse("BankQuestionCategory Retrieved Successfully", HttpStatus.OK,
				bankQuestionCategorys);
	}

}
