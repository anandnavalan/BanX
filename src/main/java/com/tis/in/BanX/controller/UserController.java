package com.tis.in.BanX.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tis.in.BanX.domain.User;
import com.tis.in.BanX.domain.UserType;
import com.tis.in.BanX.handler.ResponseHandler;
import com.tis.in.BanX.service.UserService;

@RestController
public class UserController {
	@Autowired
	UserService userService;

	@RequestMapping(value = "/createuser", name = "createUser", method = RequestMethod.POST)
	private ResponseEntity<Object> createUser(@RequestBody @Valid User user) {
		Optional<User> optionalUser = userService.getUser(user.getUserName());
		if (optionalUser.isPresent()) {
			return ResponseHandler.generateResponse("Username already exists in our system", HttpStatus.CONFLICT);
		} else {

			User createUser = userService.addOrUpdateUser(user);
			return ResponseHandler.generateResponse("User Created Successfully", HttpStatus.CREATED, createUser);
		}
	}

	@RequestMapping(value = "/updateuser", name = "updateUser", method = RequestMethod.PUT)
	private ResponseEntity<Object> updateUser(@RequestBody @Valid User user) {

		Optional<User> optionalUser = userService.getUser(user.getUserId());
		if (optionalUser.isPresent()) {
			return ResponseHandler.generateResponse("UserType not exists in our system", HttpStatus.NOT_FOUND);

		} else {
			user = userService.addOrUpdateUser(user);
			return ResponseHandler.generateResponse("UserType updated Successfully", HttpStatus.OK, optionalUser);
		}
	}

	@RequestMapping(value = "/getuser", name = "getUser", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getUser() {

		List<User> User = userService.getAllUser();
		return new ResponseEntity<>(User, HttpStatus.OK);
	}

	@GetMapping(value = "/getuser/{id}", name = "getidUser")
	public ResponseEntity<User> getidUserbyid(@PathVariable long id) {
		Optional<User> user = userService.getUser(id);
		return new ResponseEntity<>(user.get(), HttpStatus.OK);
	}

}
