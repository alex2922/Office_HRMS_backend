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

import com.SaharaAmussmentPark.Dto.DesignationDto;
import com.SaharaAmussmentPark.Dto.Message;
import com.SaharaAmussmentPark.Service.DesignationService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/designation")
@Log4j2

public class DesignationController {
	private final DesignationService designationservice;

	public DesignationController(DesignationService designationservice) {
		super();
		this.designationservice = designationservice;
	}
	@PostMapping("/addDesignation")
	public ResponseEntity<Message<DesignationDto>> AddDesignation(@RequestBody DesignationDto request){
		log.info("In usercontroller login() with request:{}",request);
		Message<DesignationDto> message=designationservice.AddDesignation(request);
		HttpStatus httpStatus=HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
		
	}
	@DeleteMapping("/deleteDesignation")
	public ResponseEntity<Message<DesignationDto>> DeleteDesignation(@RequestParam("did") int did){
		log.info("In usercontroller login() with request:{}",did);
		Message<DesignationDto> message=designationservice.DeleteDesignation(did);
		HttpStatus httpStatus=HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}
	@PutMapping("/updateDesignation")
	public ResponseEntity<Message<DesignationDto>> UpdateDesignation(@RequestBody DesignationDto request){
		log.info("In usercontroller login() with request:{}",request);
		Message<DesignationDto> message=designationservice.UpdateDesignation(request);
		HttpStatus httpStatus=HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}
	@GetMapping("/getDesignationById")
	public ResponseEntity<Message<DesignationDto>> GetDesignation(@RequestParam("did") int did){
		log.info("In usercontroller login() with request:{}",did);
		Message<DesignationDto> message=designationservice.GetDesignationById(did);
		HttpStatus httpStatus=HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}
	@GetMapping("/getAllDesignations")
	public ResponseEntity<List<Message<DesignationDto>>> GetAllDesignations(){
		List<Message<DesignationDto>> message=designationservice.GetAllDesignation();
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}
	

}
