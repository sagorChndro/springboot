package com.sagor.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sagor.exception.ChatException;
import com.sagor.exception.UserException;
import com.sagor.model.Chat;
import com.sagor.model.User;
import com.sagor.request.SingleChatRequest;
import com.sagor.response.ApiResponse;
import com.sagor.service.ChatService;
import com.sagor.service.GroupChatRequest;
import com.sagor.service.UserService;

@RestController
@RequestMapping("api/chats")
public class ChatController {

	private final ChatService chatService;
	private final UserService userService;

	public ChatController(ChatService chatService, UserService userService) {
		this.chatService = chatService;
		this.userService = userService;
	}

	@PostMapping("/createChat")
	public ResponseEntity<Chat> createChatHandler(@RequestBody SingleChatRequest singleChatRequest,
			@RequestHeader("Authorization") String jwt) throws UserException {

		User reqUser = userService.findUserProfile(jwt);
		Chat chat = chatService.createChat(reqUser, singleChatRequest.getUserId());

		return new ResponseEntity<Chat>(chat, HttpStatus.CREATED);

	}

	@PostMapping("/createGroup")
	public ResponseEntity<Chat> createGroupHanlder(@RequestBody GroupChatRequest groupChatRequest,
			@RequestHeader("Authorization") String jwt) throws UserException {

		User reqUser = userService.findUserProfile(jwt);

		Chat chat = chatService.createGroup(groupChatRequest, reqUser);

		return new ResponseEntity<Chat>(chat, HttpStatus.CREATED);

	}

	@GetMapping("/{chatId}")
	public ResponseEntity<Chat> findChatByIdHandler(@PathVariable("chatId") Integer chatId,
			@RequestHeader("Authorization") String jwt) throws ChatException {

		Chat chat = chatService.findChatById(chatId);

		return new ResponseEntity<Chat>(chat, HttpStatus.OK);

	}

	@GetMapping("/user")
	public ResponseEntity<List<Chat>> findAllChatByUserIdHandler(@RequestHeader("Authorization") String jwt)
			throws UserException {
		User reqUser = userService.findUserProfile(jwt);
		List<Chat> chats = chatService.findAllChatByUserId(reqUser.getId());

		return new ResponseEntity<List<Chat>>(chats, HttpStatus.OK);
	}

	@PutMapping("/{chatId}/add/{userId}")
	public ResponseEntity<Chat> addUserToGroupHandler(@PathVariable("chatId") Integer chatId,
			@PathVariable("userId") Integer userId, @RequestHeader("Authorization") String jwt)
			throws UserException, ChatException {

		User reqUser = userService.findUserProfile(jwt);
		Chat chat = chatService.addUserToGroup(userId, chatId, reqUser);

		return new ResponseEntity<Chat>(chat, HttpStatus.OK);

	}

	@PutMapping("/{chatId}/remove/{userId}")
	public ResponseEntity<Chat> removeUserFromGroupHandler(@PathVariable("chatId") Integer chatId,
			@PathVariable("userId") Integer userId, @RequestHeader("Authorization") String jwt)
			throws UserException, ChatException {
		User reqUser = userService.findUserProfile(jwt);
		Chat chat = chatService.removeFromGroup(chatId, userId, reqUser);
		return new ResponseEntity<Chat>(chat, HttpStatus.OK);

	}

	@DeleteMapping("/delete/{chatId}")
	public ResponseEntity<ApiResponse> deleteChatHandler(@PathVariable("chatId") Integer chatId,
			@RequestHeader("Authorization") String jwt) throws UserException, ChatException {
		User reqUser = userService.findUserProfile(jwt);
		chatService.deleteChat(chatId, reqUser.getId());

		ApiResponse response = new ApiResponse("Chat deleted successfully", true);

		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);

	}

}
