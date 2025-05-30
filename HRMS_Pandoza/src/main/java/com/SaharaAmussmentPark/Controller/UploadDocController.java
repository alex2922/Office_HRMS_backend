package com.SaharaAmussmentPark.Controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.SaharaAmussmentPark.Service.DocumentManegmentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/uploadDoc")
@CrossOrigin(origins = { "*" }, allowedHeaders = { "*" })
@Log4j2
@RequiredArgsConstructor
public class UploadDocController {
	private final DocumentManegmentService documentManegmentServiceimpl;
	
	
	
	 @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	    public ResponseEntity<Map<String, Object>> uploadEmployeeDocuments(
	            @RequestPart(value = "adharCard", required = false) MultipartFile adharCard,
	            @RequestPart(value = "panCard", required = false) MultipartFile panCard,
	            @RequestPart(value = "experianceLetter", required = false) MultipartFile experianceLetter,
	            @RequestPart(value = "certificate", required = false) MultipartFile certificate,
	            @RequestPart(value = "salarySlip", required = false) MultipartFile salarySlip,
	            @RequestPart(value = "bankStatement", required = false) MultipartFile bankStatement,
	            @RequestPart(value = "otherDocuments", required = false) MultipartFile otherDocuments,
	            @RequestPart(value = "latestEducationCertificateOrDegree", required = false) MultipartFile latestEducationCertificateOrDegree,
	            @RequestPart(value = "employeeImage", required = false) MultipartFile employeeImage,
	            @RequestParam("uId") int uId
	    ) {
	        try {
	            Map<String, Object> response = documentManegmentServiceimpl.uploadDocuments(
	                    adharCard,
	                    panCard,
	                    experianceLetter,
	                    certificate,
	                    salarySlip,
	                    bankStatement,
	                    otherDocuments,
	                    latestEducationCertificateOrDegree,
	                    employeeImage,
	                    uId
	            );
	            return new ResponseEntity<>(response, HttpStatus.OK);
	        } catch (Exception e) {
	            Map<String, Object> error = new HashMap<>();
	            error.put("HttpStatus", HttpStatus.INTERNAL_SERVER_ERROR);
	            error.put("message", "Failed to upload documents: " + e.getMessage());
	            error.put("data", Collections.emptyList());
	            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	 @PutMapping("/update/{uId}")
	    public ResponseEntity<Map<String, Object>> updateDocuments(
	    		@RequestPart(value = "adharCard", required = false) MultipartFile adharCard,
	    		@RequestPart(value = "panCard", required = false) MultipartFile panCard,
	    		@RequestPart(value = "experianceLetter", required = false) MultipartFile experianceLetter,
	    		@RequestPart(value = "certificate", required = false) MultipartFile certificate,
	    		@RequestPart(value = "salarySlip", required = false) MultipartFile salarySlip,
	    		@RequestPart(value = "bankStatement", required = false) MultipartFile bankStatement,
	    		@RequestPart(value = "otherDocuments", required = false) MultipartFile otherDocuments,
	    		@RequestPart(value = "latestEducationCertificateOrDegree", required = false) MultipartFile latestEducationCertificateOrDegree,
	    		@RequestPart(value = "employeeImage", required = false) MultipartFile employeeImage,
	            @PathVariable int uId) {

	        Map<String, Object> response = documentManegmentServiceimpl.updateDocuments(
	                adharCard,
	                panCard,
	                experianceLetter,
	                certificate,
	                salarySlip,
	                bankStatement,
	                otherDocuments,
	                latestEducationCertificateOrDegree,
	                employeeImage,
	                uId
	        );

	        HttpStatus status = (HttpStatus) response.get("status");
	        return new ResponseEntity<>(response, status);
	    }
	 @GetMapping("/getDocuments/{uId}")
	    public ResponseEntity<Map<String, Object>> getDocuments(@PathVariable int uId) {
	        Map<String, Object> response = documentManegmentServiceimpl.getDocuments(uId);
	        HttpStatus status = (HttpStatus) response.get("status");
	        return new ResponseEntity<>(response, status);
	    }
	}