package com.tis.in.BanX.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.tis.in.BanX.domain.QuestionCategory;

@Repository
public interface QuestionCategoryRepository extends JpaRepository<QuestionCategory, Long> {

	
	Optional<QuestionCategory> findByQuestionCategoryId(long questionCategoryid);
	Optional<QuestionCategory> findByQuestionCategoryName(String questionCategoryName);
}
