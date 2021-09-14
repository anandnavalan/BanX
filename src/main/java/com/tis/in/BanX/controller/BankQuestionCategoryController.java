package com.tis.in.BanX.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankQuestionCategoryController {
	
//	@Autowired
//	BankQuestionCategory bankQuestionCategoryService;
	
//	@RequestMapping(value = "/createbankquestioncategory", name = "createBankQuestionCategory", method = RequestMethod.POST)
//	private ResponseEntity<Object> createBankQuestionCategory(@RequestBody @Valid BankQuestionCategory bankQuestionCategory){
//		Optional<BankQuestionCategory> optionalBankQuestionCategory= bankQuestionCategoryService.getBankQuestionCategory();
//		if (optionalBankQuestionCategory.isPresent()) {
//			return ResponseHandler.generateResponse("", HttpStatus.CONFLICT);
//		
//		
//		} else {
//			bankQuestionCategory = bankQuestionCategoryService.addOrUpdateBankQuestionCategory(bankQuestionCategory);
//			return ResponseHandler.generateResponse("", HttpStatus.CREATED, bankQuestionCategory);
//
//		
//		@RequestMapping(value = "/updatebankquestioncategory", name = "updateBankQuestionCategory", method = RequestMethod.PUT)
//		private ResponseEntity<Object> updateBankQuestionCategory(@RequestBody @Valid BankExam bankExam){
//			Optional<BankExam> optionalBankExam = bankExamService.getBankExam(bankExam.getBankExamId());
//			if (optionalBankExam.isPresent()) {
//				return ResponseHandler.generateResponse(".", HttpStatus.NOT_FOUND);
//			
//			
//			} else {
//				bankExam = bankExamService.addOrUpdateBankExam(bankExam);
//				return ResponseHandler.generateResponse("", HttpStatus.OK, bankExam);
//
//			}
//		}
//
//	}

}
