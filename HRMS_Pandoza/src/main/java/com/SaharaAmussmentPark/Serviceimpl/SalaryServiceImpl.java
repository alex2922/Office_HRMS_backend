package com.SaharaAmussmentPark.Serviceimpl;

import java.io.ByteArrayOutputStream;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.SaharaAmussmentPark.Dto.Message;
import com.SaharaAmussmentPark.Dto.SalaryDto;
import com.SaharaAmussmentPark.Dto.SalaryResponse;
import com.SaharaAmussmentPark.Repository.EmployeeRepository;
import com.SaharaAmussmentPark.Repository.SalaryRepository;
import com.SaharaAmussmentPark.Service.SalaryService;
import com.SaharaAmussmentPark.Util.constants;
import com.SaharaAmussmentPark.mapper.SalaryMapper;
import com.SaharaAmussmentPark.model.Employee;
import com.SaharaAmussmentPark.model.Salary;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class SalaryServiceImpl implements SalaryService {

	private final SalaryMapper salaryMapperImpl;
	private final SalaryRepository salaryRepository;
	private final EmployeeRepository employeeRepository;
	@Override
	public Message<SalaryDto> AddSalary(SalaryDto request) {
		Message<SalaryDto> response = new Message<>();
		try {
			 Employee employee = employeeRepository.findEmployeeByEmployeeId(request.getEmployeeId())
		                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,constants.RECORD_NOT_FOUND));
			 Salary salary = salaryMapperImpl.salaryDtoToSalary(request);
			 salaryRepository.save(salary);
			 response.setStatus(HttpStatus.OK);
			 response.setResponseMessage(constants.SALARY_ADDED_SUCCESSFULLY);
			 response.setData(salaryMapperImpl.salaryToSalaryDto(salary));
			 return response;
		} catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setResponseMessage(constants.SOMETHING_WENT_WRONG);
			return response;
		}
	}
		@Override
		public Message<SalaryDto> getSalaryDetails(String employeeId, String month) {
			Message<SalaryDto> response = new Message<>();
			try {
				Salary salary = salaryRepository.findEmployeeByEmployeeIdAndMonth(employeeId, month)
		                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Salary details not found"));

		        response.setStatus(HttpStatus.OK);
		        response.setResponseMessage(constants.RECORD_FOUND);
		        response.setData(salaryMapperImpl.salaryToSalaryDto(salary));
		        return response;
			} catch (Exception e) {
				response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
				response.setResponseMessage(constants.SOMETHING_WENT_WRONG);
				return response;
			}
		}
}

