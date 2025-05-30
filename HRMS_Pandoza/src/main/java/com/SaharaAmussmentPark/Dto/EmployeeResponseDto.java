package com.SaharaAmussmentPark.Dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class EmployeeResponseDto {
	@NotBlank(message = "Employee ID is required")
	private String employeeId;

	@NotBlank(message = "Month is required")
	private String month;

	@NotBlank(message = "Year is required")
	private String year;

	@NotNull(message = "Basic Salary cannot be null")
	@Min(value = 0, message = "Basic Salary must be greater than or equal to 0")
	private double basicSalary;

	@NotNull(message = "HRA cannot be null")
	@Min(value = 0, message = "HRA must be greater than or equal to 0")
	private double hra;
	private double netSalary;


	@NotNull(message = "Pf cannot be null")
	private int sId;
	 private double esicNumber;
    private double panNumber;
    private double pf;
    private double lop;
    private int absentDays;
    private int presentDays;
    private int workingDays;
    private String employeeName;
    private String department;
    private String designation;
    private String accountNumber;
    private String bankName;
    private String uanNo;
    private double bonus;
	private double reimbursement;
	private double conveyance;
	private double otheAllownce;
	private double insuranceCorporation;
	private double professionalTax;
	private double tds;
	private double salaryAdvance;
	private double personalLoan;
	private double otherDiductions; 


 

}
