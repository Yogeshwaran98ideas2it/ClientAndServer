package com.example.healthcare.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.healthcare.entity.User;
import com.example.healthcare.mapper.CustomModelMapping;
import com.example.healthcare.repository.UserRepository;
import com.example.healthcare.userandroledto.UserAndRoleDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

// TODO: Auto-generated Javadoc
/**
 * The Class UserService.
 */
@Service

public class UserService implements UserInterface {

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;

	/** The model mapper. */
	@Autowired
	private CustomModelMapping modelMapper;
	
	/** The object mapper. */
	@Autowired
	private ObjectMapper objectMapper;
	
	

	/**
	 * Gets the all user.
	 *
	 * @return the all user
	 */
	@Override
	@Transactional
	public List<UserAndRoleDto> getAllUser() {
		/*
		 * modelMapper.getConfiguration() .setFieldMatchingEnabled(true)
		 * .setFieldAccessLevel(AccessLevel.PRIVATE);
		 * 
		 * 
		 * List<UserAndRoleDto> listUser = new ArrayList<UserAndRoleDto>();
		 * 
		 * List<User> listSavedUser = userRepository.findAll();
		 * System.out.println("entity:: " + listSavedUser);
		 * 
		 * listSavedUser.stream().forEach((userList) -> {
		 * 
		 * UserAndRoleDto obj = modelMapper.map(userList, UserAndRoleDto.class);
		 * 
		 * List<UserAndRoleDto> dtos = listSavedUser .stream() .map(user ->
		 * modelMapper.map(user, UserAndRoleDto.class)) .collect(Collectors.toList());
		 * 
		 * 
		 * listUser.add(modelMapper.map(user, UserAndRoleDto.class));
		 * System.out.println("inside loop:: " + user);
		 * System.out.println("inside loop listUser:: " + listUser);
		 * 
		 * 
		 * 
		 * 
		 * System.out.println("Listuser:: " + listUser);
		 */
		return modelMapper.mapList(userRepository.findAll(), UserAndRoleDto.class);
	}

	/**
	 * Creates the user.
	 *
	 * @param userAndRoleDto the user and role dto
	 * @return the optional
	 */
	@Override
	@Transactional
	public Optional<UserAndRoleDto> createUser(UserAndRoleDto userAndRoleDto) {
		User user = modelMapper.map(userAndRoleDto, User.class);
		User savedUser = userRepository.save(user);
		return Optional.ofNullable(modelMapper.map(savedUser, UserAndRoleDto.class));
	}

	/**
	 * Gets the user by id.
	 *
	 * @param userId the user id
	 * @return the user by id
	 */
	@Override
	@Transactional
	public Optional<UserAndRoleDto> getUserById(Integer userId) {
		System.out.println(userId);
		System.out.println(userRepository.findById(userId).get());
		UserAndRoleDto userAndRoleDto = modelMapper.map(userRepository.findById(userId).get(), UserAndRoleDto.class);
		System.out.println(userAndRoleDto.toString());
		return Optional.ofNullable(userAndRoleDto);
	}

	/**
	 * Delete user by id.
	 *
	 * @param userId the user id
	 */
	@Override
	@Transactional
	public void deleteUserById(Integer userId) {
		userRepository.deleteById(userId);

	}

	/**
	 * Update user by id.
	 *
	 * @param userAndRoleDto the user and role dto
	 * @param userId the user id
	 */
	@Override
	@Transactional
	public void updateUserById(UserAndRoleDto userAndRoleDto, Integer userId) {

		User user = modelMapper.map(userAndRoleDto, User.class);
		Optional<User> userPresent = userRepository.findById(userId);
		if (!userPresent.isEmpty()) {
			user.setCreatedAt(userPresent.get().getCreatedAt());
			userRepository.save(user);
		}

	}

	/**
	 * Patch user by id.
	 *
	 * @param jsonPatch the json patch
	 * @param userId the user id
	 * @return the optional
	 * @throws JsonPatchException the json patch exception
	 * @throws JsonProcessingException the json processing exception
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	@Override
	public Optional<UserAndRoleDto> patchUserById( JsonPatch jsonPatch, Integer userId) throws JsonPatchException, JsonProcessingException, IllegalArgumentException {
		
		Optional<User> user=userRepository.findById(userId);
		User applyPatch=applyPatchToCustomer(jsonPatch,user.get());
		if(user.isPresent()) {
			return Optional.ofNullable(modelMapper.map(userRepository.save(applyPatch)	, UserAndRoleDto.class));
		}
		else
		{
			return Optional.empty();
		}
		
		/*
		 * User user=userRepository.findById(userId).get(); JsonNode
		 * patched=jsonPatch.apply(objectMapper.convertValue(user, JsonNode.class));
		 * user=objectMapper.treeToValue(patched, User.class);
		 * userRepository.save(user);
		 */
		/*
		 * User user = modelMapper.map(userAndRoleDto, User.class); boolean needUpdate =
		 * false; if (StringUtils.hasLength(userAndRoleDto.getUserName())) {
		 * user.setUserName(userAndRoleDto.getUserName()); needUpdate = true; } if
		 * (StringUtils.hasLength(userAndRoleDto.getUpdatedBy())) {
		 * user.setUpdatedBy(userAndRoleDto.getUpdatedBy()); needUpdate = true; } if
		 * (StringUtils.hasLength(userAndRoleDto.getAccess())) {
		 * user.setAccess(userAndRoleDto.getAccess()); needUpdate = true; } if
		 * (StringUtils.hasLength(userAndRoleDto.getRole().getRoleName())) {
		 * user.getRole().setRoleName(userAndRoleDto.getRole().getRoleName());
		 * needUpdate = true; } if
		 * (StringUtils.hasLength(userAndRoleDto.getRole().getTiming())) {
		 * user.getRole().setTiming(userAndRoleDto.getRole().getTiming()); needUpdate =
		 * true; }
		 * 
		 * if (needUpdate) { userRepository.save(user); }
		 */	
		}

	/**
	 * Apply patch to customer.
	 *
	 * @param jsonPatch the json patch
	 * @param user the user
	 * @return the user
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws JsonPatchException the json patch exception
	 * @throws JsonProcessingException the json processing exception
	 */
	private User applyPatchToCustomer(JsonPatch jsonPatch, User user) throws IllegalArgumentException, JsonPatchException, JsonProcessingException {
		ObjectMapper om=new ObjectMapper();
		//ObjectMapper om=JsonMapper.builder().findAndAddModules().build();
		om.registerModule(new JavaTimeModule());//Jackson will serialize the Date to a timestamp format by default (number of milliseconds since the date, UTC).
		om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);//so disabling the feature will handle the error when localdatetime is used
		JsonNode patched= jsonPatch.apply(//apply method will apply the operations to target.
				om.convertValue(			//convert user to jsonnode class
						user, JsonNode.class
						)
				);
		User updated = om.treeToValue(patched, User.class);//binds the data in the patched com.fasterxml.jackson.databind.JsonNode to the Customer type
        return updated;
	}

	/**
	 * List page.
	 *
	 * @param pageNo the page no
	 * @param pageSize the page size
	 * @return the page
	 */
	public Page<UserAndRoleDto> listPage(int pageNo, int pageSize) {
		System.out.println("PageNO:" + pageNo + ",pageSize:" + pageSize);

		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("userName").ascending());

		Page<User> pagedResult = userRepository.findAll(paging);

		System.out.println("PagedResult:" + pagedResult.toString());

		 List <User> listSavedUser= pagedResult.getContent();
		// System.out.println(listSavedUser.toString());
		// List <UserAndRoleDto> listUser=new ArrayList<UserAndRoleDto>();

//		listSavedUser.stream().forEach((userList)->listUser.add(Usermapper.autoMAPPER.mapToUserDto(userList)));
//		System.out.println(listUser.toString());
		Page<UserAndRoleDto> pageUser = new PageImpl<UserAndRoleDto>(
				modelMapper.mapList(listSavedUser, UserAndRoleDto.class));

		System.out.println(pageUser.getContent().toString());
		return pageUser;
	}

	
}
