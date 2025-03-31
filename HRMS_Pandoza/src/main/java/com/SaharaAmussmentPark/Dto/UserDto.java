package com.SaharaAmussmentPark.Dto;

import java.util.Date;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@Accessors(chain = true)
public class UserDto {
	private int uId;
	private String email;
	private String password;
	private String role;
	private Date createdDate;
	private String token;

}
