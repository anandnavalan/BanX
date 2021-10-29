package com.tis.in.BanX.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tis.in.BanX.domain.BankQuestionCategory;
import com.tis.in.BanX.repository.BankQuestionCategoryRepository;

@Service
public class BankQuestionCategoryService {

	@Autowired
	BankQuestionCategoryRepository bankQuestionCategoryRepository;

	public BankQuestionCategory addOrUpdateBankQuestionCategory(BankQuestionCategory bankQuestionCategory) {

		return bankQuestionCategoryRepository.save(bankQuestionCategory);
	}

	public Optional<BankQuestionCategory> getBankQuestionCategory(long bankExamId, long questionCategoryId) {
		return bankQuestionCategoryRepository.findByBankExamIdAndQuestionCategoryId(bankExamId, questionCategoryId);
	}

	public Optional<BankQuestionCategory> getBankQuestionCategory(long bankQuestionCategoryId) {
		return bankQuestionCategoryRepository.findById(bankQuestionCategoryId);
	}

	public List<BankQuestionCategory> getAllBankQuestionCategory() {
		return bankQuestionCategoryRepository.findAll();
	}
}
