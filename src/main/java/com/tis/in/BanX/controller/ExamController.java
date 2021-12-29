package com.tis.in.BanX.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
import com.tis.in.BanX.domain.Exam;
import com.tis.in.BanX.exception.model.ResourceCreationException;
import com.tis.in.BanX.exception.model.ResourceNotFoundException;
import com.tis.in.BanX.handler.ResponseHandler;
import com.tis.in.BanX.service.ExamService;

@RestController
public class ExamController {

	@Autowired
	ExamService examService;

	@Transactional
	@RequestMapping(value = "/createexam", name = "CreateExam", method = RequestMethod.POST)
	private ResponseEntity<ResponseBuilder> createExam(Principal principal, @RequestBody @Valid Exam exam)
			throws ResourceCreationException {

		Optional<Exam> optionalExam = examService.getExam(exam.getExamId());

		if (optionalExam.isPresent()) {
			throw new ResourceCreationException(ErrorConstants.ERROR_EXAM_EXISTS);
		} else {

			AuditInfo auditInfo = new AuditInfo();

			auditInfo.setCreatedBy(principal.getName());
			auditInfo.setCreatedDate(Utility.getSQLDate());
			auditInfo.setModifiedBy(principal.getName());
			auditInfo.setModifiedDate(Utility.getSQLDate());

			exam.setAuditInfo(auditInfo);

			Exam createExam = examService.addOrUpdateExam(exam);

			ResponseBuilder builder = Utility.responseBuilder(
					Utility.getLocalizedMessage(CommonMessageConstants.SUCCESS_EXAM_CREATION),
					HttpStatus.CREATED.value());

			return new ResponseEntity<>(builder, HttpStatus.CREATED);

		}

	}

	@RequestMapping(value = "/updateexam", name = "UpdateExam", method = RequestMethod.PUT)
	private ResponseEntity<ResponseBuilder> updateExam(Principal principal, @RequestBody @Valid Exam exam)
			throws ResourceNotFoundException, ResourceCreationException {
		Optional<Exam> optionalExam = examService.getExam(exam.getExamId());

		if (!optionalExam.isPresent()) {
			throw new ResourceCreationException(ErrorConstants.ERROR_EXAM_NOT_EXISTS);
		} else {
			AuditInfo auditInfo = new AuditInfo();
			auditInfo.setCreatedBy(principal.getName());
			auditInfo.setCreatedDate(Utility.getSQLDate());
			auditInfo.setModifiedBy(principal.getName());
			auditInfo.setModifiedDate(Utility.getSQLDate());

			exam.setAuditInfo(auditInfo);

			exam = examService.addOrUpdateExam(exam);
			ResponseBuilder builder = Utility.responseBuilder(
					Utility.getLocalizedMessage(CommonMessageConstants.SUCCESS_EXAM_UPDATION),
					HttpStatus.CREATED.value());

			return new ResponseEntity<>(builder, HttpStatus.CREATED);
		}

	}

	@RequestMapping(value = "/getexams", name = "getExams", method = RequestMethod.GET)
	public ResponseEntity<Object> getExams() {
		List<Exam> exams = examService.getExams();
		return ResponseHandler.generateResponse("Exam Retrieved Successfully", HttpStatus.OK, exams);
	}

	@GetMapping(value = "/getexam/{id}", name = "getExam")
	public ResponseEntity<Object> getidBankExambyid(@PathVariable long id) {
		Optional<Exam> exam = examService.getExam(id);
		return ResponseHandler.generateResponse("Exam Retrieved Successfully", HttpStatus.OK, exam.get());
	}

}
