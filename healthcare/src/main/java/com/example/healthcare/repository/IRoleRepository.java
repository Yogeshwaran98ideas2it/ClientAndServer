package com.example.healthcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.healthcare.entity.Role;

/**
 * The Interface RoleRepository.
 */
/**
 * @author Yogesh
 *
 */

public interface IRoleRepository extends JpaRepository<Role, Integer> {

	Role findByRoleName(String roleName);
	
	@Query(value="SELECT * FROM role WHERE shift=?1",nativeQuery = true)
	List<Role> findByTiming(String timing);
}
