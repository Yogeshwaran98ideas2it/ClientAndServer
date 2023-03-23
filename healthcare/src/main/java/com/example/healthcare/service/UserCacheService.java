/**
 * 
 */
package com.example.healthcare.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.healthcare.dto.UserAndRoleDto;
import com.example.healthcare.entity.User;
import com.example.healthcare.mapper.CustomModelMapping;

import com.example.healthcare.repository.IUsersRepository;

/**
 * @author Yogesh
 *
 */
@Repository
public class UserCacheService {
	
	public static final String HASH_KEY="User";
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	//private HashOperations hashOperations;
	
	
	PasswordEncoder passwordEncoder;

	public UserCacheService() {
		
		//hashOperations=redisTemplate.opsForHash();
		this.passwordEncoder=new BCryptPasswordEncoder();
	}

	
	public UserAndRoleDto save(UserAndRoleDto user) {
		String encodedPassword=this.passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		redisTemplate.opsForHash().put(HASH_KEY, user.getUserId(), user);
//		User user = modelMapper.map(userAndRoleDto, User.class);
//		User savedUser = userRepository.save(user);
//		String encodedPassword=this.passwordEncoder.encode(user.getPassword());
//		user.setPassword(encodedPassword);
//		return Optional.ofNullable(modelMapper.map(savedUser, UserAndRoleDto.class));
		return user;
		
	}

	
	public List<UserAndRoleDto> findAll() {

		return redisTemplate.opsForHash().values(HASH_KEY);
	}

	
	public UserAndRoleDto findById(int id) {

		return (UserAndRoleDto)redisTemplate.opsForHash().get(HASH_KEY, id);
	}

}
