package com.tis.in.BanX.validator;

import javax.validation.constraints.NotEmpty;

public class PhoneNumber {

	@NotEmpty
	private String value;

	@NotEmpty
	private String locale;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

}