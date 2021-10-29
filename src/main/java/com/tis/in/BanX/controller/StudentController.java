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
import com.tis.in.BanX.domain.StudentUser;
import com.tis.in.BanX.exception.model.ResourceCreationException;
import com.tis.in.BanX.exception.model.ResourceNotFoundException;
import com.tis.in.BanX.handler.ResponseHandler;
import com.tis.in.BanX.service.StudentService;

@RestController
public class StudentController {

	@Autowired
	StudentService studentService;

	@RequestMapping(value = "/createstudent", name = "createStudent", method = RequestMethod.POST)
	private ResponseEntity<ResponseBuilder> createStudent(@RequestBody @Valid StudentUser studentUser)
			throws ResourceCreationException {

		Optional<StudentUser> optionalStudentUser = studentService.getStudentUser(studentUser.getStudentId());

		if (optionalStudentUser.isPresent()) {
			throw new ResourceCreationException(ErrorConstants.ERROR_STUDENT_USER_EXISTS);
		} else {
			AuditInfo auditInfo = new AuditInfo();

			auditInfo.setCreatedBy("system");
			auditInfo.setCreatedDate(Utility.getSQLDate());
			auditInfo.setModifiedBy("system");
			auditInfo.setModifiedDate(Utility.getSQLDate());

			studentUser.setAuditInfo(auditInfo);
			StudentUser createStudent = studentService.addOrUpdateStudentUser(studentUser);

			ResponseBuilder builder = Utility.responseBuilder(
					Utility.getLocalizedMessage(CommonMessageConstants.SUCCESS_STUDENT_USER_CREATION),
					HttpStatus.CREATED.value());

			return new ResponseEntity<>(builder, HttpStatus.CREATED);
		}

	}

	@RequestMapping(value = "/updatestudentuser", name = "updateStudentUser", method = RequestMethod.PUT)
	private ResponseEntity<Object> updateUser(@RequestBody @Valid StudentUser studentUser)
			throws ResourceNotFoundException {
		Optional<StudentUser> optionalStudentUser = studentService.getStudentUser(studentUser.getStudentId());
		if (optionalStudentUser.isPresent()) {
			AuditInfo auditInfo = new AuditInfo();
			auditInfo.setModifiedBy("system");
			auditInfo.setModifiedDate(Utility.getSQLDate());

			studentUser.setAuditInfo(auditInfo);
			studentUser = studentService.addOrUpdateStudentUser(studentUser);

			ResponseBuilder builder = Utility.responseBuilder(
					Utility.getLocalizedMessage(CommonMessageConstants.SUCCESS_STUDENT_USER_UPDATION),
					HttpStatus.CREATED.value());

			return new ResponseEntity<>(builder, HttpStatus.CREATED);
		} else {
			throw new ResourceNotFoundException(ErrorConstants.ERROR_STUDENT_USER_NOT_EXISTS);
		}
	}

	@RequestMapping(value = "/getstudentusers", name = "getStudentUsers", method = RequestMethod.GET)
	public ResponseEntity<Object> getStudentUser() {
		List<StudentUser> studentUsers = studentService.getAllStudentUser();
		return ResponseHandler.generateResponse("Students Retrieved Successfully", HttpStatus.OK, studentUsers);
	}

	@GetMapping(value = "/getstudentuser/{id}", name = "getStudentUser")
	public ResponseEntity<Object> getidStudentUserbyid(@PathVariable long id) {
		Optional<StudentUser> studentUser = studentService.getStudentUser(id);
		return ResponseHandler.generateResponse("Students Retrieved Successfully", HttpStatus.OK, studentUser.get());
	}

}
