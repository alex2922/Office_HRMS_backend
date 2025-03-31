package com.SaharaAmussmentPark.MapperImpl;


import java.util.Date;

import org.springframework.stereotype.Component;

import com.SaharaAmussmentPark.Dto.UserDto;
import com.SaharaAmussmentPark.mapper.UserMapper;
import com.SaharaAmussmentPark.model.User;

@Component
public class UserMapperImpl implements UserMapper {

	@Override
	public UserDto userToUserDto(User user) {
		return new UserDto().setUId(user.getUId()).
				setEmail(user.getEmail()).
				setPassword(user.getPassword()).
				setRole(user.getRole()).
				setCreatedDate(new Date());
	}

	@Override
	public User userDtoToUser(UserDto userDto) {
		return new User().setUId(userDto.getUId()).
				setEmail(userDto.getEmail()).
				setPassword(userDto.getPassword()).
				setCreatedDate(userDto.getCreatedDate())
				.setRole(userDto.getRole());
	}

}
