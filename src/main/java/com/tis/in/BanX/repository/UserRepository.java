package com.tis.in.BanX.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tis.in.BanX.domain.User;
import com.tis.in.BanX.domain.UserType;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUserId(long id);

	//Optional<User> findByUserNameAndUserPassword(String userName, String password);

	Optional<User> findByUserName(String userName);

	Optional<User> findByUserNameAndUserPassword(String userName, String password);

}
