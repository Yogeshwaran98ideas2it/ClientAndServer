package com.example.healthcare.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.healthcare.userandroledto.UserAndRoleDto;

import jakarta.persistence.Column;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.Table;
import lombok.Data;


/**
 * 
 * Instantiates a new user.
 */
/**
 * @author Yogesh
 *
 */
@Data
@Entity
@Table(name="user")
@EntityListeners(AuditingEntityListener.class)
@NamedQuery(name="User.findByName",query="from User where userName=?1")
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
