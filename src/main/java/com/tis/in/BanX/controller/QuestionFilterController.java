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
import com.tis.in.BanX.domain.QuestionFilter;
import com.tis.in.BanX.exception.model.ResourceCreationException;
import com.tis.in.BanX.exception.model.ResourceNotFoundException;
import com.tis.in.BanX.handler.ResponseHandler;
import com.tis.in.BanX.service.QuestionFilterService;

@RestController
public class QuestionFilterController {

	@Autowired
	private QuestionFilterService questionFilterService;

	@RequestMapping(value = "/createquestionfilter", name = "createQuestionFilter", method = RequestMethod.POST)
	private ResponseEntity<ResponseBuilder> createQuestionFilter(Principal principal,
			@RequestBody @Valid QuestionFilter questionFilter) throws ResourceCreationException {
		Optional<QuestionFilter> optionalQuestionFilter = questionFilterService
				.getQuestionFilter(questionFilter.getQuestionFilterName().trim());
		if (optionalQuestionFilter.isPresent()) {
			throw new ResourceCreationException(ErrorConstants.ERROR_QUESTION_FILTER_EXISTS);
		} else {
			AuditInfo auditInfo = new AuditInfo();

			auditInfo.setCreatedBy(principal.getName());
			auditInfo.setCreatedDate(Utility.getSQLDate());
			auditInfo.setModifiedBy(principal.getName());
			auditInfo.setModifiedDate(Utility.getSQLDate());

			questionFilter.setAuditInfo(auditInfo);
			QuestionFilter createQuestionFilter = questionFilterService
					.addOrUpdateQuestionFilter(questionFilter);

			ResponseBuilder builder = Utility.responseBuilder(
					Utility.getLocalizedMessage(CommonMessageConstants.SUCCESS_QUESTION_FILTER_CREATION),
					HttpStatus.CREATED.value());

			return new ResponseEntity<>(builder, HttpStatus.CREATED);
		}

	}

	@RequestMapping(value = "/updatequestionfilter", name = "updateQuestionFilter", method = RequestMethod.PUT)
	private ResponseEntity<ResponseBuilder> updateQuestionFilter(Principal principal,
			@RequestBody @Valid QuestionFilter questionFilter)
			throws ResourceNotFoundException, ResourceCreationException {

		System.out.println(questionFilter.getQuestionFilterId());
		Optional<QuestionFilter> optionalQuestionFilter = questionFilterService
				.getQuestionFilter(questionFilter.getQuestionFilterId());

		if (optionalQuestionFilter.isPresent()) {

			if (!optionalQuestionFilter.get().getQuestionFilterName()
					.equals(questionFilter.getQuestionFilterName().trim())) {
				Optional<QuestionFilter> optionalQuestionFilterName = questionFilterService
						.getQuestionFilter(questionFilter.getQuestionFilterName().trim());

				if (optionalQuestionFilterName.isPresent()) {
					throw new ResourceCreationException(ErrorConstants.ERROR_QUESTION_FILTER_EXISTS);
				} else {
					return addOrUpdateQuestionFilter(principal, optionalQuestionFilter, questionFilter);
				}
			} else {
				return addOrUpdateQuestionFilter(principal, optionalQuestionFilter, questionFilter);
			}

		} else {
			throw new ResourceNotFoundException(ErrorConstants.ERROR_QUESTION_FILTER_NOT_EXISTS);
		}

	}

	private ResponseEntity<ResponseBuilder> addOrUpdateQuestionFilter(Principal principal,
			Optional<QuestionFilter> optionalQuestionFilter, QuestionFilter questionFilter) {
		AuditInfo auditInfo = optionalQuestionFilter.get().getAuditInfo();
		auditInfo.setModifiedBy(principal.getName());
		auditInfo.setModifiedDate(Utility.getSQLDate());

		questionFilter.setAuditInfo(auditInfo);
		questionFilter = questionFilterService.addOrUpdateQuestionFilter(questionFilter);
		ResponseBuilder builder = Utility.responseBuilder(
				Utility.getLocalizedMessage(CommonMessageConstants.SUCCESS_QUESTION_FILTER_UPDATION),
				HttpStatus.CREATED.value());

		return new ResponseEntity<>(builder, HttpStatus.CREATED);

	}

	@RequestMapping(value = "/getquestionfilters", name = "getquestionfilters", method = RequestMethod.GET)
	public ResponseEntity<Object> getQuestionFilter() {

		List<QuestionFilter> questionFilters = questionFilterService.getAllQuestionFilter();
		return ResponseHandler.generateResponse("QuestionFilter Retrieved Successfully", HttpStatus.OK,
				questionFilters);
	}

	@GetMapping(value = "/getquestionfilter/{id}", name = "getQuestionFilter")
	public ResponseEntity<Object> getidQuestionFilterbyid(@PathVariable long id) {
		Optional<QuestionFilter> questionFilter = questionFilterService.getQuestionFilter(id);
		return ResponseHandler.generateResponse("QuestionFilter Retrieved Successfully", HttpStatus.OK,
				questionFilter.get());
	}



}
