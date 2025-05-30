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
	private String salarySlip;
	private String bankStatement;
	private String otherDocuments;
	private String latestEducationCertificateOrDegree;
	private String employeeImage;
	private int uId; 
	private String EmployeeId;
	private String employeeName;
}
