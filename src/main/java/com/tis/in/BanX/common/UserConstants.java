package com.tis.in.BanX.common;

public enum UserConstants {
	USERNAME("username"), REALMS("realms"), ADMIN("admin"), USERS("users"), CLIENTS("clients"), ROLES("roles"),
	BEARER("Bearer "), RCMID("rcm_id"), LOGOUT("logout"), LOCALE("US"), MESSAGEDIR("messages/message"),
	PASSWORD("password"), FIRST("first"), MAX("max"), COUNT("count");

	private String constants;

	UserConstants(String constants) {
		this.constants = constants;
	}

	public String getConstants() {
		return constants;
	}
}
