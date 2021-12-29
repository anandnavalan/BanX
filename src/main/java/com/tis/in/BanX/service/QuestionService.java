package com.tis.in.BanX.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tis.in.BanX.domain.Question;
import com.tis.in.BanX.domain.QuestionOption;
import com.tis.in.BanX.model.QuestionsResponse;
import com.tis.in.BanX.repository.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	QuestionRepository questionRepository;

	public Question addOrUpdateQuestion(@Valid Question question) {
		return questionRepository.save(question);
	}

	public Optional<Question> getQuestion(Long questionId) {
		Optional<Question> question = questionRepository.findByQuestionId(questionId);
		return question;
	}

	public List<Question> getAllQuestion() {
		return questionRepository.findAll();
	}

	public List<QuestionsResponse> getAllQuestions() {
		List<Question> questions = questionRepository.findAll();
		return questions.stream().map(question -> {
			return new QuestionsResponse.ResponseBuilder(question.getQuestionId())
					.questionCategoryId(question.getQuestionCategoryId())
					.questionSubCategoryId(question.getQuestionSubCategoryId())
					.questionTypeId(question.getQuestionTypeId())
					.questionName(question.getQuestionName())
					.build();
		}).collect(Collectors.toList());

	}

}
