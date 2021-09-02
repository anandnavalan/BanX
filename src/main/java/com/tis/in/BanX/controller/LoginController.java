package com.tis.in.BanX.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@RequestMapping(value =  "/login", method = RequestMethod.POST)
	private String login() {
		return "Logged In";
	}
}
