package com.SaharaAmussmentPark.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SaharaAmussmentPark.Dto.DepartmentDto;
import com.SaharaAmussmentPark.Dto.DesignationDto;
import com.SaharaAmussmentPark.Dto.Message;
import com.SaharaAmussmentPark.Dto.UserDto;
import com.SaharaAmussmentPark.Service.DepartmentService;
import com.SaharaAmussmentPark.Service.DesignationService;
import com.SaharaAmussmentPark.Service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/Admin")
@CrossOrigin(origins = { "*" }, allowedHeaders = { "*" })
@Log4j2
@RequiredArgsConstructor
public class AdminController {

	private final UserService userservice;
	private final DepartmentService departmentservice;
	private final DesignationService designationservice;

	@PostMapping("/RegisterUser")
	public ResponseEntity<Message<UserDto>> registerUser(@RequestBody UserDto user) {
		log.info("In UserController registerUser() with request: {}", user);
		Message<UserDto> message = userservice.registerUser(user);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}

	@GetMapping("/GetAllUser")
	public ResponseEntity<List<Message<UserDto>>> getAllUser(@RequestParam("Page") int page,
			@RequestParam("Size") int size) {
		log.info("In AdminController getAllUser()");
		List<Message<UserDto>> message = userservice.getAllUsers(page, size);
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}

	@PostMapping("/addDepartment")
	public ResponseEntity<Message<DepartmentDto>> AddDepartment(@RequestBody DepartmentDto request) {
		log.info("In usercontroller login() with request:", request);
		Message<DepartmentDto> message = departmentservice.AddDepartment(request);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);

	}

	@DeleteMapping("/deleteDepartment")
	public ResponseEntity<Message<DepartmentDto>> DeleteDepartment(@RequestParam("dId") int dId) {
		log.info("In usercontroller login() with request:", dId);
		Message<DepartmentDto> message = departmentservice.DeleteDepartment(dId);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}

	@PutMapping("/updateDepartment")
	public ResponseEntity<Message<DepartmentDto>> UpdateDepartment(@RequestBody DepartmentDto request) {
		log.info("In usercontroller login() with request:", request);
		Message<DepartmentDto> message = departmentservice.UpdateDepartment(request);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}

	@GetMapping("/getDepartmentById")
	public ResponseEntity<Message<DepartmentDto>> GetDepartmentById(@RequestParam("dId") int dId) {
		log.info("In usercontroller login() with request:{}", dId);
		Message<DepartmentDto> message = departmentservice.GetDepartmentById(dId);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}

	@GetMapping("/getAllDepartments")
	public ResponseEntity<List<Message<DepartmentDto>>> GetAllDepartments() {
		List<Message<DepartmentDto>> message = departmentservice.GetAllDepartment();
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}

	@PostMapping("/addDesignation")
	public ResponseEntity<Message<DesignationDto>> AddDesignation(@RequestBody DesignationDto request) {
		log.info("In usercontroller login() with request:{}", request);
		Message<DesignationDto> message = designationservice.AddDesignation(request);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);

	}

	@DeleteMapping("/deleteDesignation")
	public ResponseEntity<Message<DesignationDto>> DeleteDesignation(@RequestParam("did") int did) {
		log.info("In usercontroller login() with request:{}", did);
		Message<DesignationDto> message = designationservice.DeleteDesignation(did);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}

	@PutMapping("/updateDesignation")
	public ResponseEntity<Message<DesignationDto>> UpdateDesignation(@RequestBody DesignationDto request) {
		log.info("In usercontroller login() with request:{}", request);
		Message<DesignationDto> message = designationservice.UpdateDesignation(request);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}

	@GetMapping("/getDesignationById")
	public ResponseEntity<Message<DesignationDto>> GetDesignation(@RequestParam("did") int did) {
		log.info("In usercontroller login() with request:{}", did);
		Message<DesignationDto> message = designationservice.GetDesignationById(did);
		HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}

	@GetMapping("/getAllDesignations")
	public ResponseEntity<List<Message<DesignationDto>>> GetAllDesignations() {
		List<Message<DesignationDto>> message = designationservice.GetAllDesignation();
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}
}
