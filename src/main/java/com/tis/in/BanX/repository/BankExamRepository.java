package com.tis.in.BanX.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tis.in.BanX.domain.BankExam;


@Repository
public interface BankExamRepository extends JpaRepository<BankExam, Long> {
	Optional<BankExam> findByBankExamId(long bankExamid);
	Optional<BankExam> findByBankExamName(String bankExamName);
	

}
