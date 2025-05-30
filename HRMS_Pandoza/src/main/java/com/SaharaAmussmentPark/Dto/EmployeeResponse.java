package com.SaharaAmussmentPark.Dto;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@Accessors(chain = true)
public class EmployeeResponse {
	private int uId;
	private String email;
	private String role;
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
	private String employeeImage;
	private String address;


}
