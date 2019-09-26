package com.marketplace.notificationservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Notification {
	
	
	@Override
	public String toString() {
		return "Notification [notificationId=" + notificationId + ", message=" + message + "]";
	}

	@JsonProperty("notificationId")
	private Integer notificationId;
	
	@JsonProperty("message")
	private String message;
	

	public Integer getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Integer notificationId) {
		this.notificationId = notificationId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message; 
	}

	public Notification(Integer notificationId, String message) {
		super();
		this.notificationId = notificationId;
		this.message = message;
	}
	

}
