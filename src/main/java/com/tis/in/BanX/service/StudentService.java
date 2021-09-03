package com.tis.in.BanX.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tis.in.BanX.domain.StudentUser;

import com.tis.in.BanX.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;

	public StudentUser addOrUpdateStudentUser(StudentUser studentuser) {
		return studentRepository.save(studentuser);
	}

	public List<StudentUser> getAllStudentUser() {
		return studentRepository.findAll();
	}

	public Optional<StudentUser> getStudentUser(long id) {
		return studentRepository.findById(id);
	}

}
