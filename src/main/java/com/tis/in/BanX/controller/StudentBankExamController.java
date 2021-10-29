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

import com.tis.in.BanX.domain.StudentBankExam;
import com.tis.in.BanX.handler.ResponseHandler;
import com.tis.in.BanX.service.StudentBankExamService;

@RestController
public class StudentBankExamController {

	
	@Autowired
	StudentBankExamService studentBankExamService;
	
	@RequestMapping(value="/studentbankexam", name="createStudentBankExam", method=RequestMethod.POST)
	private ResponseEntity<Object> createStudentBankExam(@RequestBody @Valid StudentBankExam studentBankExam){
		Optional<StudentBankExam> optionalStudentBankExam=studentBankExamService.getStudentBankExam(studentBankExam.getBankExamId(),
				studentBankExam.getStudentId());
		if (optionalStudentBankExam.isPresent()) {
			return ResponseHandler.generateResponse("StudentBankExam already existing.", HttpStatus.CONFLICT);
		
		
		} else {
			studentBankExam = studentBankExamService.addOrUpdateStudentBankExam(studentBankExam);
			return ResponseHandler.generateResponse("StudentBankExam Created Successfully", HttpStatus.CREATED, studentBankExam);
			
		}

	}
	@RequestMapping(value = "/updatestudentbankexam", name = "updateStudentBankExam", method = RequestMethod.PUT)
	private ResponseEntity<Object> updateStudentBankExam(@RequestBody @Valid StudentBankExam studentBankExam) {
		Optional<StudentBankExam> optionalStudentBankExam = studentBankExamService.getStudentBankExam(studentBankExam.getStudentBankExamId());
		if (optionalStudentBankExam.isPresent()) {
			studentBankExam = studentBankExamService.addOrUpdateStudentBankExam(studentBankExam);
			return ResponseHandler.generateResponse("StudentBankExam Updated Successfully", HttpStatus.OK, studentBankExam);
		}
		else {
			return ResponseHandler.generateResponse("StudentBankExam not exists in your system", HttpStatus.NOT_FOUND);
	
		}
	}
	@RequestMapping(value = "/getstudentbankexam", name = "getStudentBankExams", method = RequestMethod.GET)
	public ResponseEntity<Object> getStudentBankExam() {
		List<StudentBankExam> studentBankExams = studentBankExamService.getAllStudentBankExam();
		return ResponseHandler.generateResponse("StudentBankExam Retrieved Successfully", HttpStatus.OK, studentBankExams);
	}
	@GetMapping(value = "/getstudentbankexam/{id}", name = "getidStudentBankExam")
	public ResponseEntity<Object> getidStudentBankExambyid(@PathVariable long id) {
		Optional<StudentBankExam> studentBankExam = studentBankExamService.getStudentBankExam(id);
		return ResponseHandler.generateResponse("StudentBankExam Details Retrieved Successfully", HttpStatus.OK, studentBankExam.get());
	}
	
}
