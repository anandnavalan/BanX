package com.tis.in.BanX.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tis.in.BanX.domain.StudentQualification;

import com.tis.in.BanX.repository.StudentQualificationRepository;

@Service
public class StudentQualificationService {

	private static final Long Id = null;
	@Autowired
	StudentQualificationRepository studentQualificationRepository;

	public StudentQualification addOrUpdateStudentQualification(@Valid StudentQualification studentqualification) {
		return studentQualificationRepository.save(studentqualification);
	}

	public Optional<StudentQualification> getStudentQualification(long qualificationId, long studentId,
			String qualificationName) {
		return studentQualificationRepository.findByQualificationIdAndStudentIdAndQualificationName(qualificationId,
				studentId, qualificationName);
	}

	

	public List<StudentQualification> getAllStudentQualification() {
		return studentQualificationRepository.findAll();
	}

	public Optional<StudentQualification> getStudentQualification(long studentId) {
	
		return studentQualificationRepository.findById(Id);
	}
}
