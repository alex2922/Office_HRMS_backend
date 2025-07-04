package com.SaharaAmussmentPark.Dto;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@Accessors(chain = true)

public class IncreamentLetterDto {
	private int id;
	private String date;
	private String employeeName;
	private String employeeId;
	private double salary;
	private String hrManagerName;
	private String designation;
	private String gender;
	private double grossSalary;
	private double hra;
	private double basicSalary;
	private double da;
	private double otherAllowance;
	private double oldSalary;

}
