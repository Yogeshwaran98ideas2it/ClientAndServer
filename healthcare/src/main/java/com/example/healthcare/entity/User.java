package com.example.healthcare.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new user.
 */
@Data
@Entity
@Table(name="user")
@EntityListeners(AuditingEntityListener.class)
public class User {

	/** The user id. */
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int userId;
	
	/** The user name. */
	
	@Column(name="user_name")
	private String userName;
	
	/** The created at. */
	@CreatedDate
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	/** The updated at. */
	@LastModifiedDate
	@Column(name="updated_at")
	private LocalDateTime updatedAt;
	
	
	/** The created by. */
	@Column(name="created_by")
	private String createdBy;
	

	/** The updated by. */
	@Column(name="updated_by")
	private String updatedBy;
	
	/** The access. */
	private String access;
	
	/** The role. */
	@ManyToOne
	@JoinColumn(name="roleId")
	Role role;

		
}
