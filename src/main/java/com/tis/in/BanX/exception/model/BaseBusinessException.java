package com.tis.in.BanX.exception.model;

public class BaseBusinessException extends Exception {
	
	/**
	 * Default serial version ID
	 */
	private static final long serialVersionUID = 1L;

	public BaseBusinessException(String code) {
        super(code);
    }


}
