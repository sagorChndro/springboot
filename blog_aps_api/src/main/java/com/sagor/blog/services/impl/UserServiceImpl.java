package com.sagor.blog.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sagor.blog.model.User;
import com.sagor.blog.payloadordto.Response;
import com.sagor.blog.payloadordto.UserDto;
import com.sagor.blog.repository.UserRepository;
import com.sagor.blog.services.UserService;
import com.sagor.blog.utils.ResponseBuilder;

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

}
