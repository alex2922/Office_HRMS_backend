package com.SaharaAmussmentPark.Service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;



public interface DocumentManegmentService {
	public Map<String, Object> uploadDocuments(MultipartFile adharCard, MultipartFile panCard, MultipartFile experianceLetter,
            MultipartFile certificate, MultipartFile salarySlip, MultipartFile bankStatement,
            MultipartFile otherDocuments, MultipartFile latestEducationCertificateOrDegree,
            MultipartFile employeeImage, int uId);
	public Map<String, Object> updateDocuments(MultipartFile adharCard, MultipartFile panCard, MultipartFile experianceLetter,
            MultipartFile certificate, MultipartFile salarySlip, MultipartFile bankStatement,
            MultipartFile otherDocuments, MultipartFile latestEducationCertificateOrDegree,
            MultipartFile employeeImage, int uId);
	
	public Map<String, Object> getDocuments(int uId);
}
