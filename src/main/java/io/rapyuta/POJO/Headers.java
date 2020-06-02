package io.rapyuta.POJO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Headers {
	@JsonProperty("Authorization")
	private String authorization;

	public String getAuthorization() {
		return authorization;
	}

	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}
}