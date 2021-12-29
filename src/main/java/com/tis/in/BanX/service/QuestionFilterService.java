package com.tis.in.BanX.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tis.in.BanX.domain.QuestionFilter;
import com.tis.in.BanX.repository.QuestionFilterRepository;


@Service
public class QuestionFilterService {
	@Autowired
	QuestionFilterRepository questionFilterRepository;

	public  QuestionFilter addOrUpdateQuestionFilter(@Valid QuestionFilter questionFilter) {
		return questionFilterRepository.save(questionFilter);
	}

	public Optional<QuestionFilter> getQuestionFilter(String questionFilterName) {
		return questionFilterRepository.findByQuestionFilterName(questionFilterName);
	}

	public Optional<QuestionFilter> getQuestionFilter(long questionFilterId) {
		Optional<QuestionFilter> questionfilter = questionFilterRepository.findByQuestionFilterId(questionFilterId);
		return questionfilter;
	}

	public List<QuestionFilter> getAllQuestionFilter() {
		return questionFilterRepository.findAll();
	}


}
