package com.example.healthcare.userandroledto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


// TODO: Auto-generated Javadoc
/**
 * Instantiates a new user and role dto.
 */
@Data

public class UserAndRoleDto {

	/** The user id. */
	@NotNull
	private int userId;
	
	/** The user name. */
	@NotNull
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
	@NotNull
	private String access;
	
	/** The role. */
	@NotNull
	private RoleDto role;
	
	
	
	
	

	
	
	
	
}
