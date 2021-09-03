package com.tis.in.BanX.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tis.in.BanX.domain.User;
import com.tis.in.BanX.domain.UserType;
import com.tis.in.BanX.handler.ResponseHandler;
import com.tis.in.BanX.service.UserTypeService;

@RestController
public class UserTypeController {

	@Autowired
	UserTypeService userTypeService;

	@RequestMapping(value = "/createusertype", name = "createUserType", method = RequestMethod.POST)
	private ResponseEntity<Object> createUserType(@RequestBody @Valid UserType userType) {

		Optional<UserType> optionalUserType = userTypeService.getUserType(userType.getUserTypeName());

		if (optionalUserType.isPresent()) {
			return ResponseHandler.generateResponse("UserType already exists in our system", HttpStatus.CONFLICT);

		} else {
			UserType createdUserType = userTypeService.addOrUpdateUserType(userType);
			return ResponseHandler.generateResponse("UserType Created Successfully", HttpStatus.CREATED,
					createdUserType);
		}

	}

	@RequestMapping(value = "/updateusertype", name = "updateUserType", method = RequestMethod.PUT)
	private ResponseEntity<Object> updateUserType(@RequestBody @Valid UserType userType) {
		Optional<UserType> optionalUserType = userTypeService.getUserType(userType.getUserTypeId());

		if (optionalUserType.isPresent()) {
			userType = userTypeService.addOrUpdateUserType(userType);
			return ResponseHandler.generateResponse("UserType updated Successfully", HttpStatus.OK, userType);

		} else {
			return ResponseHandler.generateResponse("UserType not exists in our system", HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "/getusertypes", name = "getUserTypes", method = RequestMethod.GET)
	public ResponseEntity<Object> getUserTypes() {
		List<UserType> userTypes = userTypeService.getAllUserTypes();
		return ResponseHandler.generateResponse("UserTypes retrieved Successfully", HttpStatus.OK, userTypes);

	}

	@GetMapping(value = "/getusertype/{id}", name = "getidUserType")
	public ResponseEntity<Object> getidUserTypebyid(@PathVariable long id) {
		Optional<UserType> userType = userTypeService.getUserType(id);
		return ResponseHandler.generateResponse("UserTypes retrieved Successfully", HttpStatus.OK, userType);
	}

}
