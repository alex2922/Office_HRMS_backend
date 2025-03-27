package com.SaharaAmussmentPark.Service;

import java.util.List;

import com.SaharaAmussmentPark.Dto.LoginDto;
import com.SaharaAmussmentPark.Dto.Message;
import com.SaharaAmussmentPark.Dto.UserDto;

public interface UserService {
	public Message<UserDto>registerUser(UserDto request);
	public Message<UserDto>loginUser(LoginDto request);
	public List<Message<UserDto>>getAllUsers(Integer page, Integer size);
}
