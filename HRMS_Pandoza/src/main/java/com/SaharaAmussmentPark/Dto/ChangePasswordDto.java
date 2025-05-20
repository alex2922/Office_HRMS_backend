package com.SaharaAmussmentPark.Dto;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString 
@Accessors(chain = true)
public class ChangePasswordDto {
	
	private String email;
	private String newPassword;
	private String confirmPassword;

}
