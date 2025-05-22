package com.SaharaAmussmentPark.Service;

import java.util.List;

import com.SaharaAmussmentPark.Dto.ChangePasswordDto;
import com.SaharaAmussmentPark.Dto.LoginDto;
import com.SaharaAmussmentPark.Dto.LoginResponseDto;
import com.SaharaAmussmentPark.Dto.LoginResponseDto;
import com.SaharaAmussmentPark.Dto.Message;
import com.SaharaAmussmentPark.Dto.UserDto;


public interface UserService {
	public Message<UserDto>registerUser(UserDto request);
	public Message<LoginResponseDto>loginUser(LoginDto request);
	public List<Message<UserDto>>getAllUsers(Integer page, Integer size);
	public Message<UserDto>updateUser(UserDto request);
	public Message<UserDto>deleteUser(Integer uId);
	public Message<UserDto>getUserById(Integer uId);
	public Message<UserDto>updatePassword(ChangePasswordDto request);
	public Message<UserDto> sendOtp(String email);
	public Message<UserDto>verifyOtp(String email,String otp);
}
