package com.tis.in.BanX.controller;

import java.util.List;
import java.util.Optional;

import com.tis.in.BanX.domain.QuestionComplexity;
import com.tis.in.BanX.handler.ResponseHandler;
import com.tis.in.BanX.service.QuestionComplexityService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionComplexityController {

	@Autowired
	QuestionComplexityService questionComplexityService;

	@RequestMapping(name = "/createquestioncomplexity", value = "CreateQuestionComplexity", method = RequestMethod.POST)
	private ResponseEntity<Object> CreateQuestionComplexity(@RequestBody @Valid QuestionComplexity questionComplexity) {
		Optional<QuestionComplexity> optionalQuestionComplexity = questionComplexityService
				.getQuestionComplexity(questionComplexity.getQuestionComplexityId());
		if (optionalQuestionComplexity.isPresent()) {
			return ResponseHandler.generateResponse("QuestionComplexity already existing.", HttpStatus.CONFLICT);

		} else {
			questionComplexity = questionComplexityService.addOrUpdateQuestionComplexity(questionComplexity);
			return ResponseHandler.generateResponse("QuestionComplexity Created Successfully", HttpStatus.CREATED,
					questionComplexity);

		}

	}

	@RequestMapping(value = "/updatequestioncomplexity", name = "updateQuestionComplexity", method = RequestMethod.PUT)
	private ResponseEntity<Object> updateUser(@RequestBody @Valid QuestionComplexity questionComplexity) {
		Optional<QuestionComplexity> optionalQuestionComplexity = questionComplexityService
				.getQuestionComplexity(questionComplexity.getQuestionComplexityId());
		if (optionalQuestionComplexity.isPresent()) {
			return ResponseHandler.generateResponse("QuestionComplexity not exists in your system",
					HttpStatus.NOT_FOUND);

		} else {
			questionComplexity = questionComplexityService.addOrUpdateQuestionComplexity(questionComplexity);
			return ResponseHandler.generateResponse("QuestionComplexity Updated Successfully", HttpStatus.OK,
					questionComplexity);

		}
	}

	@RequestMapping(value = "/getquestioncomplexity", name = "getQuestionComplexitys", method = RequestMethod.GET)
	public ResponseEntity<Object> getQuestionComplexity() {
		List<QuestionComplexity> questionComplexitys = questionComplexityService.getAllQuestionComplexity();
		return ResponseHandler.generateResponse("QuestionComplexity Retrieved Successfully", HttpStatus.OK,
				questionComplexitys);
	}

	@RequestMapping(value = "/getquestioncomplexity/{id}", name = "getidQuestionComplexity", method = RequestMethod.GET)
	public ResponseEntity<Object> getidQuestionComplexitybyid(@PathVariable long id) {
		Optional<QuestionComplexity> questionComplexity = questionComplexityService.getQuestionComplexity(id);
		return ResponseHandler.generateResponse("QuestionComplexity Retrieved Successfully", HttpStatus.OK,
				questionComplexity.get());
	}
}
