package com.tis.in.BanX.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tis.in.BanX.domain.StudentQualification;

public interface StudentQualificationRepository extends JpaRepository<StudentQualification, Long> {

	Optional<StudentQualification> findByQualificationIdAndStudentIdAndQualificationName(long qualificationId,
			long studentId, String qualificationName);

}
