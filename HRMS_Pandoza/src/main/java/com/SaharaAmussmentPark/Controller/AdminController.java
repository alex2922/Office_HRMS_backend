package com.SaharaAmussmentPark.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SaharaAmussmentPark.Dto.Message;
import com.SaharaAmussmentPark.Dto.UserDto;
import com.SaharaAmussmentPark.Service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/Employee")
@CrossOrigin(origins = { "*" }, allowedHeaders = { "*" })
@Log4j2
@RequiredArgsConstructor
public class AdminController {

	private final UserService userservice;
	
	
	@GetMapping("/GetAllUser")
	public ResponseEntity<List<Message<UserDto>>> getAllUser(@RequestParam("Page")int page,@RequestParam("Size")int size) {
		log.info("In AdminController getAllUser()");
		List<Message<UserDto>> message=userservice.getAllUsers(page, size);
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}
}
