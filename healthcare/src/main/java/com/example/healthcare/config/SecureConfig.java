/**
 * 
 */
package com.example.healthcare.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.healthcare.jwt.JwtFilter;
import com.example.healthcare.service.UserService;

/**
 * @author Yogesh
 *
 */
@Configuration
@EnableWebSecurity

public class SecureConfig {
//
//	@Autowired
//	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	UserService userService;
	
	@Autowired
	private JwtFilter jwtFilter;
	
	public final String[] allowedApi= {
			
			"/users",
			
			"/users/authenticate",
			"/users/{id}",
			"/users/access",
			"/users/timing",
			"/users/role",
			"/users/name",
			"/users/{pageNo}/{pageSize}"
		
	};
	
	

	/*
	 * @Override public void configure(AuthenticationManagerBuilder  	
	 * authenticationManagerBuilder) throws Exception {		//Commenting for deprecation
	 * authenticationManagerBuilder.userDetailsService(userDetailsService).
	 * passwordEncoder(passwordEncoder()); }	//Commenting for deprecation
	 */

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/*
	 * @Bean(name=BeanIds.AUTHENTICATION_MANAGER) public AuthenticationManager
	 * authenticationManagerBean() throws Exception{ return
	 * super.authenticationManagerBean(); }	//Commenting for deprecation
	 */
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
	
	
	@Bean
	  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
//		.cors()
//		.and()
		.csrf()
		.disable()
		
		.authorizeHttpRequests()
		.requestMatchers("/users/authenticate")
		.permitAll()
//		.requestMatchers("/users")
//		.permitAll()
		.requestMatchers(allowedApi)
//		.permitAll()
		.hasAnyAuthority("Engineer",           "Nurse")
		.anyRequest()
		.authenticated()
		.and()
		.exceptionHandling()
//		.authenticationEntryPoint(jwtAuthenticationEntryPoint)
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	
		;
		
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
		
		
	}
	

}
