package com.SaharaAmussmentPark.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeInfoDto {
	private String employeeId;
    private String name;
    private String designation;

}
