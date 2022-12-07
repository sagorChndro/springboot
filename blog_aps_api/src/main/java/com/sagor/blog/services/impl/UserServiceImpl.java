package com.sagor.blog.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sagor.blog.model.Role;
import com.sagor.blog.model.User;
import com.sagor.blog.payloadordto.Response;
import com.sagor.blog.payloadordto.UserDto;
import com.sagor.blog.repository.RoleRepository;
import com.sagor.blog.repository.UserRepository;
import com.sagor.blog.services.UserService;
import com.sagor.blog.utils.ResponseBuilder;
import com.sagor.blog.utils.UrlConstraint;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final ModelMapper modelMapper;
	private final PasswordEncoder passwordEncoder;
	private final RoleRepository roleRepository;
	private String root = "User";

	public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder,
			RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository = roleRepository;
	}

	@Override
	public Response createUser(UserDto userDto) {
		User user = modelMapper.map(userDto, User.class);
		user = userRepository.save(user);
		if (user != null) {
			return ResponseBuilder.getSuccessResponse(HttpStatus.CREATED, root + " created successfully", null);
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occur");
	}

	@Override
	public Response updateUser(UserDto userDto, Long userId) {
		User user = userRepository.findByuserIdAndIsActiveTrue(userId);
		if (user != null) {
			modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
			modelMapper.map(userDto, user);
			user = userRepository.save(user);
			if (user != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " updated successfully", null);
			}
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occur");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found");
	}

	@Override
	public Response delete(Long userId) {
		User user = userRepository.findByuserIdAndIsActiveTrue(userId);
		if (user != null) {
			user.setIsActive(false);
			user = userRepository.save(user);
			if (user != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " deleted successfully", null);
			}
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occur");

		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found for delete");
	}

	@Override
	public Response getUser(Long userId) {
		User user = userRepository.findByuserIdAndIsActiveTrue(userId);
		if (user != null) {
			modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
			UserDto userDto = modelMapper.map(user, UserDto.class);
			if (userDto != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " retrieve successfully", userDto);
			}
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occur");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " id not found");

	}

	@Override
	public Response getAllUsers() {
		List<User> user = userRepository.findAllByIsActiveTrue();
		List<UserDto> userDtos = this.getUsers(user);
		return ResponseBuilder.getSuccessResponse(HttpStatus.OK, "All " + root + " retrieve successfully", userDtos);
	}

	private List<UserDto> getUsers(List<User> users) {
		List<UserDto> dtoList = new ArrayList<>();
		users.forEach(user -> {
			modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
			UserDto userdto = modelMapper.map(user, UserDto.class);
			dtoList.add(userdto);
		});
		return dtoList;
	}

	@Override
	public User getUsername(String name) {
		return userRepository.findBynameAndIsActiveTrue(name);
	}

	@Override
	public Response registerNewUser(UserDto userDto) {
		User user = modelMapper.map(userDto, User.class);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Role role = roleRepository.findById(UrlConstraint.UserManagement.NORMAL_USER).get();
		user.getRoles().add(role);
		user = userRepository.save(user);
		modelMapper.map(user, UserDto.class);
		return ResponseBuilder.getSuccessResponse(HttpStatus.OK, "New user registered successfully", null);
	}

}
