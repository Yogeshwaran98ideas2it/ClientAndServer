package com.example.healthcare.dto;

import java.io.Serializable;

import lombok.Data;


/**
 * Instantiates a new role dto.
 */
/**
 * @author Yogesh
 *
 */
@Data

public class RoleDto implements Serializable{

	/** The role id. */
	private int roleId;

	/** The timing. */
	private String timing;
	
	/** The role name. */
	private String roleName;

}
