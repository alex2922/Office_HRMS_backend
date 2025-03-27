package com.SaharaAmussmentPark.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SaharaAmussmentPark.Dto.DepartmentDto;
import com.SaharaAmussmentPark.Dto.Message;
import com.SaharaAmussmentPark.Service.DepartmentService;

import lombok.extern.log4j.Log4j2;


@RestController
@RequestMapping("/department")
@Log4j2

public class DepartmentController {
	private final DepartmentService departmentservice;

	public DepartmentController(DepartmentService departmentservice) {
		super();
		this.departmentservice = departmentservice;
	}
	@PostMapping("/addDepartment")
	public ResponseEntity<Message<DepartmentDto>> AddDepartment(@RequestBody DepartmentDto request){
		log.info("In usercontroller login() with request:",request);
		Message<DepartmentDto> message=departmentservice.AddDepartment(request);
		HttpStatus httpStatus=HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
		
	}
	@DeleteMapping("/deleteDepartment")
	public ResponseEntity<Message<DepartmentDto>> DeleteDepartment(@RequestParam("dId") int dId){
		log.info("In usercontroller login() with request:",dId);
		Message<DepartmentDto> message=departmentservice.DeleteDepartment(dId);
		HttpStatus httpStatus=HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}
	@PutMapping("/updateDepartment")
	public ResponseEntity<Message<DepartmentDto>> UpdateDepartment(@RequestBody DepartmentDto request){
		log.info("In usercontroller login() with request:",request);
		Message<DepartmentDto> message=departmentservice.UpdateDepartment(request);
		HttpStatus httpStatus=HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);	
	}
	@GetMapping("/getDepartmentById")
	public ResponseEntity<Message<DepartmentDto>> GetDepartmentById(@RequestParam("dId") int dId){
		log.info("In usercontroller login() with request:{}",dId);
		Message<DepartmentDto> message=departmentservice.GetDepartmentById(dId);
		HttpStatus httpStatus=HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}
	@GetMapping("/getAllDepartments")
	public ResponseEntity<List<Message<DepartmentDto>>> GetAllDepartments(){
		List<Message<DepartmentDto>> message=departmentservice.GetAllDepartment();
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}

}
