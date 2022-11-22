package com.sagor.blog.services;

import com.sagor.blog.payloadordto.Response;
import com.sagor.blog.payloadordto.UserDto;

public interface UserService {

	Response createUser(UserDto userDto);

	Response updateUser(UserDto userDto, Long userId);

	Response delete(Long userId);

	Response getUser(Long userId);

	Response getAllUsers();

}
