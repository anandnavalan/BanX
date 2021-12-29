package com.tis.in.BanX.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tis.in.BanX.domain.QuestionSubCategory;



@Repository
public interface QuestionSubCategoryRepository extends JpaRepository<QuestionSubCategory, Long> {

	
	Optional<QuestionSubCategory> findByQuestionSubCategoryId(long questionSubCategoryid);
	Optional<QuestionSubCategory> findByQuestionSubCategoryName(String questionSubCategoryName);
}
