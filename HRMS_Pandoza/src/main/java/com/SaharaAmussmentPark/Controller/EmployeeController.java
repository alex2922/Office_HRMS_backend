package com.SaharaAmussmentPark.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SaharaAmussmentPark.Dto.ChangePasswordDto;
import com.SaharaAmussmentPark.Dto.EmployeeDto;
import com.SaharaAmussmentPark.Dto.EmployeeResponseDto;
import com.SaharaAmussmentPark.Dto.Message;
import com.SaharaAmussmentPark.Dto.UserDto;
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
	private final UserService userservice;
	
	private final SalaryService salaryService;
	private final EmployeeService employeeService;
	
	
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
	 @PutMapping("/updatePassword")
		public ResponseEntity<Message<UserDto>> updatePassword(@RequestBody ChangePasswordDto request) {
			log.info("In usercontroller login() with request:{}", request);
			Message<UserDto> message = userservice.updatePassword(request);
			HttpStatus httpStatus = HttpStatus.valueOf(message.getStatus().value());
			return ResponseEntity.status(httpStatus).body(message);
		}
//	
}
