package com.tis.in.BanX.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tis.in.BanX.domain.QuestionCategory;
import com.tis.in.BanX.repository.QuestionCategoryRepository;

@Service
public class QuestionCategoryService {
	
	@Autowired
	QuestionCategoryRepository questionCategoryRepository;

	public  QuestionCategory addOrUpdateQuestionCategory(@Valid QuestionCategory questionCategory) {
		return questionCategoryRepository.save(questionCategory);
	}

	public Optional<QuestionCategory> getQuestionCategory(String questionCategoryName) {
		return questionCategoryRepository.findByQuestionCategoryName(questionCategoryName);
	}

	public Optional<QuestionCategory> getQuestionCategory(long questionCategoryId) {
		Optional<QuestionCategory> questioncategory = questionCategoryRepository.findByQuestionCategoryId(questionCategoryId);
		return questioncategory;
	}

	public List<QuestionCategory> getAllQuestionCategory() {
		return questionCategoryRepository.findAll();
	}

}
