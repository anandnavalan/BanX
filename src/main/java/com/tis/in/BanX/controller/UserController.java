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

import com.tis.in.BanX.common.CommonMessageConstants;
import com.tis.in.BanX.common.ErrorConstants;
import com.tis.in.BanX.common.ResponseBuilder;
import com.tis.in.BanX.common.Utility;
import com.tis.in.BanX.domain.AuditInfo;
import com.tis.in.BanX.domain.User;
import com.tis.in.BanX.domain.UserType;
import com.tis.in.BanX.exception.model.ResourceCreationException;
import com.tis.in.BanX.exception.model.ResourceNotFoundException;
import com.tis.in.BanX.handler.ResponseHandler;
import com.tis.in.BanX.service.UserService;

@RestController
public class UserController {
	@Autowired
	UserService userService;

	@RequestMapping(value = "/createuser", name = "createUser", method = RequestMethod.POST)
	private ResponseEntity<ResponseBuilder> createUser(@RequestBody @Valid User user) throws ResourceCreationException {

		Optional<User> optionalUser = userService.getUser(user.getUserName());

		if (optionalUser.isPresent()) {
			throw new ResourceCreationException(ErrorConstants.ERROR_USER_EXISTS);
		} else {

			AuditInfo auditInfo = new AuditInfo();

			auditInfo.setCreatedBy("system");
			auditInfo.setCreatedDate(Utility.getSQLDate());
			auditInfo.setModifiedBy("system");
			auditInfo.setModifiedDate(Utility.getSQLDate());

			user.setAuditInfo(auditInfo);

			User createUser = userService.addOrUpdateUser(user);

			ResponseBuilder builder = Utility.responseBuilder(
					Utility.getLocalizedMessage(CommonMessageConstants.SUCCESS_USER_CREATION),
					HttpStatus.CREATED.value());

			return new ResponseEntity<>(builder, HttpStatus.CREATED);
		}
	}

	@RequestMapping(value = "/updateuser", name = "updateUser", method = RequestMethod.PUT)
	private ResponseEntity<ResponseBuilder> updateUser(@RequestBody @Valid User user) throws ResourceNotFoundException {

		Optional<User> optionalUser = userService.getUser(user.getUserId());

		if (optionalUser.isPresent()) {
			AuditInfo auditInfo = new AuditInfo();
			auditInfo.setModifiedBy("system");
			auditInfo.setModifiedDate(Utility.getSQLDate());

			user.setAuditInfo(auditInfo);

			user = userService.addOrUpdateUser(user);
			ResponseBuilder builder = Utility.responseBuilder(
					Utility.getLocalizedMessage(CommonMessageConstants.SUCCESS_USER_UPDATION),
					HttpStatus.CREATED.value());

			return new ResponseEntity<>(builder, HttpStatus.CREATED);

		} else {
			throw new ResourceNotFoundException(ErrorConstants.ERROR_USER_NOT_EXISTS);
		}
	}

	@RequestMapping(value = "/getusers", name = "getUser", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getUser() {
		List<User> users = userService.getAllUser();
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}

	@GetMapping(value = "/getuser/{id}", name = "getIdUser")
	public ResponseEntity<User> getidUserbyid(@PathVariable long id) {
		Optional<User> user = userService.getUser(id);
		return ResponseEntity.status(HttpStatus.OK).body(user.get());
	}

}
