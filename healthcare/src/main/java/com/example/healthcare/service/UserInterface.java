package com.example.healthcare.service;

import java.util.List;
import java.util.Optional;

import com.example.healthcare.userandroledto.UserAndRoleDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

/**
 * The Interface UserInterface.
 */
/**
 * @author Yogesh
 *
 */
public interface UserInterface {

	/**
	 * Gets the all user.
	 *
	 * @return the all user
	 */
	public List<UserAndRoleDto> getAllUser();

	/**
	 * Creates the user.
	 *
	 * @param userdto the userdto
	 * @return the optional
	 */
	public Optional<UserAndRoleDto> createUser(UserAndRoleDto userdto);

	/**
	 * Gets the user by id.
	 *
	 * @param id the id
	 * @return the user by id
	 */
	public Optional<UserAndRoleDto> getUserById(Integer id);

	/**
	 * Delete user by id.
	 *
	 * @param id the id
	 */
	public void deleteUserById(Integer id);

	/**
	 * Update user by id.
	 *
	 * @param user   the user
	 * @param userId the user id
	 */
	public void updateUserById(UserAndRoleDto user, Integer userId);

	/**
	 * Patch user by id.
	 *
	 * @param jsonPatch the json patch
	 * @param userId    the user id
	 * @return the optional
	 * @throws JsonPatchException       the json patch exception
	 * @throws JsonProcessingException  the json processing exception
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	// public void patchUserById(UserAndRoleDto userAndRoleDto, Integer userId);
	public Optional<UserAndRoleDto> patchUserById(JsonPatch jsonPatch, Integer userId)
			throws JsonPatchException, JsonProcessingException, IllegalArgumentException;
}
