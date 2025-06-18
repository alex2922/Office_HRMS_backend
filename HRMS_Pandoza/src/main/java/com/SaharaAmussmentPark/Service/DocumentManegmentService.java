package com.SaharaAmussmentPark.Service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;



public interface DocumentManegmentService {
	public Map<String, Object> uploadDocuments(MultipartFile adharCard, MultipartFile panCard, MultipartFile experianceLetter,
             MultipartFile salarySlip1,MultipartFile salarySlip2,MultipartFile salarySlip3, MultipartFile bankStatement,
             MultipartFile relevingLetter,MultipartFile tenthCertificate,MultipartFile twelfthCertificate,MultipartFile degreeCertificate,MultipartFile latestEducationCertificateOrDegree,
            MultipartFile employeeImage, MultipartFile diplomaCertificate,int uId);
	
	
	public Map<String, Object> updateDocuments(MultipartFile adharCard, MultipartFile panCard, MultipartFile experianceLetter,
            MultipartFile salarySlip1,MultipartFile salarySlip2,MultipartFile salarySlip3, MultipartFile bankStatement,
            MultipartFile relevingLetter,MultipartFile tenthCertificate,MultipartFile twelfthCertificate,MultipartFile degreeCertificate,MultipartFile latestEducationCertificateOrDegree,
           MultipartFile employeeImage, MultipartFile diplomaCertificate,int uId);
	
	public Map<String, Object> getDocuments(String employeeId);
}
