package com.SaharaAmussmentPark.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SaharaAmussmentPark.Dto.ChangePasswordDto;
import com.SaharaAmussmentPark.Dto.EmployeeResponse;
import com.SaharaAmussmentPark.Dto.LoginDto;
import com.SaharaAmussmentPark.Dto.LoginResponseDto;
import com.SaharaAmussmentPark.Dto.Message;
import com.SaharaAmussmentPark.Dto.RestTemplateDto;
import com.SaharaAmussmentPark.Dto.UserDto;
import com.SaharaAmussmentPark.Dto.userdetailsResponseDto;
import com.SaharaAmussmentPark.Service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
@RestController
@RequestMapping("/AuthController")
@CrossOrigin(origins = { "*" }, allowedHeaders = { "*" })
@Log4j2
@RequiredArgsConstructor
public class AuthController {
	private final UserService userservice;

	@PostMapping("/Login")
	public ResponseEntity<Message<LoginResponseDto>> loginUser(@RequestBody LoginDto request) {
		log.info("In UserController login() with request: {}", request);
		System.out.println(request);
		Message<LoginResponseDto> message = userservice.loginUser(request);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}

	@PostMapping("/SendOtp")
	public ResponseEntity<Message<UserDto>> sendOTP(@RequestParam("Email") String email) {
		log.info("In UserController sendOTP() with request: {}", email);
		Message<UserDto> message = userservice.sendOtp(email);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}

	@PostMapping("/VerifyOtp")
	public ResponseEntity<Message<UserDto>> verifyOTP(@RequestParam("Email") String email,
			@RequestParam("Otp") String otp) {
		log.info("In UserController verifyOTP() with request: {}", email);
		Message<UserDto> message = userservice.verifyOtp(email, otp);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}

	@PutMapping("/updatePassword")
	public ResponseEntity<Message<UserDto>> updatePassword(@RequestBody ChangePasswordDto request) {
		log.info("In usercontroller login() with request:{}", request);
		Message<UserDto> message = userservice.updatePassword(request);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}
	@GetMapping("/getUserById/{uId}")
	public ResponseEntity<Message<userdetailsResponseDto>> getUserById(@PathVariable int uId) {
		log.info("In usercontroller login() with request:{}", uId);
		Message<userdetailsResponseDto> message = userservice.getUserById(uId);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}
	@GetMapping("/getUserByemail/{email}")
	public ResponseEntity<Message<RestTemplateDto>> getUserById(@PathVariable String email) {
		log.info("In usercontroller login() with request:{}", email);
		Message<RestTemplateDto> message = userservice.findByEmail(email);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);

	}
	
	@GetMapping("/UserByemail/{email}")
	public ResponseEntity<Message<EmployeeResponse>> getUserByemial(@PathVariable String email) {
		log.info("In usercontroller login() with request:{}", email);
		Message<EmployeeResponse> message = userservice.getByEmail(email);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);

	}
}
