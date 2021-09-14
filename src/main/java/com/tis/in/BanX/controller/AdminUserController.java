package com.tis.in.BanX.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tis.in.BanX.domain.AdminUser;
import com.tis.in.BanX.handler.ResponseHandler;
import com.tis.in.BanX.service.AdminUserService;

@RestController
public class AdminUserController {

	@Autowired
	AdminUserService adminUserService;

	@RequestMapping(value = "/createadminuser", name = "createadminuser", method = RequestMethod.POST)
	private ResponseEntity<Object> createAdminUser(@RequestBody @Valid AdminUser adminuser) {

		Optional<AdminUser> adminUser = adminUserService.getAdminUser(adminuser.getAdminId());
		if (adminUser.isPresent()) {
			return ResponseHandler.generateResponse("Admin already exists in our system", HttpStatus.CONFLICT);
		} else

		{
			AdminUser createAdminUser = adminUserService.addOrUpdateAdminUser(adminuser);
			return ResponseHandler.generateResponse("Admin Created Successfullty", HttpStatus.CREATED, createAdminUser);
		}
	}

	@RequestMapping(value = "/updateadminuser", name = "updateAdminUser", method = RequestMethod.PUT)
	private ResponseEntity<Object> updateAdminUser(@RequestBody @Valid AdminUser adminuser) {
		Optional<AdminUser> adminUser = adminUserService.getAdminUser(adminuser.getAdminId());
		if (adminUser.isPresent()) {
			AdminUser updateAdminUser = adminUserService.addOrUpdateAdminUser(adminuser);
			return ResponseHandler.generateResponse("Admin Updated Successfullty", HttpStatus.CREATED, updateAdminUser);
		} else {
			return ResponseHandler.generateResponse("Admin not exists in our system", HttpStatus.NOT_FOUND);

		}

	}

	@RequestMapping(value = "/getadminuser", name = "getAdminUser", method = RequestMethod.GET)
	private ResponseEntity<Object> getAdminUser() {
		List<AdminUser> adminusers = adminUserService.getAllAdminUser();
		return ResponseHandler.generateResponse("Admin Retrieved Successfullty", HttpStatus.OK, adminusers);
	}

	@RequestMapping(value = "/getadminuser/{id}", name = "getAdminId", method = RequestMethod.GET)
	public ResponseEntity<Object> get(@PathVariable long id) {
		Optional<AdminUser> adminUser = adminUserService.getAdminUser(id);
		if (adminUser.isPresent()) {
			return ResponseHandler.generateResponse("Admin Retrieve Successfullty", HttpStatus.OK, adminUser);
		} else {
			return ResponseHandler.generateResponse("Admin not exists in our system", HttpStatus.NOT_FOUND);
		}
	}
}
