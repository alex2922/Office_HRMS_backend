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
@Accessors(chain = true)
@Entity

public class Salary {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sId;
	private String month;
	private String year;
	private double basicSalary;
	private double hra;
	private double diduction;
	private double netSalary;
    private String employeeId;
    private double pf;
    private double lop;
    private int absentDays;
    private int presentDays;
    private int workingDays;

}
