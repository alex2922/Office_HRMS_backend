package com.SaharaAmussmentPark.Dto;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@Accessors(chain = true)
public class LoginDto {
	private String email;
	private String password;

}
