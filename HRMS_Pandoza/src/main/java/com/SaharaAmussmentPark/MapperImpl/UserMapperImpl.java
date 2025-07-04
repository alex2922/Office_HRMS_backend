package com.SaharaAmussmentPark.MapperImpl;

import org.springframework.stereotype.Component;

import com.SaharaAmussmentPark.Dto.LoginResponseDto;
import com.SaharaAmussmentPark.Dto.RestTemplateDto;
import com.SaharaAmussmentPark.Dto.UserDto;
import com.SaharaAmussmentPark.mapper.UserMapper;
import com.SaharaAmussmentPark.model.User;

@Component
public class UserMapperImpl implements UserMapper {

	@Override
	public UserDto userToUserDto(User user) {
		return new UserDto().setUId(user.getUId()).setCreatedDate(user.getCreatedDate()).setEmail(user.getEmail())
				.setRole(user.getRole());

	}

	@Override
	public User userDtoToUser(UserDto userDto) {
		return new User().setCreatedDate(userDto.getCreatedDate()).setEmail(userDto.getEmail())
				.setPassword(userDto.getPassword()).setRole(userDto.getRole());

	}

	@Override
	public LoginResponseDto userToLoginResponseDto(User user) {
		return new LoginResponseDto().setUId(user.getUId()).setEmail(user.getEmail()).setRole(user.getRole());
	}

	@Override
	public RestTemplateDto userToRestTemplateDto(User user) {
		return new RestTemplateDto().setUId(user.getUId()).setEmail(user.getEmail()).setRole(user.getRole());

	}

}