package com.marketplace.notificationservice.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegistrationDataTemplate {
	
	@JsonProperty("token")
	private String token;
	@JsonProperty("username")
	private String username;
	@JsonProperty("email")
	private String email;
	@JsonProperty("id")
	private UUID id;
	@Override
	public String toString() {
		return "RegistrationDataTemplate [token=" + token + ", username=" + username + ", email=" + email + "]";
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
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	

}
