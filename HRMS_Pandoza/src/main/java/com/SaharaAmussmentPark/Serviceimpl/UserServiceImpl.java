package com.SaharaAmussmentPark.Serviceimpl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.SaharaAmussmentPark.Dto.ChangePasswordDto;
import com.SaharaAmussmentPark.Dto.LoginDto;
import com.SaharaAmussmentPark.Dto.Message;
import com.SaharaAmussmentPark.Dto.UserDto;
import com.SaharaAmussmentPark.Repository.EmployeeRepository;
import com.SaharaAmussmentPark.Repository.UserRepository;
import com.SaharaAmussmentPark.Service.JwtService;
import com.SaharaAmussmentPark.Service.UserService;
import com.SaharaAmussmentPark.Util.constants;
import com.SaharaAmussmentPark.mapper.UserMapper;
import com.SaharaAmussmentPark.model.Employee;
import com.SaharaAmussmentPark.model.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService {
private final UserMapper userMapperImpl;
private final UserRepository userRepository;
private final EmployeeRepository employeeRepository;
private final JwtService jwtService;
	@Override
	public Message<UserDto> registerUser(UserDto request) {
          Message<UserDto> message=new Message<>();
          try {
          User user=userRepository.getByEmail(request.getEmail());
          if(user!=null) {
        	  message.setResponseMessage(constants.EMAIL_ALREADY_EXISTS);
        	  message.setStatus(HttpStatus.CONFLICT);
        	  return message;
		  }
		  user=userMapperImpl.userDtoToUser(request);
		  userRepository.save(user);
		  message.setResponseMessage(constants.USER_REGISTERED_SUCCESSFULLY);
		  message.setStatus(HttpStatus.OK);
		  message.setData(userMapperImpl.userToUserDto(user));
		  return message;
          }catch (Exception e) {
  			message.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
  			message.setResponseMessage(e.getMessage());
  			log.error(constants.SOMETHING_WENT_WRONG + "  " + message.getResponseMessage());
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
				message.setResponseMessage(constants.RECORD_NOT_FOUND);
				log.info("RECORD_NOT_FOUND");
				return message;
			}
			 String token=jwtService.genrateToken(user);
			 userDto=userMapperImpl.userToUserDto(user);
			 userDto.setToken(token);
			message.setStatus(HttpStatus.OK);
			message.setResponseMessage(constants.LOGIN_SUCCESSFULL);
			message.setData(userDto);
			log.info("LOGIN_SUCCESSFULL" + "  " + message.getData());
			return message;
		} catch (Exception e) {
			message.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			message.setResponseMessage(e.getMessage());
			log.error(constants.SOMETHING_WENT_WRONG + "  " + message.getResponseMessage());
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
		            message.setResponseMessage(constants.USER_RECORD_NOT_FOUND);
		            messages.add(message);
		            return messages;
		        }
		        
		        for (User user : users) { 
		            Message<UserDto> message = new Message<>();
		            
		            {
		                message.setStatus(HttpStatus.OK);
		                message.setResponseMessage(constants.RECORD_FOUND);
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

	@Override
	public Message<UserDto> updateUser(UserDto request) {
		Message<UserDto> message=new Message<>();
		try {
			User user= userRepository.getById(request.getUId());
			if(user==null) {
				message.setStatus(HttpStatus.NOT_FOUND);
				message.setResponseMessage(constants.USER_RECORD_NOT_FOUND);
				return message;
			}
			user=userMapperImpl.userDtoToUser(request);
			userRepository.save(user);
			message.setStatus(HttpStatus.OK);
			message.setResponseMessage(constants.USER_UPDATED_SUCCESSFULLY);
			message.setData(userMapperImpl.userToUserDto(user));
			return message;
		} catch (Exception e) {
			message.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			message.setResponseMessage(e.getMessage());
			log.error(constants.SOMETHING_WENT_WRONG + "  " + message.getResponseMessage());
			return message;
		}
	}

	@Override
	public Message<UserDto> getUserById(Integer uId) {
		Message<UserDto> message=new Message<>();
		try {
			User user= userRepository.getById(uId);
			if(user==null) {
				message.setStatus(HttpStatus.NOT_FOUND);
				message.setResponseMessage(constants.USER_RECORD_NOT_FOUND);
				return message;
			}
			message.setStatus(HttpStatus.OK);
			message.setResponseMessage(constants.RECORD_FOUND);
			message.setData(userMapperImpl.userToUserDto(user));
			return message;
		} catch (Exception e) {
			message.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			message.setResponseMessage(e.getMessage());
			log.error(constants.SOMETHING_WENT_WRONG + "  " + message.getResponseMessage());
			return message;
		}
	}

	@Override
	public Message<UserDto> updatePassword(ChangePasswordDto request) {
      Message<UserDto> message=new Message<>();
      try {
    	  User user=userRepository.getById(request.getUId());
		  if(user==null || !user.getPassword().equals(request.getOldPassword())) {
			  message.setStatus(HttpStatus.NOT_FOUND);
			  message.setResponseMessage(constants.USER_RECORD_NOT_FOUND);
			  return message;
		  }
		  if (request.getNewPassword().equals(request.getConfirmPassword())) {
			  user.setPassword(request.getNewPassword());
			  userRepository.save(user);
			  message.setStatus(HttpStatus.OK);
			  message.setResponseMessage(constants.PASSWORD_CHANGED_SUCCESSFULLY);
			  message.setData(userMapperImpl.userToUserDto(user));
			  return message;
		  } else {
			  message.setStatus(HttpStatus.BAD_REQUEST);
			  message.setResponseMessage(constants.PASSWORD_NOT_MATCHED);
			  return message;
		  }
	  } catch (Exception e) {
		  message.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		  message.setResponseMessage(e.getMessage());
		  log.error(constants.SOMETHING_WENT_WRONG + "  " + message.getResponseMessage());
		  return message;
      }
	}

	@Override
	public Message<UserDto> deleteUser(Integer uId) {
		Message<UserDto> message=new Message<>();
		try {
		 Optional<User> userOptional = userRepository.findById(uId);
	        if (userOptional.isEmpty()) {
	            message.setStatus(HttpStatus.NOT_FOUND);
	            message.setResponseMessage(constants.USER_RECORD_NOT_FOUND);
	            return message;
	        }
	        
	        User user = userOptional.get();

	        // Check if Employee exists for the given uId
	        Optional<Employee> employeeOptional = employeeRepository.findById(uId);
	        employeeOptional.ifPresent(employee -> {
	            employeeRepository.delete(employee); // Delete Employee record if found
	        });

	        // Delete User record
	        userRepository.delete(user);

	        message.setStatus(HttpStatus.OK);
	        message.setResponseMessage(constants.USER_DELETED_SUCCESSFULLY);
	        return message;

	    } catch (Exception e) {
	        message.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
	        message.setResponseMessage(e.getMessage());
	        log.error(constants.SOMETHING_WENT_WRONG + "  " + message.getResponseMessage());
	        return message;
	    }

}
}
