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
import com.tis.in.BanX.domain.BankExam;
import com.tis.in.BanX.exception.model.ResourceCreationException;
import com.tis.in.BanX.exception.model.ResourceNotFoundException;
import com.tis.in.BanX.handler.ResponseHandler;
import com.tis.in.BanX.service.BankExamService;

@RestController
public class BankExamController {

	@Autowired
	BankExamService bankExamService;

	@RequestMapping(value = "/createbankexam", name = "CreateBankExam", method = RequestMethod.POST)
	private ResponseEntity<ResponseBuilder> createBankExam(@RequestBody @Valid BankExam bankExam)
			throws ResourceCreationException {
		Optional<BankExam> optionalBankExam = bankExamService.getBankExam(bankExam.getBankExamName());
		if (optionalBankExam.isPresent()) {
			throw new ResourceCreationException(ErrorConstants.ERROR_BANK_EXAM_EXISTS);
		} else {
			AuditInfo auditInfo = new AuditInfo();

			auditInfo.setCreatedBy("system");
			auditInfo.setCreatedDate(Utility.getSQLDate());
			auditInfo.setModifiedBy("system");
			auditInfo.setModifiedDate(Utility.getSQLDate());

			bankExam.setAuditInfo(auditInfo);

			BankExam createBankExam = bankExamService.addOrUpdateBankExam(bankExam);

			ResponseBuilder builder = Utility.responseBuilder(
					Utility.getLocalizedMessage(CommonMessageConstants.SUCCESS_BANK_EXAM_CREATION),
					HttpStatus.CREATED.value());

			return new ResponseEntity<>(builder, HttpStatus.CREATED);

		}

	}

	@RequestMapping(value = "/updatebankexam", name = "UpdateBankExam", method = RequestMethod.PUT)
	private ResponseEntity<ResponseBuilder> updateBankExam(@RequestBody @Valid BankExam bankExam)
			throws ResourceNotFoundException, ResourceCreationException {
		Optional<BankExam> optionalBankExamId = bankExamService.getBankExam(bankExam.getBankExamId());
		if (optionalBankExamId.isPresent()) {

			Optional<BankExam> optionalBankExam = bankExamService.getBankExam(bankExam.getBankExamName());

			if (optionalBankExam.isPresent()) {
				throw new ResourceCreationException(ErrorConstants.ERROR_BANK_EXAM_EXISTS);
			} else {
				AuditInfo auditInfo = new AuditInfo();
				auditInfo.setCreatedBy("system");
				auditInfo.setCreatedDate(Utility.getSQLDate());
				auditInfo.setModifiedBy("system");
				auditInfo.setModifiedDate(Utility.getSQLDate());

				bankExam.setAuditInfo(auditInfo);

				bankExam = bankExamService.addOrUpdateBankExam(bankExam);
				ResponseBuilder builder = Utility.responseBuilder(
						Utility.getLocalizedMessage(CommonMessageConstants.SUCCESS_BANK_EXAM_UPDATION),
						HttpStatus.CREATED.value());

				return new ResponseEntity<>(builder, HttpStatus.CREATED);
			}

		} else {
			throw new ResourceNotFoundException(ErrorConstants.ERROR_BANK_EXAM_NOT_EXISTS);
		}
	}

	@RequestMapping(value = "/getbankexams", name = "getBankExams", method = RequestMethod.GET)
	public ResponseEntity<Object> getBankExam() {
		List<BankExam> bankExams = bankExamService.getAllBankExam();
		return ResponseHandler.generateResponse("BankExam Retrieved Successfully", HttpStatus.OK, bankExams);
	}

	@GetMapping(value = "/getbankexam/{id}", name = "getBankExam")
	public ResponseEntity<Object> getidBankExambyid(@PathVariable long id) {
		Optional<BankExam> bankExam = bankExamService.getBankExam(id);
		return ResponseHandler.generateResponse("BankExam Retrieved Successfully", HttpStatus.OK, bankExam.get());
	}
}
