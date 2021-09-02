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

import com.tis.in.BanX.domain.StaffUser;
import com.tis.in.BanX.handler.ResponseHandler;
import com.tis.in.BanX.service.StaffUserService;

@RestController
public class StaffUserController {

	@Autowired
	StaffUserService staffUserService;

	@RequestMapping(value = "/createstaffuser", name = "createStaffUser", method = RequestMethod.POST)
	private ResponseEntity<Object> createStaffUser(@RequestBody @Valid StaffUser staffuser) {

		Optional<StaffUser> staffUser = Optional.empty();

		if (Optional.of(staffuser.getStaffId()).get() > 0)
			staffUser = staffUserService.getStaffUser(staffuser.getStaffId());

		if (staffUser.isPresent()) {
			return ResponseHandler.generateResponse("Staff already exists in our system", HttpStatus.CONFLICT);
		} else {
			StaffUser createStaffUser = staffUserService.addOrUpdateStaffUser(staffuser);
			return ResponseHandler.generateResponse("Staff Created Successfullty", HttpStatus.CREATED, createStaffUser);
			// return new ResponseEntity<>(staffuser, HttpStatus.CREATED);
		}
	}

	@RequestMapping(value = "/updatestaffuser", name = "updateStaffUser", method = RequestMethod.PUT)
	private ResponseEntity<Object> updateStaffUser(@RequestBody @Valid StaffUser staffuser) {

		Optional<StaffUser> staffUser = staffUserService.getStaffUser(staffuser.getStaffId());
		// Optional<StaffUser> staffUser = staffUserService.getStaffUser(id);
		if (staffUser.isPresent()) {
			StaffUser createStaffUser = staffUserService.addOrUpdateStaffUser(staffuser);
			return ResponseHandler.generateResponse("Staff Updated Successfullty", HttpStatus.CREATED, createStaffUser);
		} else {
			return ResponseHandler.generateResponse("Staff not exists in our system", HttpStatus.NOT_FOUND);

		}

	}

	@RequestMapping(value = "/getstaffuser", name = "getStaffUser", method = RequestMethod.GET)
	private ResponseEntity<Object> getStaffUser() {
		List<StaffUser> staffusers = staffUserService.getAllStaffUser();
		return ResponseHandler.generateResponse("Staffs Retrieved Successfullty", HttpStatus.OK, staffusers);
	}

	@RequestMapping(value = "/getstaffuser/{id}", name = "getStaffId", method = RequestMethod.GET)
	public ResponseEntity<Object> get(@PathVariable long id) {
		Optional<StaffUser> staffUser = staffUserService.getStaffUser(id);
		return ResponseHandler.generateResponse("Staff Retrieve Successfullty", HttpStatus.OK, staffUser);
	}
}
