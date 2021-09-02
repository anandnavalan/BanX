package com.tis.in.BanX.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tis.in.BanX.domain.UserType;
import com.tis.in.BanX.repository.UserTypeRepository;

@Service
public class UserTypeService {

	@Autowired
	UserTypeRepository userTypeRepository;

	public UserType addUserType(UserType userType) {
		return userTypeRepository.save(userType);
	}

	public UserType updateUserType(UserType userType) {
		return userTypeRepository.save(userType);
	}
	
	public List<UserType> getAllUserTypes() {
		return userTypeRepository.findAll();
	}

	public Optional<UserType>  getUserType(long id) {
		Optional<UserType> userType = userTypeRepository.findByUserTypeId(id);
		return userType;
	}
	
	public Optional<UserType> getUserTypeByName(String name) {
		return userTypeRepository.findByUserTypeName(name);
	}

}
