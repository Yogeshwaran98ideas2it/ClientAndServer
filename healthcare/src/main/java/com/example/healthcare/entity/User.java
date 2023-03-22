package com.example.healthcare.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
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
@NamedQueries({@NamedQuery(name="User.findByName",query="from User where userName=?1"),@NamedQuery(name="User.findByUserAccess",query="from User where access=?1")})

public class User implements UserDetails{

	/**
	 * 
	 */
	

	/**
	 * 
	 */
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -3565467283407099501L;

	/** The user id. */
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int userId;
	
	/** The user name. */
	
	@Column(name="user_name")
	private String userName;
	
	private String password;
	
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



	@Override
	public String getUsername() {
		
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return false;
	}

	@Override
	public boolean isEnabled() {
		
		return false;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
