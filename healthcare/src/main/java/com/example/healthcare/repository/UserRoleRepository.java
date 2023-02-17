/**
 * 
 */
package com.example.healthcare.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.healthcare.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;


/**
 * The Class UserRoleRepository.
 *
 * @author Yogesh
 */
@Repository
public class UserRoleRepository {

	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;
	
	
	
	
	/**
	 * Find all users.
	 *
	 * @return the list
	 */
	public List<User> findAllUsers(){
		TypedQuery<User> query=entityManager.createQuery("select u from User u",User.class);
		return query.getResultList();
		
	}
	
}
