package com.SaharaAmussmentPark.Dto;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@Accessors(chain = true)
public class userdetailsResponseDto {
	private int uId;
	private String email;
	private String role;
	private String employeeId;
	private long totalEmployeeCount;
	private String password;
	
}
