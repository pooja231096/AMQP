package com.marketplace.notificationservice.model;

public class MailResponse {
	private String message;
	private boolean status;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public MailResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MailResponse(String message, boolean status) {
		super();
		this.message = message;
		this.status = status;
	}

}