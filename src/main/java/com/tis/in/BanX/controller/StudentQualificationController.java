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

import com.tis.in.BanX.domain.StudentQualification;
import com.tis.in.BanX.handler.ResponseHandler;
import com.tis.in.BanX.service.StudentQualificationService;

@RestController
public class StudentQualificationController {

	@Autowired
	StudentQualificationService studentQualificationService;

	@RequestMapping(value = "/createstudentqualification", name = "createStudentQualification", method = RequestMethod.POST)
	private ResponseEntity<Object> createStudentQualification(
			@RequestBody @Valid StudentQualification studentqualification) {

		Optional<StudentQualification> optionalStudentQualification = studentQualificationService
				.getStudentQualification(studentqualification.getQualificationId(), studentqualification.getStudentId(),
						studentqualification.getQualificationName());

		if (optionalStudentQualification.isPresent()) {
			return ResponseHandler.generateResponse("StudentQualification already exists in our system",
					HttpStatus.CONFLICT);

		} else {
			StudentQualification createStudentQualification = studentQualificationService
					.addOrUpdateStudentQualification(studentqualification);
			return ResponseHandler.generateResponse("StudentQualification Created Successfully", HttpStatus.CREATED,
					createStudentQualification);
		}

	}

	@RequestMapping(value = "/updatestudentqualification", name = "updateStudentQualification", method = RequestMethod.PUT)
	private ResponseEntity<Object> updateStudentQualification(
			@RequestBody @Valid StudentQualification studentqualification) {

		Optional<StudentQualification> optionalStudentQualification = studentQualificationService
				.getStudentQualification(studentqualification.getStudentId());

		if (optionalStudentQualification.isPresent()) {
			return ResponseHandler.generateResponse("StudentQualification not exists in our system",
					HttpStatus.NOT_FOUND);

		} else {
			StudentQualification createStudentQualification = studentQualificationService
					.addOrUpdateStudentQualification(studentqualification);
			return ResponseHandler.generateResponse("StudentQualification updated Successfully", HttpStatus.OK,
					createStudentQualification);
		}
	}

	@RequestMapping(value = "/getstudentqualification", name = "getStudentQualification", method = RequestMethod.GET)
	public ResponseEntity<Object> getStudentQualification() {

		List<StudentQualification> studentQualification = studentQualificationService.getAllStudentQualification();
		return ResponseHandler.generateResponse("StudentsQualification Retrieved Successfully", HttpStatus.OK,
				studentQualification);
	}

}
