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
	private String employeeId;
	private String employeeName;

}