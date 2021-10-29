package com.tis.in.BanX.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tis.in.BanX.domain.BankExam;
import com.tis.in.BanX.repository.BankExamRepository;

@Service
public class BankExamService {

	
	@Autowired
	BankExamRepository bankExamRepository;

	public Optional<BankExam> getBankExam(String bankExamName) {
		return bankExamRepository.findByBankExamName(bankExamName);
	}

	public BankExam addOrUpdateBankExam(BankExam bankExam) {
		return bankExamRepository.save(bankExam);
	}

	public Optional<BankExam> getBankExam(long bankExamId) {
		Optional<BankExam> bankexam = bankExamRepository.findByBankExamId(bankExamId);
		return bankexam;
	}

	public List<BankExam> getAllBankExam() {
		return bankExamRepository.findAll();
	}

}
