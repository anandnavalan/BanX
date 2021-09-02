package com.tis.in.BanX.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tis.in.BanX.domain.User;

@RestController
public class UserController {

	@RequestMapping(value = "/createuser", name = "createUser", method = RequestMethod.POST)
	private ResponseEntity<User> createUser(@RequestBody @Valid User user) {
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
}
