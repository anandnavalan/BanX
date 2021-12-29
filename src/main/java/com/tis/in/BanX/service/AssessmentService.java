package com.tis.in.BanX.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tis.in.BanX.domain.Assessment;
import com.tis.in.BanX.domain.AssessmentQuestion;
import com.tis.in.BanX.domain.Assessment_Select;
import com.tis.in.BanX.domain.Question;
import com.tis.in.BanX.model.AssessmentQuestionResponse;
import com.tis.in.BanX.model.AssessmentResponse;
import com.tis.in.BanX.repository.AssessmentRepository;
import com.tis.in.BanX.repository.Assessment_SelectRepository;
import com.tis.in.BanX.repository.QuestionRepository;

@Service
public class AssessmentService {

	@Autowired
	AssessmentRepository assessmentRepository;

	@Autowired
	Assessment_SelectRepository assessmentSelectRepository;

	public Assessment addOrUpdateAssessment(Assessment assessment) {
		return assessmentRepository.save(assessment);
	}

	public Optional<Assessment> getAssessment(long id) {
		Optional<Assessment> assessment = assessmentRepository.findByAssessmentId(id);
		return assessment;
	}

	public Optional<Assessment> getAssessment(long bankExamId, long userId) {
		Optional<Assessment> assessment = assessmentRepository.findByBankExamIdAndUserId(bankExamId, userId);
		return assessment;

	}

	public Optional<Assessment_Select> getAssessmentWithQuestion(long id) {
		Optional<Assessment_Select> optionalAssessment = assessmentSelectRepository.findByAssessmentId(id);
		return optionalAssessment;

	}

	public List<Assessment> getAllAssessment() {
		return assessmentRepository.findAll();
	}

}
