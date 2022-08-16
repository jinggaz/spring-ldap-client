package com.raytheon.ldap.client.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -9047302708500957031L;

	private static final String ERR_MSG = "No user exist";

	public UserNotFoundException() {
		super(ERR_MSG);
	}

}
