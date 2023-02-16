package com.example.healthcare.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.healthcare.entity.User;
import com.example.healthcare.userandroledto.UserAndRoleDto;

/**
 * The Interface UserRepository.
 */
/**
 * @author Yogesh
 *
 */


public interface UserRepository extends JpaRepository<User, Integer> {

	
	User findByUserName(String userName);
}
