package com.tis.in.BanX.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

import com.tis.in.BanX.domain.UserType;
import com.tis.in.BanX.service.UserTypeService;


@RestController
public class UserTypeController {
	
	@Autowired
	UserTypeService userTypeService;
	
	@RequestMapping(value ="/createusertype", name = "createUserType", method =  RequestMethod.POST)
	private ResponseEntity<UserType> createUserType(@RequestBody @Valid UserType userType) {
		
//		userTypeService.getUserTypeByName(userType.getUserTypeName())
		UserType createdUserType = userTypeService.addUserType(userType);
		return new ResponseEntity<>(createdUserType, HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value ="/updateusertype", name = "updateUserType", method = RequestMethod.PUT)
	private ResponseEntity<UserType> updateUserType(@RequestBody @Validated UserType userType) {
		UserType createdUserType = userTypeService.updateUserType(userType);
		return new ResponseEntity<>(userType, HttpStatus.OK);
		
	}
	
	@RequestMapping(value ="/getusertypes", name = "getUserTypes", method = RequestMethod.GET)
	public ResponseEntity<List<UserType>> getUserTypes() {

		List<UserType> UserTypes = userTypeService.getAllUserTypes();
		return new ResponseEntity<>(UserTypes, HttpStatus.OK);
		
	}
	
	@GetMapping(value="/getusertype/{id}",name = "getidUserType")
	public ResponseEntity<UserType> getidUserTypebyid(@PathVariable long id) {
		Optional<UserType> userType =userTypeService.getUserType(id);
		return new ResponseEntity<>(userType.get(), HttpStatus.OK);
		
	}

	}
