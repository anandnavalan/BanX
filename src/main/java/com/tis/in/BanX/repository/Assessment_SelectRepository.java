package com.tis.in.BanX.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tis.in.BanX.domain.Assessment_Select;

@Repository
public interface Assessment_SelectRepository extends JpaRepository<Assessment_Select, Long> {

	Optional<Assessment_Select> findByAssessmentId(long id);

	Optional<Assessment_Select> findByAssessmentIdOrderByAssessmentQuestions_AssessmentQuestionId(long id);

}
