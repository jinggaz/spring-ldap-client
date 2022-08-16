package com.raytheon.ldap.client.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class TokenRefreshException extends RuntimeException {

	private static final long serialVersionUID = -2617063788463138110L;

	public TokenRefreshException(String token, String message) {
		super(String.format("Failed for [%s]: %s", token, message));
	}
}
