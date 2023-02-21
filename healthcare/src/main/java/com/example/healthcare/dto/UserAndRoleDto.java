package com.example.healthcare.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;



/**
 * Instantiates a new user and role dto.
 */
/**
 * @author Yogesh
 *
 */
@Data

public class UserAndRoleDto {

	/** The user id. */
	@NotNull
	private int userId;
	
	/** The user name. */
	@NotNull
	private String userName;
	
	@NotNull
	private String password;
	
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
