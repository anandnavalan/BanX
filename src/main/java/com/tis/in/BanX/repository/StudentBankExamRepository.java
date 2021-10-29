package com.tis.in.BanX.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tis.in.BanX.domain.StudentBankExam;

@Repository
public interface StudentBankExamRepository extends JpaRepository<StudentBankExam, Long> {
	
	Optional<StudentBankExam> findByBankExamIdAndStudentId(long bankExamId,
			long studentId);

}
