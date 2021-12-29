package com.tis.in.BanX.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tis.in.BanX.domain.Exam;
import com.tis.in.BanX.repository.ExamRepository;

@Service
public class ExamService {

	@Autowired
	ExamRepository examRepository;

	public Exam addOrUpdateExam(Exam exam) {
		return examRepository.save(exam);
	}

	public List<Exam> getExams() {
		List<Exam> exams = examRepository.findAll();
		return exams;
	}

	public Optional<Exam> getExam(long id) {
		Optional<Exam> exam = examRepository.findByExamId(id);
		return exam;
	}

}
