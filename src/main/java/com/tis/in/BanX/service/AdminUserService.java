package com.tis.in.BanX.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tis.in.BanX.domain.AdminUser;
import com.tis.in.BanX.domain.StaffUser;
import com.tis.in.BanX.repository.AdminUserRepository;

@Service
public class AdminUserService {
	@Autowired
	AdminUserRepository adminUserRepository;

	public AdminUser addOrUpdateAdminUser(@Valid AdminUser adminuser) {
		return adminUserRepository.save(adminuser);
	}

	

	public Optional<AdminUser> getAdminUser(long id) {
		Optional<AdminUser> staffUser = adminUserRepository.findByAdminId(id);
		return staffUser;
	}



	public List<AdminUser> getAllAdminUser() {
		return adminUserRepository.findAll();
	}

}
