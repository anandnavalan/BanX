package com.tis.in.BanX.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tis.in.BanX.domain.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
	Optional<Users> findByUsername(String username);
}