package com.tis.in.BanX.common;

public class ResponseBuilder {

	private int responseCode;
	private String responseMessage;
	private String timeStamp;

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int created) {
		this.responseCode = created;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
}
