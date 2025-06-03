package com.SaharaAmussmentPark.Serviceimpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.SaharaAmussmentPark.Dto.EmployeeDto;
import com.SaharaAmussmentPark.Dto.Message;
import com.SaharaAmussmentPark.Dto.UserDto;
import com.SaharaAmussmentPark.Repository.EmployeeRepository;
import com.SaharaAmussmentPark.Service.EmployeeService;
import com.SaharaAmussmentPark.Util.constants;
import com.SaharaAmussmentPark.mapper.EmployeeMapper;
import com.SaharaAmussmentPark.model.Employee;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeMapper employeeMapperImpl;
	private final EmployeeRepository employeeRepository;
	@Value("${spring.servlet.multipart.location}")
	public String uploadDirectory;

	@Override
	public Message<EmployeeDto> registerUser(@Valid EmployeeDto request) {
		Message<EmployeeDto> response = new Message<>();
		try {
			Optional<Employee> existingEmployee = employeeRepository.findByEmployeeIdOrEmailOrAadharNumber(
					request.getEmployeeId(), request.getEmail(), request.getAadharNumber());

			if (existingEmployee.isPresent()) {
				response.setStatus(HttpStatus.CONFLICT);
				response.setResponseMessage("Employee with same Employee ID, Email, or Aadhar already exists!");
				return response;
			}

			// Convert DTO to Entity
			Employee employee = employeeMapperImpl.employeeDtoToEmployee(request);

			// Save Employee
			Employee savedEmployee = employeeRepository.save(employee);

			// Convert Entity to DTO
			response.setStatus(HttpStatus.CREATED);
			response.setResponseMessage("Employee Registered Successfully!");
			response.setData(employeeMapperImpl.employeeToEmployeeDto(savedEmployee));
			return response;

		} catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setResponseMessage("Error while registering employee: " + e.getMessage());
			return response;
		}

	}

	@Override
	public Message<EmployeeDto> updateEmployee(@Valid EmployeeDto request) {
		Message<EmployeeDto> response = new Message<>();
		try {
			// Check if Employee exists
			Optional<Employee> existingEmployee = employeeRepository.findById(request.getEId());

			if (existingEmployee.isEmpty()) {
				response.setStatus(HttpStatus.NOT_FOUND);
				response.setResponseMessage("Employee Not Found!");
				return response;
			}

			Employee employee = existingEmployee.get();

			// Prevent duplicate email, aadhar, or employeeId conflicts
			Optional<Employee> duplicateCheck = employeeRepository.findByEmployeeIdOrEmailOrAadharNumber(
					request.getEmployeeId(), request.getEmail(), request.getAadharNumber());

			if (duplicateCheck.isPresent() && duplicateCheck.get().getEId() != request.getEId()) {
				response.setStatus(HttpStatus.CONFLICT);
				response.setResponseMessage("Another Employee with this Email, Employee ID, or Aadhar exists!");
				return response;
			}

			// Update fields
			employeeMapperImpl.employeeDtoToEmployee(request);
//		        employee.setEmployeeName(request.getEmployeeName());
//		        employee.setDesignation(request.getDesignation());
//		        employee.setDepartment(request.getDepartment());
//		        employee.setEmployeeStatus(request.getEmployeeStatus());
//		        employee.setContactNumber(request.getContactNumber());
//		        employee.setEmail(request.getEmail());
//		        employee.setCosttoCompany(request.getCosttoCompany());
//		        employee.setEmployeeSalary(request.getEmployeeSalary());
//		        employee.setIfscCode(request.getIfscCode());
//		        employee.setAccountNumber(request.getAccountNumber());
//		        employee.setBankName(request.getBankName());
//		        employee.setDiduction(request.getDiduction());
//		        employee.setDateOfJoining(request.getDateOfJoining());
//		        employee.setDateOfLiving(request.getDateOfLiving());
//		        employee.setDateOfBirth(request.getDateOfBirth());
//		        employee.setAadharNumber(request.getAadharNumber());
//		        employee.setAttendanceCode(request.getAttendanceCode());
//		        employee.setCompanyName(request.getCompanyName());
//		        employee.setUanNo(request.getUanNo());

			// Update fields
			employee.setEmployeeName(request.getEmployeeName());
			employee.setDesignation(request.getDesignation());
			employee.setDepartment(request.getDepartment());
			employee.setEmployeeStatus(request.getEmployeeStatus());
			employee.setContactNumber(request.getContactNumber());
			employee.setEmail(request.getEmail());
			employee.setCosttoCompany(request.getCosttoCompany());
			employee.setEmployeeSalary(request.getEmployeeSalary());
			employee.setIfscCode(request.getIfscCode());
			employee.setAccountNumber(request.getAccountNumber());
			employee.setBankName(request.getBankName());
			employee.setDateOfJoining(request.getDateOfJoining());
			employee.setDateOfLiving(request.getDateOfLiving());
			employee.setDateOfBirth(request.getDateOfBirth());
			employee.setAadharNumber(request.getAadharNumber());
			employee.setCompanyName(request.getCompanyName());
			employee.setUanNo(request.getUanNo());
			employee.setPolicyNumber(request.getPolicyNumber());
			employee.setInsuranceCompany(request.getInsuranceCompany());

			Employee updatedEmployee = employeeRepository.save(employee);

			response.setStatus(HttpStatus.OK);
			response.setResponseMessage("Employee Updated Successfully!");
			response.setData(employeeMapperImpl.employeeToEmployeeDto(updatedEmployee));

		} catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setResponseMessage("Error while updating employee: " + e.getMessage());
		}
		return response;
	}

	@Override
	public Message<EmployeeDto> getByemployeeId(String employeeId) {
		Message<EmployeeDto> response = new Message<>();
		try {
			Employee employee = employeeRepository.findByEmployeeId(employeeId);
			if (employee != null) {
				response.setStatus(HttpStatus.OK);
				response.setResponseMessage(constants.RECORD_FOUND);
				response.setData(employeeMapperImpl.employeeToEmployeeDto(employee));
				return response;
			} else {
				response.setStatus(HttpStatus.NOT_FOUND);
				response.setResponseMessage(constants.RECORD_NOT_FOUND);
				return response;
			}
		} catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setResponseMessage(e.getMessage());
			log.error(constants.SOMETHING_WENT_WRONG + "  " + response.getResponseMessage());
			return response;
		}
	}

	@Override
	public List<Message<EmployeeDto>> getAllEmployee() {
		List<Message<EmployeeDto>> message = new ArrayList<>();
		try {
			
			List<Employee> employees = employeeRepository.findAll();
			if (employees == null || employees.isEmpty()) {
				Message<EmployeeDto> message1 = new Message<>();
				message1.setStatus(HttpStatus.NOT_FOUND);
				message1.setResponseMessage(constants.EMPLOYEE_RECORD_NOT_FOUND);
				message.add(message1);
				return message;
			}
			for (Employee employee : employees) {
				Message<EmployeeDto> message1 = new Message<>();
				message1.setStatus(HttpStatus.OK);
				message1.setResponseMessage(constants.RECORD_FOUND);
				message1.setData(employeeMapperImpl.employeeToEmployeeDto(employee));
				message.add(message1);
			}
			return message;
		} catch (Exception e) {
			Message<EmployeeDto> errorMessage = new Message<>();
			errorMessage.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			errorMessage.setResponseMessage(e.getMessage());
			log.error("SOMETHING_WENT_WRONG" + "  " + errorMessage.getResponseMessage());
			message.add(errorMessage);
			return message;
		}
	}

}
