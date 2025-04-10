package com.SaharaAmussmentPark.Dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class EmployeeResponseDto {
    private SalaryDto salary;
	private EmployeeDto employee;
    private String employeeName;
    private String department;
    private String designation;
    private String accountNumber;
    private String bankName;
    private int uanNo;
 

}
