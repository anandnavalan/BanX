package com.tis.in.BanX.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

	public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", responseObj);
		map.put("message", message);
		map.put("statusCode", status.value());
		map.put("status", status);

		return new ResponseEntity<>(map, status);
	}

	public static ResponseEntity<Object> generateResponse(String message, HttpStatus status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", null);
		map.put("message", message);
		map.put("statusCode", status.value());
		map.put("status", status);

		return new ResponseEntity<>(map, status);
	}
}