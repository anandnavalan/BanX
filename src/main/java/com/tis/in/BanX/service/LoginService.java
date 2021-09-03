package com.tis.in.BanX.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tis.in.BanX.domain.User;
import com.tis.in.BanX.model.LoginRequest;
import com.tis.in.BanX.repository.UserRepository;

@Service
public class LoginService {

	@Autowired
	UserRepository userRepository;

	public Optional<User> login(LoginRequest loginRequest) {
		Optional<User> user =  userRepository.findByUserNameAndUserPassword(loginRequest.getUserName(), 
				loginRequest.getPassword());
		return user;
	}
}
