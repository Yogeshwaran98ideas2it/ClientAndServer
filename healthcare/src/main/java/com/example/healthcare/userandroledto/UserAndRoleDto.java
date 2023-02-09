package com.example.healthcare.userandroledto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import lombok.Data;


// TODO: Auto-generated Javadoc
/**
 * Instantiates a new user and role dto.
 */
@Data

public class UserAndRoleDto {

	/** The user id. */
	private int userId;
	
	/** The user name. */
	private String userName;
	
	/** The created at. */
	private LocalDateTime createdAt;
	
	/** The updated at. */
	private LocalDateTime updatedAt;
	
	/** The created by. */
	private String createdBy;
	
	/** The updated by. */
	private String updatedBy;
	
	
	
	/** The access. */
	private String access;
	
	/** The role. */
	private RoleDto role;
	
	
	
	
	

	
	
	
	
}
