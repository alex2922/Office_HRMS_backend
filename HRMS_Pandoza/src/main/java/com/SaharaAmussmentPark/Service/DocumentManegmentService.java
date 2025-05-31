package com.SaharaAmussmentPark.Service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;



public interface DocumentManegmentService {
	public Map<String, Object> uploadDocuments(MultipartFile adharCard, MultipartFile panCard, MultipartFile experianceLetter,
            MultipartFile certificate, MultipartFile salarySlip1, MultipartFile bankStatement,
            MultipartFile latestEducationCertificateOrDegree,
            MultipartFile employeeImage,MultipartFile salarySlip2,MultipartFile salarySlip3,MultipartFile relevingLetter,MultipartFile tenthCertificate,MultipartFile twelfthCertificate,MultipartFile degreeCertificate, int uId);
	
	
	public Map<String, Object> updateDocuments(MultipartFile adharCard, MultipartFile panCard, MultipartFile experianceLetter,
            MultipartFile certificate, MultipartFile salarySlip1, MultipartFile bankStatement,
            MultipartFile latestEducationCertificateOrDegree,
            MultipartFile employeeImage,MultipartFile salarySlip2,MultipartFile salarySlip3,MultipartFile relevingLetter,MultipartFile tenthCertificate,MultipartFile twelfthCertificate,MultipartFile degreeCertificate, int uId);
	
	public Map<String, Object> getDocuments(int uId);
}
