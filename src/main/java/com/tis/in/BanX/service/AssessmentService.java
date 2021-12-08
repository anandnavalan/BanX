package com.tis.in.BanX.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tis.in.BanX.domain.Assessment;
import com.tis.in.BanX.repository.AssessmentRepository;

@Service
public class AssessmentService {

	@Autowired
	AssessmentRepository assessmentRepository;

	public Assessment addOrUpdateAssessment(Assessment assessment) {
		return assessmentRepository.save(assessment);
	}

	public Optional<Assessment> getAssessment(long id) {
		Optional<Assessment> assessment = assessmentRepository.findByAssessmentId(id);
		return assessment;
	}

	public List<Assessment> getAllAssessment() {
		return assessmentRepository.findAll();
	}

	public Optional<Assessment> getAssessment(long bankExamId, long userId) {
		Optional<Assessment> assessment = assessmentRepository.findByBankExamIdAndUserId(bankExamId,userId);
		return assessment;
		
	}

}
