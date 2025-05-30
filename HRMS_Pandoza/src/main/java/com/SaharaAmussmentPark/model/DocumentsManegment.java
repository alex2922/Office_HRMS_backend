package com.SaharaAmussmentPark.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
 @ToString
 @Entity
 @Accessors(chain = true)
public class DocumentsManegment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
