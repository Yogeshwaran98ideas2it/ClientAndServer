package com.example.healthcare.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Instantiates a new role.
 */
/**
 * @author Yogesh
 *
 */
@Data
@Entity
@Table(name = "role")
@NamedQuery(name="Role.findByName",query="from Role where roleName=?1")
public class Role implements Serializable{

	/** The role id. */
	@Id
	@Column(name = "role_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private int roleId;

	/** The role name. */
	@Column(name = "role_name")
	private String roleName;

	/** The timing. */
	@Column(name = "shift")
	private String timing;

}
