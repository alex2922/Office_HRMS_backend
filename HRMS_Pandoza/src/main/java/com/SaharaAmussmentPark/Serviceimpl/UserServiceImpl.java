package com.SaharaAmussmentPark.Serviceimpl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.SaharaAmussmentPark.Dto.LoginDto;
import com.SaharaAmussmentPark.Dto.Message;
import com.SaharaAmussmentPark.Dto.UserDto;
import com.SaharaAmussmentPark.Repository.UserRepository;
import com.SaharaAmussmentPark.Service.JwtService;
import com.SaharaAmussmentPark.Service.UserService;
import com.SaharaAmussmentPark.mapper.UserMapper;
import com.SaharaAmussmentPark.model.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService {
private final UserMapper userMapperImpl;
private final UserRepository userRepository;
private final JwtService jwtService;
	@Override
	public Message<UserDto> registerUser(UserDto request) {
          Message<UserDto> message=new Message<>();
          try {
          User user=userRepository.getByEmail(request.getEmail());
          if(user!=null) {
        	  message.setResponseMessage("UserAlready available");
        	  message.setStatus(HttpStatus.CONFLICT);
        	  return message;
		  }
		  user=userMapperImpl.userDtoToUser(request);
		  userRepository.save(user);
		  message.setResponseMessage("User registered successfully");
		  message.setStatus(HttpStatus.OK);
		  return message;
          }catch (Exception e) {
  			message.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
  			message.setResponseMessage(e.getMessage());
  			log.error("SOMETHING_WENT_WRONG" + "  " + message.getResponseMessage());
  			return message;

  		}
	}

	@Override
	public Message<UserDto> loginUser(LoginDto request) {
		Message<UserDto> message = new Message<>();
		User user = null;
		UserDto userDto =null;
		try {
			user = userRepository.getByEmailAndPassword(request.getEmail(), request.getPassword());
			if (user == null) {
				message.setStatus(HttpStatus.NOT_FOUND);
				message.setResponseMessage("RECORD_NOT_FOUND");
				log.info("RECORD_NOT_FOUND");
				return message;
			}
			 String token=jwtService.genrateToken(user);
			 userDto=userMapperImpl.userToUserDto(user);
			 userDto.setToken(token);
			message.setStatus(HttpStatus.OK);
			message.setResponseMessage("LOGIN_SUCCESSFULL");
			message.setData(userDto);
			log.info("LOGIN_SUCCESSFULL" + "  " + message.getData());
			return message;
		} catch (Exception e) {
			message.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			message.setResponseMessage(e.getMessage());
			log.error("SOMETHING_WENT_WRONG "+ "  " + message.getResponseMessage());
			return message;
		}
	}

	@Override
	public List<Message<UserDto>> getAllUsers(Integer page, Integer size) {
			List<Message<UserDto>> messages = new ArrayList<>(); 
		    try {
		    	 int pageNumber = (page == null || page <= 0) ? 0 : page - 1;

		         int pageSize = (size == null || size <= 0) ? 10 : size;

		         Pageable pageable = PageRequest.of(pageNumber, pageSize);
				Page<User> users = userRepository.findAll(pageable);
		        
		        if (users == null || users.isEmpty()) { 
		            Message<UserDto> message = new Message<>();
		            message.setStatus(HttpStatus.NOT_FOUND);
		            message.setResponseMessage("RECORD_NOT_FOUND");
		            messages.add(message);
		            return messages;
		        }
		        
		        for (User user : users) { 
		            Message<UserDto> message = new Message<>();
		            
		            {
		                message.setStatus(HttpStatus.OK);
		                message.setResponseMessage("USER_FOUND");
		                message.setData(userMapperImpl.userToUserDto(user)); 
		            }
		            messages.add(message); 
		        }
		        
		        return messages; 
		    } catch (Exception e) {
		        Message<UserDto> errorMessage = new Message<>();
		        errorMessage.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		        errorMessage.setResponseMessage(e.getMessage());
		        log.error("SOMETHING_WENT_WRONG" + "  " + errorMessage.getResponseMessage());
		        messages.add(errorMessage);
		        return messages;
		    }
		}

}
