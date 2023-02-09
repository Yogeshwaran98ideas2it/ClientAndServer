package com.example.healthcare.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.healthcare.service.UserService;
import com.example.healthcare.userandroledto.ResponseDto;
import com.example.healthcare.userandroledto.UserAndRoleDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import jakarta.validation.Valid;

// TODO: Auto-generated Javadoc
/**
 * The Class UserController.
 */
@RestController

@RefreshScope

@RequestMapping("users/")
public class UserController {


	/** The user service. */
	@Autowired
	UserService userService;
	
	/** The value. */
	@Value("${data}")
	private String value;
	
	 /** The logger. */
 	Logger logger = LoggerFactory.getLogger(UserController.class);

	/**
	 * Gets the all user.
	 *
	 * @return the all user
	 */
	@GetMapping
	public ResponseDto getAllUser() {
		List<UserAndRoleDto> userAndRoleDto= userService.getAllUser();
		logger.info("value:"+value);;
		ResponseDto response=new ResponseDto();
		if(!userAndRoleDto.isEmpty()) {
			response.setSuccess(userAndRoleDto, "List retrieved Successfully");
		}
		return response;
				
	}
	
	/**
	 * List pageable.
	 *
	 * @param pageNo the page no
	 * @param pageSize the page size
	 * @return the page
	 */
	@GetMapping("{pageNo}/{pageSize}")
	public Page<UserAndRoleDto> listPageable(@PathVariable int pageNo, @PathVariable int pageSize) {
		return userService.listPage(pageNo, pageSize);
		
	}
	
	/**
	 * Gets the user by id.
	 *
	 * @param id the id
	 * @return the user by id
	 */
	@GetMapping("{id}")
	public ResponseDto getUserById(@PathVariable Integer id) {
		Optional<UserAndRoleDto> userAndRoleDto=userService.getUserById(id);
		ResponseDto response=new ResponseDto();
		if(!userAndRoleDto.isEmpty()) {
			response.setSuccess(userAndRoleDto, "User retrieved Successfully");
		}
		return response;
	}

	/**
	 * Creates the user.
	 *
	 * @param userAndRoleDto the user and role dto
	 */
	@PostMapping
	public void createUser( @Valid @RequestBody UserAndRoleDto userAndRoleDto) {
		userService.createUser(userAndRoleDto);
	}
	
	
	/**
	 * Delete user by id.
	 *
	 * @param id the id
	 */
	@DeleteMapping("{id}")
	public void deleteUserById(@PathVariable Integer id) {
		userService.deleteUserById(id);	
	}
	
	/**
	 * Update user by id.
	 *
	 * @param userAndRoleDto the user and role dto
	 * @param id the id
	 */
	@PutMapping("{id}")
	public void updateUserById(@Valid @RequestBody UserAndRoleDto userAndRoleDto, @PathVariable Integer id) {
		userService.updateUserById(userAndRoleDto, id);
		
	}
	
	/**
	 * Patch user by id.
	 *
	 * @param id the id
	 * @param JsonPatch the json patch
	 * @throws JsonPatchException the json patch exception
	 * @throws JsonProcessingException the json processing exception
	 */
	@PatchMapping(path="{id}",consumes = "application/json-patch+json")
	public void patchUserById(@PathVariable Integer id, @RequestBody JsonPatch JsonPatch) throws JsonPatchException, JsonProcessingException {
		
		 userService.patchUserById(JsonPatch, id);
	}
	
}
