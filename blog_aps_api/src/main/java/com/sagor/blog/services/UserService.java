package com.sagor.blog.services;

import com.sagor.blog.model.User;
import com.sagor.blog.payloadordto.Response;
import com.sagor.blog.payloadordto.UserDto;

public interface UserService {

	Response registerNewUser(UserDto userDto);

	Response createUser(UserDto userDto);

	Response updateUser(UserDto userDto, Long userId);

	Response delete(Long userId);

	Response getUser(Long userId);

	User getUsername(String name);

	Response getAllUsers();

}
