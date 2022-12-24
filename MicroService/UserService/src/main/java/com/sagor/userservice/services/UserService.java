package com.sagor.userservice.services;

import com.sagor.userservice.dto.Response;
import com.sagor.userservice.dto.UserDto;

public interface UserService {

	Response saveUser(UserDto userDto);

	Response updateUser(UserDto userDto, String userId);

	Response getUser(String userId);

	Response deleteUser(String userId);

	Response getAllUsers();

}
