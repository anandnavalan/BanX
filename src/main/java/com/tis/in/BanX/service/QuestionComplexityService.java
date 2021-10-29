package com.tis.in.BanX.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tis.in.BanX.domain.QuestionComplexity;
import com.tis.in.BanX.repository.QuestionComplexityRepository;

@Service
public class QuestionComplexityService {

	@Autowired
	QuestionComplexityRepository questionComplexityRepository;

	public QuestionComplexity addOrUpdateQuestionComplexity(QuestionComplexity questionComplexity) {
		return questionComplexityRepository.save(questionComplexity);
	}

	public Optional<QuestionComplexity> getQuestionComplexity(long questionComplexityId) {
		Optional<QuestionComplexity> questionComplexity = questionComplexityRepository.findById(questionComplexityId);
		return questionComplexity;
	}

	public List<QuestionComplexity> getAllQuestionComplexity() {
		return questionComplexityRepository.findAll();
	}

}
