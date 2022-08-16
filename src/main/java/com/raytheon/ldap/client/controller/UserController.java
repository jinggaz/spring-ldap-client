package com.raytheon.ldap.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raytheon.ldap.client.dto.LoginForm;
import com.raytheon.ldap.client.dto.ResultForm;
import com.raytheon.ldap.client.service.UserService;

@RestController
@RequestMapping("/auth")
public class UserController {

	@Autowired
	private UserService userServie;

	@GetMapping("/test")
	public String test() {

		return "Hello World!";
	}

	@PostMapping("/login")
	public ResponseEntity<ResultForm> login(@RequestBody LoginForm loginForm) throws Exception {

		final ResultForm resultForm = userServie.login(loginForm);

		return ResponseEntity.ok(resultForm);
	}

}
