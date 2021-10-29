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
import com.tis.in.BanX.domain.AuditInfo;
import com.tis.in.BanX.domain.StudentQualification;
import com.tis.in.BanX.domain.StudentUser;
import com.tis.in.BanX.exception.model.ResourceCreationException;
import com.tis.in.BanX.exception.model.ResourceNotFoundException;
import com.tis.in.BanX.handler.ResponseHandler;
import com.tis.in.BanX.service.StudentQualificationService;
import com.tis.in.BanX.service.StudentService;

@RestController
public class StudentQualificationController {

	@Autowired
	StudentService studentService;

	@Autowired
	StudentQualificationService studentQualificationService;

	@RequestMapping(value = "/createstudentqualification", name = "createStudentQualification", method = RequestMethod.POST)
	private ResponseEntity<ResponseBuilder> createStudentQualification(
			@RequestBody @Valid StudentQualification studentQualification) throws ResourceCreationException {

		Optional<StudentUser> optionalStudentUser = studentService.getStudentUser(studentQualification.getStudentId());

		if(!optionalStudentUser.isPresent()) {
//			throw new ResourceCreationException(ErrorConstants.ERROR_STUDENT_NOT_EXISTS);	
		}
			
		Optional<StudentQualification> optionalStudentQualification = studentQualificationService
				.getStudentQualification(studentQualification.getQualificationId(), studentQualification.getStudentId(),
						studentQualification.getQualificationName());

		if (optionalStudentQualification.isPresent()) {
			throw new ResourceCreationException(ErrorConstants.ERROR_STUDENT_QUALIFICATION_EXISTS);
		} else {
			AuditInfo auditInfo = new AuditInfo();

			auditInfo.setCreatedBy("system");
			auditInfo.setCreatedDate(Utility.getSQLDate());
			auditInfo.setModifiedBy("system");
			auditInfo.setModifiedDate(Utility.getSQLDate());

			studentQualification.setAuditInfo(auditInfo);
			StudentQualification createStudentQualification = studentQualificationService
					.addOrUpdateStudentQualification(studentQualification);

			ResponseBuilder builder = Utility.responseBuilder(
					Utility.getLocalizedMessage(CommonMessageConstants.SUCCESS_STUDENT_QUALIFICATION_CREATION),
					HttpStatus.CREATED.value());

			return new ResponseEntity<>(builder, HttpStatus.CREATED);
		}

	}

	@RequestMapping(value = "/updatestudentqualification", name = "updateStudentQualification", method = RequestMethod.PUT)
	private ResponseEntity<ResponseBuilder> updateStudentQualification(
			@RequestBody @Valid StudentQualification studentQualification) throws ResourceNotFoundException {

		Optional<StudentUser> optionalStudentUser = studentService.getStudentUser(studentQualification.getStudentId());

		if(!optionalStudentUser.isPresent()) {
//			throw new ResourceCreationException(ErrorConstants.ERROR_STUDENT_NOT_EXISTS);	
		}
		
		Optional<StudentQualification> optionalStudentQualification = studentQualificationService
				.getStudentQualification(studentQualification.getStudentId());

		if (optionalStudentQualification.isPresent()) {
			AuditInfo auditInfo = new AuditInfo();
			auditInfo.setModifiedBy("system");
			auditInfo.setModifiedDate(Utility.getSQLDate());

			studentQualification.setAuditInfo(auditInfo);
			studentQualification = studentQualificationService.addOrUpdateStudentQualification(studentQualification);

			ResponseBuilder builder = Utility.responseBuilder(
					Utility.getLocalizedMessage(CommonMessageConstants.SUCCESS_STUDENT_QUALIFICATION_UPDATION),
					HttpStatus.CREATED.value());

			return new ResponseEntity<>(builder, HttpStatus.CREATED);
		} else {
			throw new ResourceNotFoundException(ErrorConstants.ERROR_STUDENT_QUALIFICATION_NOT_EXISTS);
		}
	}

	@RequestMapping(value = "/getstudentqualifications", name = "getStudentQualifications", method = RequestMethod.GET)
	public ResponseEntity<Object> getStudentQualification() {

		List<StudentQualification> studentQualifications = studentQualificationService.getAllStudentQualification();
		return ResponseHandler.generateResponse("StudentsQualification Retrieved Successfully", HttpStatus.OK,
				studentQualifications);
	}

	@GetMapping(value = "/getstudentqualification/{id}", name = "getStudentQualification")
	public ResponseEntity<Object> getidStudentQualificationbyid(@PathVariable long id) {
		Optional<StudentQualification> studentQualification = studentQualificationService.getStudentQualification(id);
		return ResponseHandler.generateResponse("StudentsQualification Retrieved Successfully", HttpStatus.OK,
				studentQualification.get());
	}

}
