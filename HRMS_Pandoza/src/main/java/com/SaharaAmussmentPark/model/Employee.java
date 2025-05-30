package com.SaharaAmussmentPark.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity
@Data
@ToString
@Accessors(chain = true)
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int eId;
	private String employeeName;
	private String employeeId;
	private int attendanceCode;
	private String gender;
	private String employeeStatus;
	private String designation;
	private String department;
	private String dateOfJoining;
	private String dateOfLiving;
	private String contactNumber;
	private String email;
	private String ifscCode;
	private String dateOfBirth;
	private String aadharNumber;
	private String panNumber;
	private String accountNumber;
	private double costtoCompany;
	private double employeeSalary;
	private String bankName;
	private String companyName;
	private double diduction;
	private String address;
	private String accNumber;
	private String salaryMonth;
    private int uId;
	private String otp;
    private String uanNo;
    private double esicNumber;

	
	
	
	
}
