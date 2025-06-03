package com.SaharaAmussmentPark.Dto;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@Accessors(chain = true)
public class DocumentsManegmentDto {
	private int dId;
	private String adharCard;
	private String panCard;
	private String experianceLetter;
	private String certificate;
	private String salarySlip1;
	private String salarySlip2;
	private String salarySlip3;
	private String bankStatement;
	private String relievingLetter;
	private String tenthCertificate;
	private String twelfthCertificate;
	private String degreeCertificate;
	private String latestEducationCertificateOrDegree;
	private String employeeImage;
	private String diplomaCertificate;
	
	private int uId; 
	private String EmployeeId;
	private String employeeName;
}
