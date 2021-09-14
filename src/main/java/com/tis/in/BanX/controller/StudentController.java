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

import com.tis.in.BanX.domain.StudentUser;
import com.tis.in.BanX.handler.ResponseHandler;
import com.tis.in.BanX.service.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	StudentService studentService;

	@RequestMapping(value = "/createstudent", name = "createStudent", method = RequestMethod.POST)
	private ResponseEntity<Object> createStudent(@RequestBody @Valid StudentUser studentUser) {

		Optional<StudentUser> optionalStudentUser = studentService.getStudentUser(studentUser.getStudentId());

		if (optionalStudentUser.isPresent()) {
			return ResponseHandler.generateResponse("Student already existing.", HttpStatus.CONFLICT);
		
		
		} else {
			studentUser = studentService.addOrUpdateStudentUser(studentUser);
			return ResponseHandler.generateResponse("Student Created Successfully", HttpStatus.CREATED, studentUser);
			
		}

	}

	@RequestMapping(value = "/updatestudentuser", name = "updateStudentUser", method = RequestMethod.PUT)
	private ResponseEntity<Object> updateUser(@RequestBody @Valid StudentUser studentUser) {
		Optional<StudentUser> optionalStudentUser = studentService.getStudentUser(studentUser.getStudentId());
		if (optionalStudentUser.isPresent()) {
			return ResponseHandler.generateResponse("Student not exists in your system", HttpStatus.NOT_FOUND);
			
		}
		else {
			studentUser = studentService.addOrUpdateStudentUser(studentUser);
			return ResponseHandler.generateResponse("Student Updated Successfully", HttpStatus.OK, studentUser);
			
		}
	}
		

	@RequestMapping(value = "/getstudentuser", name = "getStudentUser", method = RequestMethod.GET)
	public ResponseEntity<Object> getStudentUser() {
		List<StudentUser> studentUsers = studentService.getAllStudentUser();
		return ResponseHandler.generateResponse("Students Retrieved Successfully", HttpStatus.OK, studentUsers);
	}

	@GetMapping(value = "/getstudentuser/{id}", name = "getidStudentUser")
	public ResponseEntity<Object> getidStudentUserbyid(@PathVariable long id) {
		Optional<StudentUser> studentUser = studentService.getStudentUser(id);
		return ResponseHandler.generateResponse("Students Retrieved Successfully", HttpStatus.OK, studentUser.get());
	}

}
