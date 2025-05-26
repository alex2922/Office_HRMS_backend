package com.SaharaAmussmentPark.Controller;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SaharaAmussmentPark.Dto.Message;
import com.SaharaAmussmentPark.Dto.userdetailsResponseDto;
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
	
	
	@GetMapping("/getUserById/{uId}")
	public ResponseEntity<Message<userdetailsResponseDto>> getUserById(@PathVariable int uId) {
		log.info("In usercontroller login() with request:{}", uId);
		Message<userdetailsResponseDto> message = userservice.getUserById(uId);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}


}
