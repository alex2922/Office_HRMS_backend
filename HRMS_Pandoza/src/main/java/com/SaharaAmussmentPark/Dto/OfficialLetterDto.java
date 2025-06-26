package com.SaharaAmussmentPark.Dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class OfficialLetterDto {
	private int oId;
	private String date;
	private String companyName;
	private String companyLogo;
	private String employeeName;
	private String designation;
	private String department;
	private String dateOfJoining;
	private String hrManagerName;
	private int salary;
	private String status;
	private String gender;

}
