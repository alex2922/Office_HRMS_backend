package com.SaharaAmussmentPark.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SaharaAmussmentPark.Dto.LoginDto;
import com.SaharaAmussmentPark.Dto.Message;
import com.SaharaAmussmentPark.Dto.UserDto;
import com.SaharaAmussmentPark.Service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = { "*" }, allowedHeaders = { "*" })
@Log4j2
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userservice;

	
	@PostMapping("/RegisterUser")
	public ResponseEntity<Message<UserDto>> registerUser(@RequestBody UserDto user) {
		log.info("In UserController registerUser() with request: {}", user);
		Message<UserDto> message = userservice.registerUser(user);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}
	@GetMapping("/Login")
	public ResponseEntity<Message<UserDto>> loginUser(@RequestBody LoginDto request) {
		log.info("In UserController login() with request: {}", request);
		Message<UserDto> message = userservice.loginUser(request);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}
}
