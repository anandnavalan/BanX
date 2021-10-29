package com.tis.in.BanX.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tis.in.BanX.domain.QuestionComplexity;

@Repository
public interface QuestionComplexityRepository extends JpaRepository<QuestionComplexity, Long> {

}
