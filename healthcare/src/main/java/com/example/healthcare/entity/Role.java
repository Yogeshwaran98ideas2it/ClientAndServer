package com.example.healthcare.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new role.
 */
@Data
@Entity
@Table(name="role")

public class Role {	
	
	/** The role id. */
	@Id
	@Column(name="role_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private int roleId;
	
	/** The role name. */
	@Column(name="role_name")
	private String roleName;
	
	/** The timing. */
	@Column(name="shift")
	private String timing;
	
	


	
		
}
