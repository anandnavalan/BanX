package com.tis.in.BanX.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tis.in.BanX.domain.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

	Optional<Question> findByQuestionId(Long questionid);
}
