package com.marketplace.notificationservice.model;

import java.util.UUID;

public class RabbitMessage {
	private String token;
	private String username;
	private String email;
	private UUID userId;
	
	public RabbitMessage(String token, String username, String email,UUID userId) {
		super();
		this.token = token;
		this.username = username;
		this.email = email;
		this.userId=userId;
	}

	@Override
	public String toString() {
		return "RabbitMessage [token=" + token + ", username=" + username + ", email=" + email + ", userId=" + userId
				+ "]";
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
