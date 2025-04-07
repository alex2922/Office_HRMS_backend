package com.SaharaAmussmentPark.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SaharaAmussmentPark.Dto.Message;
import com.SaharaAmussmentPark.Dto.SalaryDto;
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
	
	
	
	 @GetMapping("/{employeeId}/{month}")
	    public ResponseEntity<Message<SalaryDto>> getSalaryDetails(
	            @PathVariable String employeeId,
	            @PathVariable String month
	            ) {
	        
	        Message<SalaryDto> salaryResponse = salaryService.getSalaryDetails(employeeId, month);
	        return ResponseEntity.ok(salaryResponse);
	    }
//	
}
