package com.example.healthcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.healthcare.entity.User;

/**
 * The Interface UserRepository.
 */
/**
 * @author Yogesh
 *
 */


public interface IUsersRepository extends JpaRepository<User, Integer> {

	
	User findByUserName(String userName);
	List<User> findByUserAccess(String access);
	
}
