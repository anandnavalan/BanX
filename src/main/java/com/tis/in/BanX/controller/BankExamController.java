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

import com.tis.in.BanX.domain.BankExam;
import com.tis.in.BanX.handler.ResponseHandler;
import com.tis.in.BanX.service.BankExamService;

@RestController
public class BankExamController {

	@Autowired
	BankExamService bankExamService;

	@RequestMapping(value = "/createbankExam", name = "createBankExam", method = RequestMethod.POST)
	private ResponseEntity<Object> createBankExam(@RequestBody @Valid BankExam bankExam) {
		Optional<BankExam> optionalBankExam = bankExamService.getBankExam(bankExam.getBankExamName());
		if (optionalBankExam.isPresent()) {
			return ResponseHandler.generateResponse("BankExam already exists in our system", HttpStatus.CONFLICT);

		} else {
			bankExam = bankExamService.addOrUpdateBankExam(bankExam);
			return ResponseHandler.generateResponse("BankExam created successfully", HttpStatus.CREATED, bankExam);

		}

	}

	@RequestMapping(value = "/updatebankexam", name = "updateBankExam", method = RequestMethod.PUT)
	private ResponseEntity<Object> updateBankExam(@RequestBody @Valid BankExam bankExam) {
		Optional<BankExam> optionalBankExam = bankExamService.getBankExam(bankExam.getBankExamId());
		if (optionalBankExam.isPresent()) {
			return ResponseHandler.generateResponse("BankExam not existing in our system", HttpStatus.NOT_FOUND);

		} else {
			bankExam = bankExamService.addOrUpdateBankExam(bankExam);
			return ResponseHandler.generateResponse("BankExam updated successfully", HttpStatus.OK, bankExam);

		}
	}

	@RequestMapping(value = "/getbankexam", name = "getBankExams", method = RequestMethod.GET)
	public ResponseEntity<Object> getBankExam() {

		List<BankExam> bankExams = bankExamService.getAllBankExam();
		return ResponseHandler.generateResponse("BankExam Retrieved Successfully", HttpStatus.OK, bankExams);
	}

	@GetMapping(value = "/getbankexam/{id}", name = "getBankExam")
	public ResponseEntity<Object> getidBankExambyid(@PathVariable long id) {
		Optional<BankExam> bankExam = bankExamService.getBankExam(id);
		return ResponseHandler.generateResponse("BankExams Retrieved Successfully", HttpStatus.OK, bankExam.get());
	}
}
