package com.tis.in.BanX.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tis.in.BanX.domain.User;

import com.tis.in.BanX.repository.UserRepository;
//import com.tis.in.BanX.repository.UserTypeRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public User addOrUpdateUser(User user) {
		return userRepository.save(user);
	}

	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	public Optional<User> getUser(long id) {
		Optional<User> user = userRepository.findByUserId(id);
		return user;
	}

	public Optional<User> getUser(String userName) {
		return userRepository.findByUserName(userName);
	}

}
