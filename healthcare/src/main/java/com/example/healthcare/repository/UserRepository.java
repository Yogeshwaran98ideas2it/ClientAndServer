package com.example.healthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.healthcare.entity.User;

/**
 * The Interface UserRepository.
 */
/**
 * @author Yogesh
 *
 */
public interface UserRepository extends JpaRepository<User, Integer> {

}
