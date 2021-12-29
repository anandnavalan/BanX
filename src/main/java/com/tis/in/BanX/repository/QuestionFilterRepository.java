package com.tis.in.BanX.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tis.in.BanX.domain.QuestionFilter;

@Repository
public interface QuestionFilterRepository extends JpaRepository<QuestionFilter, Long> {

	Optional<QuestionFilter> findByQuestionFilterId(long questionFilterid);
	
	Optional<QuestionFilter> findByQuestionFilterName(String questionFilterName);

	

}
