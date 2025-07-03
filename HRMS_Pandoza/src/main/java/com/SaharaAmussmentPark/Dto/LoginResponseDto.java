package com.SaharaAmussmentPark.Dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@Accessors(chain = true)
@RequiredArgsConstructor
public class LoginResponseDto {
	private int uId;
	private String email;
	private String token;
	private String role;
	private String password;
}
