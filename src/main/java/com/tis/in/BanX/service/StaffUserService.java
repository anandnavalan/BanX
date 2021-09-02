package com.tis.in.BanX.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tis.in.BanX.domain.StaffUser;
import com.tis.in.BanX.repository.StaffUserRepository;
import java.util.List;
import java.util.Optional;;

@Service
public class StaffUserService {

	@Autowired
	StaffUserRepository staffUserRepository;

	public StaffUser addOrUpdateStaffUser(@Valid StaffUser staffuser) {
		return staffUserRepository.save(staffuser);
	}

	public List<StaffUser> getAllStaffUser() {
		return staffUserRepository.findAll();
	}

	public Optional<StaffUser> getStaffUser(long id) {
		Optional<StaffUser> staffUser = staffUserRepository.findByStaffId(id);
		return staffUser;
	}

	public Optional<StaffUser> getStaffUser(String name) {
		Optional<StaffUser> staffUser = staffUserRepository.findByStaffFirstName(name);
		return staffUser;
	}
}
