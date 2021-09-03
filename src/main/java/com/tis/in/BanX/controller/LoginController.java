package com.tis.in.BanX.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tis.in.BanX.domain.User;
import com.tis.in.BanX.model.LoginRequest;
import com.tis.in.BanX.service.LoginService;

@RestController
public class LoginController {

	@Autowired
	LoginService loginService;
	
	@RequestMapping(value =  "/login", method = RequestMethod.POST)
	private ResponseEntity<User> login(@RequestBody @Valid LoginRequest loginRequest) {
		
		Optional<User> user = loginService.login(loginRequest);
		return new ResponseEntity<User>(Optional.ofNullable(user.get()).get(), HttpStatus.OK);
	}
}
