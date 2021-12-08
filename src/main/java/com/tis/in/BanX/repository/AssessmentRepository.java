package com.tis.in.BanX.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tis.in.BanX.domain.Assessment;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment, Long> {

	Optional<Assessment> findByAssessmentId(long id);
	Optional<Assessment> findByBankExamIdAndUserId(long bankExamId, long userId);

}
