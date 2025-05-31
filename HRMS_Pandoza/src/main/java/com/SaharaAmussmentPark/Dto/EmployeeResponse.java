package com.SaharaAmussmentPark.Dto;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@Accessors(chain = true)
public class EmployeeResponse {
	private String EmployeeId;
	private String employeeName;
	private String ifscCode;
	private String panNumber;
	private String accountNumber;
	private String bankName;
    private String uanNo;
	private double esicNumber;

}
