/**
 * 
 */
package com.example.healthcare.jwt;

import java.io.IOException;
import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.healthcare.service.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Yogesh
 *
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserService userService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authorizationHeader=request.getHeader("Authorization");//bearer 
		String token = null;
		String userName = null;
		if(authorizationHeader !=null && authorizationHeader.startsWith("Bearer")) {
			token=authorizationHeader.substring(7);
			userName=jwtUtil.extractUsername(token);
		}
		if(userName!= null && SecurityContextHolder.getContext().getAuthentication() == null) {
			System.out.println("inside filter method");
			UserDetails userDetails=this.userService.loadUserByUsername(userName);
			if(jwtUtil.validateToken(token, userDetails)) {
				System.out.println("inside filter method2");
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
			
		}
		System.out.println("filter method");
		
		filterChain.doFilter(request, response);
		System.out.println("after filter method");
		
		
		
		
	}

}
