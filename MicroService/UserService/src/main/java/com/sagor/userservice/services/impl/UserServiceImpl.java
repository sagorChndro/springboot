package com.sagor.userservice.services.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sagor.userservice.dto.Response;
import com.sagor.userservice.dto.UserDto;
import com.sagor.userservice.model.Hotel;
import com.sagor.userservice.model.Rating;
import com.sagor.userservice.model.User;
import com.sagor.userservice.repository.UserRepository;
import com.sagor.userservice.services.UserService;
import com.sagor.userservice.utils.ResponseBuilder;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	private final UserRepository userRepository;
	private final ModelMapper modelMapper;
	private final RestTemplate restTemplate;
	private String root = "User";

	public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RestTemplate restTemplate) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
		this.restTemplate = restTemplate;
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
		// fetch rating of the above user from RATING SERVICE
		// localhost:8083/api/v1/ratings/users/42e23526-f279-45a3-804a-c904516d31b3
//		ArrayList<Rating> ratingsOfUser = restTemplate
//				.getForObject("http://localhost:8083/api/v1/ratings/users/" + user.getUserId(), ArrayList.class);
//		logger.info("{}", ratingsOfUser);
//		user.setRatings(ratingsOfUser);
		if (user != null) {
			modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
			UserDto userDto = modelMapper.map(user, UserDto.class);

			Rating[] ratingsOfUser = restTemplate
					.getForObject("http://RATING-SERVICE/api/v1/ratings/users/" + userDto.getUserId(), Rating[].class);
			List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

			List<Rating> ratingList = ratings.stream().map(rating -> {
				ResponseEntity<Hotel> forEntity = restTemplate
						.getForEntity("http://HOTEL-SERVICE/api/v1/hotels/get/" + rating.getHotelId(), Hotel.class);
				Hotel hotel = forEntity.getBody();
				logger.info("{}", forEntity.getStatusCode());
				rating.setHotel(hotel);
				return rating;
			}).collect(Collectors.toList());
// 		.getForEntity("http://localhost:8082/api/v1/hotels/get/" + rating.getHotelId(), Hotel.class);
			userDto.setRatings(ratingList);
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
