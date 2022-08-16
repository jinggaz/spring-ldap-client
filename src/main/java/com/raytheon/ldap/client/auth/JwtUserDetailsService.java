package com.raytheon.ldap.client.auth;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${authenticate.service.url}")
	private String authServer;
	
	public LdapUser loadUserByUsername(String token, String username) throws UsernameNotFoundException, URISyntaxException {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer "+ token);
		
		HttpEntity<?> httpEntity = new HttpEntity<>(headers);
		ParameterizedTypeReference<LdapUser> typeRef = new ParameterizedTypeReference<LdapUser>() {};

		final ResponseEntity<LdapUser> user = restTemplate.exchange(authServer + "/users/user/{email}", HttpMethod.GET, httpEntity, typeRef, username);
		
		return user.getBody();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return null;
	}

}
