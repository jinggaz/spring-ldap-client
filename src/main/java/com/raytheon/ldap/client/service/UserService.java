package com.raytheon.ldap.client.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.raytheon.ldap.client.dto.LoginForm;
import com.raytheon.ldap.client.dto.ResultForm;

@Component
public class UserService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${authenticate.service.url}")
	private String authServer;

	private static final String LOGIN_PATH = "/users/login";
	
	public ResultForm login(LoginForm loginForm) throws URISyntaxException {
	
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
	
		URI uri = new URI(authServer + LOGIN_PATH);
		HttpEntity<LoginForm> httpEntity = new HttpEntity<>(loginForm, headers);
	
		ResultForm resultForm = restTemplate.postForObject(uri, httpEntity, ResultForm.class);
	
		return resultForm;
	}

}
