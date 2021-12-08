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
import com.tis.in.BanX.domain.UserType;
import com.tis.in.BanX.exception.model.ResourceCreationException;
import com.tis.in.BanX.exception.model.ResourceNotFoundException;
import com.tis.in.BanX.handler.ResponseHandler;
import com.tis.in.BanX.service.UserTypeService;

@RestController
public class UserTypeController {

	@Autowired
	UserTypeService userTypeService;

	@RequestMapping(value = "/createusertype", name = "createUserType", method = RequestMethod.POST)
	private ResponseEntity<ResponseBuilder> createUserType(@RequestBody @Valid UserType userType)
			throws ResourceCreationException {

		Optional<UserType> optionalUserType = userTypeService.getUserType(userType.getUserTypeName());

		if (optionalUserType.isPresent()) {
			throw new ResourceCreationException(ErrorConstants.ERROR_USER_TYPE_EXISTS);
		} else {
			UserType createUserType = userTypeService.addOrUpdateUserType(userType);

			ResponseBuilder builder = Utility.responseBuilder(
					Utility.getLocalizedMessage(CommonMessageConstants.SUCCESS_USER_TYPE_CREATION),
					HttpStatus.CREATED.value());

			return new ResponseEntity<>(builder, HttpStatus.CREATED);
		}

	}

	@RequestMapping(value = "/updateusertype", name = "UpdateUserType", method = RequestMethod.PUT)
	private ResponseEntity<ResponseBuilder> updateUserType(@RequestBody @Valid UserType userType)
			throws ResourceNotFoundException, ResourceCreationException {
		
		Optional<UserType> optionalUserType = userTypeService.getUserType(userType.getUserTypeId());

		if (optionalUserType.isPresent()) {
			
			optionalUserType = userTypeService.getUserType(userType.getUserTypeName());
			
			if (optionalUserType.isPresent()) {
				throw new ResourceCreationException(ErrorConstants.ERROR_USER_TYPE_EXISTS);
			} else {
				userType = userTypeService.addOrUpdateUserType(userType);

				ResponseBuilder builder = Utility.responseBuilder(
						Utility.getLocalizedMessage(CommonMessageConstants.SUCCESS_USER_TYPE_UPDATION),
						HttpStatus.CREATED.value());

				return new ResponseEntity<>(builder, HttpStatus.CREATED);				
			}

		} else {
			throw new ResourceNotFoundException(ErrorConstants.ERROR_USER_TYPE_NOT_EXISTS);
		}
	}

	@RequestMapping(value = "/getusertypes", name = "getUserTypes", method = RequestMethod.GET)
	public ResponseEntity<List<UserType>> getUserTypes() {
		List<UserType> userTypes = userTypeService.getAllUserTypes();
		return ResponseEntity.status(HttpStatus.OK).body(userTypes);
	}

	@GetMapping(value = "/getusertype/{id}", name = "getidUserType")
	public ResponseEntity<UserType> getidUserTypebyid(@PathVariable long id) {
		Optional<UserType> userType = userTypeService.getUserType(id);
		return ResponseEntity.status(HttpStatus.OK).body(userType.get());
	}

}
