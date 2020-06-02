package io.rapyuta.POJO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	
	@JsonProperty("username")
	String username;
	
	@JsonProperty("email")
	String email;
	

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
