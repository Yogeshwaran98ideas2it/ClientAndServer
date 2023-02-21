/**
 * 
 */
package com.example.healthcare.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.example.healthcare.service.UserService;

/**
 * @author Yogesh
 *
 */
@Configuration
@EnableWebSecurity
public class SecureConfig {

	@Autowired
	UserService userService;
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
	}
	
	
	
}
