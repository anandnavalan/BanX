package com.tis.in.BanX.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tis.in.BanX.domain.QuestionSubCategory;
import com.tis.in.BanX.repository.QuestionSubCategoryRepository;

@Service
public class QuestionSubCategoryService {
	
	@Autowired
	QuestionSubCategoryRepository questionSubCategoryRepository;

	public  QuestionSubCategory addOrUpdateQuestionSubCategory(@Valid QuestionSubCategory questionSubCategory) {
		return questionSubCategoryRepository.save(questionSubCategory);
	}

	public Optional<QuestionSubCategory> getQuestionSubCategory(String questionSubCategoryName) {
		return questionSubCategoryRepository.findByQuestionSubCategoryName(questionSubCategoryName);
	}

	public Optional<QuestionSubCategory> getQuestionSubCategory(long questionSubCategoryId) {
		Optional<QuestionSubCategory> questionsubcategory = questionSubCategoryRepository.findByQuestionSubCategoryId(questionSubCategoryId);
		return questionsubcategory;
	}

	public List<QuestionSubCategory> getAllQuestionSubCategory() {
		return questionSubCategoryRepository.findAll();
	}

}
