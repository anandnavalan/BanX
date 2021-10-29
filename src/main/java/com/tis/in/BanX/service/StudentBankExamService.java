package com.tis.in.BanX.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.Id;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tis.in.BanX.domain.StudentBankExam;
import com.tis.in.BanX.repository.StudentBankExamRepository;
@Service 
public class StudentBankExamService {
	
	@Autowired
	StudentBankExamRepository studentBankExamRepository;
	
	

	public @Valid StudentBankExam addOrUpdateStudentBankExam( StudentBankExam studentBankExam) {
		return studentBankExamRepository.save(studentBankExam);
	}

	public Optional<StudentBankExam> getStudentBankExam(long bankExamId, long studentId) {
		return studentBankExamRepository.findByBankExamIdAndStudentId(bankExamId,
				studentId);
	}

	public List<StudentBankExam> getAllStudentBankExam() {
		return studentBankExamRepository.findAll();
	}

	public Optional<StudentBankExam> getStudentBankExam(long studentBankExamId) {
		return studentBankExamRepository.findById(studentBankExamId);
	}

}
