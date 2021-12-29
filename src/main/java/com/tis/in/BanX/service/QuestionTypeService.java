package com.tis.in.BanX.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tis.in.BanX.domain.QuestionType;
import com.tis.in.BanX.repository.QuestionTypeRepository;

@Service
public class QuestionTypeService {
	
	@Autowired
	QuestionTypeRepository questionTypeRepository;
	
	public  QuestionType addOrUpdateQuestionType(@Valid QuestionType questionType) {
		return questionTypeRepository.save(questionType);
	}

	public Optional<QuestionType> getQuestionType(String questionTypeName) {
		return questionTypeRepository.findByQuestionTypeName(questionTypeName);
	}

	public Optional<QuestionType> getQuestionType(long questionTypeId) {
		Optional<QuestionType> questiontype= questionTypeRepository.findByQuestionTypeId(questionTypeId);
		return questiontype;
	}

	public List<QuestionType> getAllQuestionType() {
		return questionTypeRepository.findAll();
	}

}
