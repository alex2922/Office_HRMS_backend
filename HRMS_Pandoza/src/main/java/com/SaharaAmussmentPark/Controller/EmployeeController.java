package com.SaharaAmussmentPark.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SaharaAmussmentPark.Dto.EmployeeDto;
import com.SaharaAmussmentPark.Dto.EmployeeResponseDto;
import com.SaharaAmussmentPark.Dto.Message;
import com.SaharaAmussmentPark.Dto.SalaryDto;
import com.SaharaAmussmentPark.Dto.userdetailsResponseDto;
import com.SaharaAmussmentPark.Service.EmployeeService;
import com.SaharaAmussmentPark.Service.SalaryService;
import com.SaharaAmussmentPark.Service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/Employee")
@CrossOrigin(origins = { "*" }, allowedHeaders = { "*" })
@Log4j2
@RequiredArgsConstructor
public class EmployeeController {	
	private final SalaryService salaryService;
	private final EmployeeService employeeService;
	private final UserService userservice;
	
	
	 @GetMapping("/download/{employeeId}/{month}")
	    public ResponseEntity<Message<EmployeeResponseDto>> getSalaryDetails(
	            @PathVariable String employeeId,
	            @PathVariable String month
	            ) {
	        
	        Message<EmployeeResponseDto> salaryResponse = salaryService.getSalaryDetails(employeeId, month);
	        return ResponseEntity.ok(salaryResponse);
	    }
	 @GetMapping("/GetEmployee/{employeeId}")
		public ResponseEntity<Message<EmployeeDto>> getAllDesignation(@PathVariable String employeeId) {
			log.info("In AdminController get Employee By EmployeeID");
			Message<EmployeeDto> message = employeeService.getByemployeeId(employeeId);
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}
		@GetMapping("/getUserById/{uId}")
		public ResponseEntity<Message<userdetailsResponseDto>> getUserById(@PathVariable int uId) {
			log.info("In usercontroller login() with request:{}", uId);
			Message<userdetailsResponseDto> message = userservice.getUserById(uId);
			HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
			return ResponseEntity.status(httpStatus).body(message);
//			asbh
		}
		@GetMapping("/salary/{employeeId}")
		public ResponseEntity<Map<String, Object>> getSalariesByEmployeeId(@PathVariable String employeeId) {
			Map<String, Object> response = salaryService.findAllSalaryByemployeeId(employeeId);
		    return ResponseEntity.ok(response);
		}
		@GetMapping("/salaryFilter/{month}/{year}")
		public ResponseEntity<Map<String, Object>> getSalariesByEmployeeId(@PathVariable String month,@PathVariable String year) {
			Map<String, Object> response = salaryService.findAllSalaryBymonthAndsalary(month,year);
		    return ResponseEntity.ok(response);
		}

		
}
