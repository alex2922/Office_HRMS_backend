package com.SaharaAmussmentPark.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SaharaAmussmentPark.Dto.Message;
import com.SaharaAmussmentPark.Dto.OfficialLetterDto;
import com.SaharaAmussmentPark.Service.OfficialLetterService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/officialLetter")
@Log4j2
public class OfficialLetterController {
	public final OfficialLetterService officialLetterservice;

	public OfficialLetterController(OfficialLetterService officialLetterservice) {
		super();
		this.officialLetterservice = officialLetterservice;
	}
	@PostMapping("/addOfficialLetter")
	public ResponseEntity<Message<OfficialLetterDto>> AddOfficialLetter(@RequestBody OfficialLetterDto request){
		log.info("In usercontroller login() with request:{}",request);
		Message<OfficialLetterDto> message=officialLetterservice.AddOfficialLetter(request);
		HttpStatus httpStatus=HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
		
	}
	@PutMapping("/updateOfficialLetter")
	public ResponseEntity<Message<OfficialLetterDto>> UpdateOfficialLetter(@RequestBody OfficialLetterDto request){
		log.info("In usercontroller login() with request:{}",request);
		Message<OfficialLetterDto> message=officialLetterservice.UpdateOfficialLetter(request);
		HttpStatus httpStatus=HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}
	@GetMapping("/getOfficialLetterById")
	public ResponseEntity<Message<OfficialLetterDto>> GetOfficialLetter(@RequestParam("oId") int oId){
		log.info("In usercontroller login() with request:{}",oId);
		Message<OfficialLetterDto> message=officialLetterservice.GetOfficialLetterById(oId);
		HttpStatus httpStatus=HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
		
	}
	@GetMapping("/getOfficialLetterByName")
	public ResponseEntity<Message<OfficialLetterDto>> GetOfficialLetterByName(@RequestParam("oname") String oname){
		log.info("In usercontroller login() with request:{}",oname);
		Message<OfficialLetterDto> message=officialLetterservice.GetOfficialLetterByName(oname);
		HttpStatus httpStatus=HttpStatus.valueOf(message.getStatus().value());
		return ResponseEntity.status(httpStatus).body(message);
	}
	
}
