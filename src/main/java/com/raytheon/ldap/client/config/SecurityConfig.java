package com.raytheon.ldap.client.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.raytheon.ldap.client.auth.JwtTokenFilter;
import com.raytheon.ldap.client.auth.JwtUserDetailsService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	
	@Autowired
	private JwtTokenFilter ldapTokenFilter;
	
	private static final String[] AUTH_WHITELIST = { 
			"/authenticate",
			"/swagger-resources/**",
			"/swagger-ui/**",
			"/v3/api-docs",
			"/webjars/**" };

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	


	@Override
	public void configure(WebSecurity webSecurity) throws Exception {
		webSecurity.ignoring().antMatchers(AUTH_WHITELIST);
	}

	public void configure(HttpSecurity httpSecurity) throws Exception {
  
		  httpSecurity
		  	.csrf().disable()
		  	.authorizeRequests()
		  	.antMatchers(HttpMethod.POST, "/auth/login").permitAll()
		  	.antMatchers(HttpMethod.GET, "/auth/test").hasRole("PEOPLE")
		  	//.antMatchers(HttpMethod.GET, "/users/test").hasRole(people)
		  	.and()
		  	.sessionManagement()
		  	.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		  	.and()
		  	.addFilterBefore(ldapTokenFilter,  UsernamePasswordAuthenticationFilter.class)
		  	.formLogin().disable();
	  }

}
