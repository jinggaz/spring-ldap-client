package com.raytheon.ldap.client.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UnauthenticatedUserException extends AuthenticationException {

	private static final long serialVersionUID = -6338370840133073270L;

	private static final String ERR_MSG = "UnAuthonticate User";

	public UnauthenticatedUserException() {
		super(ERR_MSG);
	}

}
