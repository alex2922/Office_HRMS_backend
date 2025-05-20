package com.SaharaAmussmentPark.Dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@ToString
@RequiredArgsConstructor

public class SalaryResponse {
	public SalaryResponse(String employeeId2, String month2, String year2, double basicSalary2, double hra2,
			double diduction, double netSalary2) {
		// TODO Auto-generated constructor stub
	}
	private String employeeId;

	private String month;

	private String year;

	private double basicSalary;

	
	private double hra;
	private double netSalary;


	private double deduction;
    private double pf;
    private double lop;
    private int absentDays;
    private int presentDays;
    private int workingDays;

}
