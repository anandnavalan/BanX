package com.tis.in.BanX.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tis.in.BanX.domain.BankQuestionCategory;

@Repository
public interface BankQuestionCategoryRepository extends JpaRepository<BankQuestionCategory, Long>{
	
	Optional<BankQuestionCategory> findByBankExamIdAndQuestionCategoryId(long bankExamId,
			long questionCategoryId);

}
