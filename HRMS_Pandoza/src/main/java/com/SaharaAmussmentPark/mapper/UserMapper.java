package com.SaharaAmussmentPark.mapper;


import com.SaharaAmussmentPark.Dto.LoginResponseDto;
import com.SaharaAmussmentPark.Dto.RestTemplateDto;
import com.SaharaAmussmentPark.Dto.UserDto;
import com.SaharaAmussmentPark.model.User;

public interface UserMapper {
public UserDto userToUserDto(User user);
public User userDtoToUser(UserDto userDto);
public LoginResponseDto userToLoginResponseDto(User user);
public RestTemplateDto userToRestTemplateDto(User user);

}
