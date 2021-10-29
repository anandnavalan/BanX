package com.tis.in.BanX.exception.model;

public class ResourceNotFoundException extends BaseBusinessException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String code) {
		super(code);
	}

}
