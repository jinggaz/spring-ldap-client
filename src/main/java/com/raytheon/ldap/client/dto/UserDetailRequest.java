package com.raytheon.ldap.client.dto;

import javax.validation.constraints.NotBlank;

public class UserDetailRequest {

	@NotBlank
	private String email;

	public UserDetailRequest(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

}
