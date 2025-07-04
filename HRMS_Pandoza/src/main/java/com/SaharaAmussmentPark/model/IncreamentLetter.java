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

public class IncreamentLetter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
