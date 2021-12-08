package com.tis.in.BanX.controller;

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
import com.tis.in.BanX.domain.Assessment;
import com.tis.in.BanX.domain.AuditInfo;
import com.tis.in.BanX.exception.model.ResourceCreationException;
import com.tis.in.BanX.exception.model.ResourceNotFoundException;
import com.tis.in.BanX.handler.ResponseHandler;
import com.tis.in.BanX.service.AssessmentService;

@RestController
public class AssessmentController {

	@Autowired
	AssessmentService assessmentService;

	@RequestMapping(value = "/createassessment", name = "CreateAssessment", method = RequestMethod.POST)
	private ResponseEntity<ResponseBuilder> createAssessment(@RequestBody @Valid Assessment assessment)
			throws ResourceCreationException {

		Optional<Assessment> optionalAssessment = assessmentService.getAssessment(assessment.getAssessmentId());

		Optional<Assessment> optionalAssessmentByBankExamIdAndUserId = assessmentService
				.getAssessment(assessment.getBankExamId(), assessment.getUserId());

		if (optionalAssessment.isPresent() || optionalAssessmentByBankExamIdAndUserId.isPresent()) {
			throw new ResourceCreationException(ErrorConstants.ERROR_ASSESSMENT_EXISTS);
		} else {

			AuditInfo auditInfo = new AuditInfo();

			auditInfo.setCreatedBy("system");
			auditInfo.setCreatedDate(Utility.getSQLDate());
			auditInfo.setModifiedBy("system");
			auditInfo.setModifiedDate(Utility.getSQLDate());

			assessment.setAuditInfo(auditInfo);

			Assessment createAssessment = assessmentService.addOrUpdateAssessment(assessment);

			ResponseBuilder builder = Utility.responseBuilder(
					Utility.getLocalizedMessage(CommonMessageConstants.SUCCESS_ASSESSMENT_CREATION),
					HttpStatus.CREATED.value());

			return new ResponseEntity<>(builder, HttpStatus.CREATED);

		}

	}

	@RequestMapping(value = "/updateassessment", name = "UpdateAssessment", method = RequestMethod.PUT)
	private ResponseEntity<ResponseBuilder> updateAssessment(@RequestBody @Valid Assessment assessment)
			throws ResourceNotFoundException, ResourceCreationException {
		Optional<Assessment> optionalAssessment = assessmentService.getAssessment(assessment.getAssessmentId());

		if (!optionalAssessment.isPresent()) {
			throw new ResourceCreationException(ErrorConstants.ERROR_ASSESSMENT_NOT_EXISTS);
		} else {
			AuditInfo auditInfo = new AuditInfo();
			auditInfo.setCreatedBy("system");
			auditInfo.setCreatedDate(Utility.getSQLDate());
			auditInfo.setModifiedBy("system");
			auditInfo.setModifiedDate(Utility.getSQLDate());

			assessment.setAuditInfo(auditInfo);

			assessment = assessmentService.addOrUpdateAssessment(assessment);
			ResponseBuilder builder = Utility.responseBuilder(
					Utility.getLocalizedMessage(CommonMessageConstants.SUCCESS_ASSESSMENT_UPDATION),
					HttpStatus.CREATED.value());

			return new ResponseEntity<>(builder, HttpStatus.CREATED);
		}

	}

	@RequestMapping(value = "/getassessments", name = "getAssessments", method = RequestMethod.GET)
	public ResponseEntity<Object> getAssessment() {
		List<Assessment> assessments = assessmentService.getAllAssessment();
		return ResponseHandler.generateResponse("Assessment Retrieved Successfully", HttpStatus.OK, assessments);
	}

	@GetMapping(value = "/getassessment/{id}", name = "getAssessment")
	public ResponseEntity<Object> getidAssessmentbyid(@PathVariable long id) {
		Optional<Assessment> assessment = assessmentService.getAssessment(id);
		return ResponseHandler.generateResponse("Assessment Retrieved Successfully", HttpStatus.OK, assessment.get());
	}

}
