package com.SaharaAmussmentPark.Serviceimpl;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.SaharaAmussmentPark.Dto.ChangePasswordDto;
import com.SaharaAmussmentPark.Dto.EmployeeResponse;
import com.SaharaAmussmentPark.Dto.LoginDto;
import com.SaharaAmussmentPark.Dto.LoginResponseDto;
import com.SaharaAmussmentPark.Dto.Message;
import com.SaharaAmussmentPark.Dto.RestTemplateDto;
import com.SaharaAmussmentPark.Dto.UserDto;
import com.SaharaAmussmentPark.Dto.userdetailsResponseDto;
import com.SaharaAmussmentPark.Repository.EmployeeRepository;
import com.SaharaAmussmentPark.Repository.UserRepository;
import com.SaharaAmussmentPark.Service.JwtService;
import com.SaharaAmussmentPark.Service.UserService;
import com.SaharaAmussmentPark.Util.constants;
import com.SaharaAmussmentPark.mapper.UserMapper;
import com.SaharaAmussmentPark.model.Employee;
import com.SaharaAmussmentPark.model.User;

import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;
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
	private final OTPGenerateService otpService;
	private final EmailService emailService;
	private final Configuration config;

	@Value("${spring.mail.username}")
	private String sender;
	@Value("${spring.mail.password}")
	private String password;

	@Override
	public Message<UserDto> registerUser(UserDto request) {
		Message<UserDto> message = new Message<>();
		try {
			User user = userRepository.getByEmail(request.getEmail());
			if (user != null) {
				message.setResponseMessage(constants.EMAIL_ALREADY_EXISTS);
				message.setStatus(HttpStatus.CONFLICT);
				return message;
			}
			user = userMapperImpl.userDtoToUser(request);
			userRepository.save(user);
			message.setResponseMessage(constants.USER_REGISTERED_SUCCESSFULLY);
			message.setStatus(HttpStatus.OK);
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
	public Message<LoginResponseDto> loginUser(LoginDto request) {
		Message<LoginResponseDto> message = new Message<>();
		User user = null;
		LoginResponseDto userloginDto = null;
		try {
			user = userRepository.getByEmailAndPassword(request.getEmail(), request.getPassword());
			if (user == null) {
				message.setStatus(HttpStatus.NOT_FOUND);
				message.setResponseMessage(constants.RECORD_NOT_FOUND);
				log.info("RECORD_NOT_FOUND");
				return message;
			}
			String token = jwtService.genrateToken(user);
			userloginDto = userMapperImpl.userToLoginResponseDto(user);
			userloginDto.setToken(token);
			message.setStatus(HttpStatus.OK);
			message.setResponseMessage(constants.LOGIN_SUCCESSFULL);
			message.setData(userloginDto);
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
	public List<Message<UserDto>> getAllUsers() {
		List<Message<UserDto>> messages = new ArrayList<>();
		try {
			List<User> users = userRepository.findAll();

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
	    Message<UserDto> response = new Message<>();
	    User user = null;
	    try {
	    	user=userRepository.getById(request.getUId());
	    	if(user == null) {
	    		response.setStatus(HttpStatus.BAD_REQUEST);
	    		response.setResponseMessage(constants.USER_NOT_FOUND);
	    		return response;
	    	}
	    
	        user.setEmail(request.getEmail());
	        user.setRole(request.getRole());
//	        user.setUId(request.getUId());
	        if (request.getCreatedDate() != null) {
	            user.setCreatedDate(request.getCreatedDate());
	        }

	        // Don't overwrite password with null
	        if (request.getPassword() != null && !request.getPassword().isBlank()) {
	            user.setPassword(request.getPassword());
	        }
	        
	   
	        userRepository.save(user);
	        UserDto dto = userMapperImpl.userToUserDto(user);

	        response.setStatus(HttpStatus.OK);
	        response.setResponseMessage(constants.USER_UPDATED_SUCCESSFULLY);
	        response.setData(dto);
	        return response;
	    } catch (Exception e) {
	    	System.err.println("Error updating User:" +e.getMessage());
	    	response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
	    	response.setResponseMessage(constants.SOMETHING_WENT_WRONG);
	    	return response;
	   
	    }
	}

	@Override
	public Message<userdetailsResponseDto> getUserById(Integer uId) {
		 Message<userdetailsResponseDto> message = new Message<>();
		    System.out.println("Welcome to service" + uId);

		    try {
		        User user = userRepository.getById(uId);
		        if (user == null) {
		            message.setStatus(HttpStatus.NOT_FOUND);
		            message.setResponseMessage(constants.USER_RECORD_NOT_FOUND);
		            return message;
		        }

		        userdetailsResponseDto responseDto = new userdetailsResponseDto();
		        responseDto.setUId(user.getUId());
		        responseDto.setEmail(user.getEmail());
		        responseDto.setRole(user.getRole());

		        long totalEmployees = employeeRepository.count(); // get total employee count
		        responseDto.setTotalEmployeeCount(totalEmployees);

		        if ("EMPLOYEE".equalsIgnoreCase(user.getRole())) {
		            Employee emp = employeeRepository.findByuId(uId);
		            if (emp == null) {
		                message.setStatus(HttpStatus.NOT_FOUND);
		                message.setResponseMessage("Employee record not found");
		                return message;
		            }
		            responseDto.setEmployeeId(emp.getEmployeeId());
		        }

		        message.setStatus(HttpStatus.OK);
		        message.setResponseMessage(constants.RECORD_FOUND);
		        message.setData(responseDto);
		        return message;

		    } catch (Exception e) {
		        message.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		        message.setResponseMessage(e.getMessage());
		        log.error(constants.SOMETHING_WENT_WRONG + "  " + message.getResponseMessage());
		        return message;
		    }
		}
	@Override
	public Message<UserDto> sendOtp(String email) {
		Message<UserDto> message = new Message<>();
		User user = userRepository.getByEmail(email);
		try {
			if (user == null) {
				message.setStatus(HttpStatus.NOT_FOUND);
				message.setResponseMessage(constants.RECORD_NOT_FOUND);
				return message;
			} else {
				String otp = otpService.generateOTP(email);
				Properties props = new Properties();
				Session session = Session.getInstance(props, new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(sender, password);
					}
				});

				MimeMessage mailMessage = new MimeMessage(session);

				MimeMessageHelper helper = new MimeMessageHelper(mailMessage,
						MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
				Map<String, Object> model = new HashMap<>();
				model.put("request1", otp);
				model.put("user", user);
				Template t = config.getTemplate("Otp.html");
				String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
//					 SimpleMailMessage mailMessage = new SimpleMailMessage();
				helper.setFrom(sender);
				helper.setTo(user.getEmail());
				helper.setSubject("Forgot Password!!");
				helper.setText(html, true);

				emailService.sendEmail(mailMessage);
				user.setOtp(otp);
				userRepository.save(user);
				message.setStatus(HttpStatus.OK);
				message.setResponseMessage(constants.OTP_SENT_SUCCESSFULLY);
				return message;
			}
		} catch (Exception e) {
			message.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			message.setResponseMessage(e.getMessage());
			return message;
		}

	}

	@Override
	public Message<UserDto> verifyOtp(String email, String otp) {
		Message<UserDto> message = new Message<>();
		try {
			User user = userRepository.getByEmail(email);

			if (user == null) {
				message.setStatus(HttpStatus.NOT_FOUND);
				message.setResponseMessage(constants.USER_NOT_FOUND);
				return message;
			}

			if (user.getOtp() != null && user.getOtp().equals(otp)) {
				message.setStatus(HttpStatus.OK);
				message.setResponseMessage(constants.OTP_VERIFIED_SUCCESSFULLY);
			} else {
				message.setStatus(HttpStatus.BAD_REQUEST);
				message.setResponseMessage(constants.INVALID_OTP);
			}

		} catch (Exception e) {
			message.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			message.setResponseMessage(constants.SOMETHING_WENT_WRONG + ": " + e.getMessage());
		}
		return message;
	}

	@Override
	public Message<UserDto> updatePassword(ChangePasswordDto request) {
		Message<UserDto> message = new Message<>();
		try {
			User user = userRepository.getByEmail(request.getEmail());
			if (user == null) {
				message.setStatus(HttpStatus.NOT_FOUND);
				message.setResponseMessage(constants.USER_RECORD_NOT_FOUND);
				return message;
			} else if (user.getPassword().equals(request.getNewPassword())) {
				message.setStatus(HttpStatus.BAD_REQUEST);
				message.setResponseMessage(constants.PASSWORD_AND_NEW_PASSWORD_SHOULD_NOT_BE_SAME);
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
		Message<UserDto> message = new Message<>();
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

	@Override
	public Message<EmployeeResponse> getByEmail(String email) {
		// TODO Auto-generated method stub
		Message<EmployeeResponse> message = new Message<>();
		EmployeeResponse dto = new EmployeeResponse();
		try {
			User user = userRepository.getByEmail(email);
			if (user == null) {
				message.setStatus(HttpStatus.NOT_FOUND);
				message.setResponseMessage(constants.USER_RECORD_NOT_FOUND);
				return message;
			}
			Employee employee = employeeRepository.findDetailsByuId(user.getUId());

			dto.setIfscCode(employee.getIfscCode());

			dto.setPanNumber(employee.getPanNumber());
			dto.setAccountNumber(employee.getAccountNumber());

			dto.setBankName(employee.getBankName());

			dto.setUanNo(employee.getUanNo());

			message.setStatus(HttpStatus.OK);
			message.setResponseMessage(constants.RECORD_FOUND);
			message.setData(dto);
			return message;
		} catch (Exception e) {
			message.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			message.setResponseMessage(e.getMessage());
			log.error(constants.SOMETHING_WENT_WRONG + "  " + message.getResponseMessage());
			return message;
		}
	}

	@Override
	public Message<RestTemplateDto> findByEmail(String email) {
		// TODO Auto-generated method stub
		Message<RestTemplateDto> message = new Message<>();
		try {
			User user = userRepository.getByEmail(email);
			if (user == null) {
				message.setStatus(HttpStatus.NOT_FOUND);
				message.setResponseMessage(constants.USER_RECORD_NOT_FOUND);
				return message;
			}

			message.setStatus(HttpStatus.OK);
			message.setResponseMessage(constants.RECORD_FOUND);
			message.setData(userMapperImpl.userToRestTemplateDto(user));
			return message;
		} catch (Exception e) {
			message.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			message.setResponseMessage(e.getMessage());
			log.error(constants.SOMETHING_WENT_WRONG + "  " + message.getResponseMessage());
			return message;
		}
	}

}
