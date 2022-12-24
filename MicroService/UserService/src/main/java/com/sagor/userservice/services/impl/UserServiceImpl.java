package com.sagor.userservice.services.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sagor.userservice.dto.Response;
import com.sagor.userservice.dto.UserDto;
import com.sagor.userservice.model.User;
import com.sagor.userservice.repository.UserRepository;
import com.sagor.userservice.services.UserService;
import com.sagor.userservice.utils.ResponseBuilder;

@Service
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final ModelMapper modelMapper;
	private String root = "User";

	public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public Response saveUser(UserDto userDto) {
		User user = modelMapper.map(userDto, User.class);
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		user = userRepository.save(user);
		if (user != null) {
			return ResponseBuilder.getSuccessResponse(HttpStatus.CREATED, root + " created successfully", user);
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occurred");
	}

	@Override
	public Response updateUser(UserDto userDto, String userId) {
		User user = userRepository.findByUserIdAndIsActiveTrue(userId);
		if (user != null) {
			modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
			modelMapper.map(userDto, user);
			user = userRepository.save(user);
			if (user != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " updated successfully", user);
			}
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR,
					"Internal server error occurred");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found");
	}

	@Override
	public Response getUser(String userId) {
		User user = userRepository.findByUserIdAndIsActiveTrue(userId);
		if (user != null) {
			modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
			UserDto userDto = modelMapper.map(user, UserDto.class);
			if (userDto != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " retrieve successfully", userDto);
			}
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR,
					"Internal server error occurred");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found");
	}

	@Override
	public Response deleteUser(String userId) {
		User user = userRepository.findByUserIdAndIsActiveTrue(userId);
		if (user != null) {
			user.setIsActive(false);
			user = userRepository.save(user);
			if (user != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " deleted successfully", null);
			}
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR,
					"Internal server error occurred");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found to delete");
	}

	@Override
	public Response getAllUsers() {
		Set<User> user = userRepository.findAllByIsActiveTrue();
		Set<UserDto> userDto = this.getUsers(user);
		return ResponseBuilder.getSuccessResponse(HttpStatus.OK, "All " + root + " retrieve successfully", userDto);
	}

	private Set<UserDto> getUsers(Set<User> users) {
		Set<UserDto> userDto = new HashSet<>();
		users.forEach(user -> {
			modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
			UserDto usersDto = modelMapper.map(user, UserDto.class);
			userDto.add(usersDto);
		});
		return userDto;
	}

}
