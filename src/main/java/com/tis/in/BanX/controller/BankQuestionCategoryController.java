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

import com.tis.in.BanX.domain.BankQuestionCategory;
import com.tis.in.BanX.handler.ResponseHandler;
import com.tis.in.BanX.service.BankQuestionCategoryService;

@RestController
public class BankQuestionCategoryController {

	@Autowired
	BankQuestionCategoryService bankQuestionCategoryService;

	@RequestMapping(value = "/createbankquestioncategory", name = "createBankQuestionCategory", method = RequestMethod.POST)
	private ResponseEntity<Object> createBankQuestionCategory(
			@RequestBody @Valid BankQuestionCategory bankQuestionCategory) {

		Optional<BankQuestionCategory> optionalBankQuestionCategory = bankQuestionCategoryService
				.getBankQuestionCategory(bankQuestionCategory.getBankExamId(),
						bankQuestionCategory.getQuestionCategoryId());

		if (optionalBankQuestionCategory.isPresent()) {
			return ResponseHandler.generateResponse("BankQuestionCategory already exists in our system",
					HttpStatus.CONFLICT);

		} else {
			BankQuestionCategory createBankQuestionCategory = bankQuestionCategoryService
					.addOrUpdateBankQuestionCategory(bankQuestionCategory);
			return ResponseHandler.generateResponse("BankQuestionCategory Created Successfully", HttpStatus.CREATED,
					createBankQuestionCategory);
		}

	}

	@RequestMapping(value = "/updatebankquestioncategory", name = "updateBankQuestionCategory", method = RequestMethod.PUT)
	private ResponseEntity<Object> updateBankQuestionCategory(
			@RequestBody @Valid BankQuestionCategory bankQuestionCategory) {

		Optional<BankQuestionCategory> optionalBankQuestionCategory = bankQuestionCategoryService
				.getBankQuestionCategory(bankQuestionCategory.getBankQuestionCategoryId());

		if (optionalBankQuestionCategory.isPresent()) {
			return ResponseHandler.generateResponse("BankQuestionCategory not exists in our system",
					HttpStatus.NOT_FOUND);

		} else {
			BankQuestionCategory createBankQuestionCategory = bankQuestionCategoryService
					.addOrUpdateBankQuestionCategory(bankQuestionCategory);
			return ResponseHandler.generateResponse("BankQuestionCategory updated Successfully", HttpStatus.OK,
					createBankQuestionCategory);
		}
	}

	@RequestMapping(value = "/getbankquestioncategory", name = "getBankQuestionCategorys", method = RequestMethod.GET)
	public ResponseEntity<Object> getBankQuestionCategory() {

		List<BankQuestionCategory> bankQuestionCategorys = bankQuestionCategoryService.getAllBankQuestionCategory();
		return ResponseHandler.generateResponse("BankQuestionCategory Retrieved Successfully", HttpStatus.OK,
				bankQuestionCategorys);
	}

}
