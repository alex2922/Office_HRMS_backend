package com.SaharaAmussmentPark.Serviceimpl;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.SaharaAmussmentPark.Dto.EmployeeResponseDto;
import com.SaharaAmussmentPark.Dto.Message;
import com.SaharaAmussmentPark.Dto.SalaryDto;
import com.SaharaAmussmentPark.Repository.EmployeeRepository;
import com.SaharaAmussmentPark.Repository.SalaryRepository;
import com.SaharaAmussmentPark.Service.SalaryService;
import com.SaharaAmussmentPark.Util.constants;
import com.SaharaAmussmentPark.mapper.EmployeeMapper;
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
	private final EmployeeMapper employeeMapperImpl;
	private final SalaryRepository salaryRepository;
	private final EmployeeRepository employeeRepository;

	@Override

	public Message<SalaryDto> AddSalary(SalaryDto request) {
		Message<SalaryDto> response = new Message<>();
		try {
			Employee employee = employeeRepository.findEmployeeByEmployeeId(request.getEmployeeId())
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, constants.RECORD_NOT_FOUND));
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
	public Message<SalaryDto> DeleteSalary(int sId) {
		Message<SalaryDto> response = new Message<>();
		try {
			Salary salary = new Salary();
			salary = salaryRepository.getById(sId);
			if (salary == null) {
				response.setStatus(HttpStatus.BAD_REQUEST);
				response.setResponseMessage(constants.SALARY_SLIP_NOT_FOUND);
				return response;
			}
			SalaryDto dto = salaryMapperImpl.salaryToSalaryDto(salary);
			salaryRepository.save(salary);

			response.setStatus(HttpStatus.OK);
			response.setResponseMessage(constants.SALARY_DELETED);
			return response;
		} catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setResponseMessage(constants.SOMETHING_WENT_WRONG);
			return response;
		}

	}

	@Override
	public Message<SalaryDto> getById(int sId) {
		Message<SalaryDto> response = new Message<>();
		try {
			Salary salary = new Salary();
			salary = salaryRepository.getById(sId);

			if (salary == null) {
				response.setStatus(HttpStatus.BAD_REQUEST);
				response.setResponseMessage(constants.SALARY_SLIP_NOT_FOUND);
				return response;
			}
			SalaryDto dto = salaryMapperImpl.salaryToSalaryDto(salary);
			response.setStatus(HttpStatus.OK);
			response.setResponseMessage(constants.SALARY_FOUND);
			response.setData(dto);
			return response;
		} catch (Exception e) {
			System.err.println("Error fetching Salary:" + e.getMessage());
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setResponseMessage(constants.SOMETHING_WENT_WRONG);
			return response;
		}
	}

	@Override
	public Message<SalaryDto> UpdateSalary(SalaryDto request) {
		Message<SalaryDto> response = new Message<>();
		Salary salary = null;

		try {
			salary = salaryRepository.getById(request.getSId());

			if (salary == null) {
				response.setStatus(HttpStatus.BAD_REQUEST);
				response.setResponseMessage(constants.SALARY_NOT_FOUND);
				return response;
			}

			// Update fields
			salary.setBasicSalary(request.getBasicSalary());
			salary.setEmployeeId(request.getEmployeeId());
			salary.setBonus(request.getBonus());
			salary.setReimbursement(request.getReimbursement());
			salary.setConveyance(request.getConveyance());
			salary.setOtheAllownce(request.getOtheAllownce());
			salary.setInsuranceCorporation(request.getInsuranceCorporation());
			salary.setProfessionalTax(request.getProfessionalTax());
			salary.setTds(request.getTds());
			salary.setSalaryAdvance(request.getSalaryAdvance());
			salary.setPersonalLoan(request.getPersonalLoan());
			salary.setOtherDiductions(request.getOtherDiductions());
			salary.setHra(request.getHra());
			salary.setMonth(request.getMonth());
			salary.setYear(request.getYear());
			salary.setPf(request.getPf());
			salary.setPresentDays(request.getPresentDays());
			salary.setWorkingDays(request.getWorkingDays());
			salary.setAbsentDays(request.getAbsentDays());

			double rawLop = ((double) (request.getWorkingDays() - request.getPresentDays()) / request.getWorkingDays())
					* request.getBasicSalary();
			double lop = Math.floor(rawLop);
			double earnigs = request.getBasicSalary() + request.getHra() + request.getBonus()
					+ request.getReimbursement() + request.getConveyance() + request.getOtheAllownce();
			double diductions = request.getInsuranceCorporation() + request.getProfessionalTax() + request.getTds()
					+ request.getSalaryAdvance() + request.getPersonalLoan() + request.getOtherDiductions()
					+ request.getPf();
			double netSalary = earnigs - diductions - lop;

			salary.setLop(lop);
			salary.setNetSalary(netSalary);

			salaryRepository.save(salary);
			SalaryDto dto = salaryMapperImpl.salaryToSalaryDto(salary);

			response.setStatus(HttpStatus.OK);
			response.setResponseMessage(constants.SALARY_UPDATED);
			response.setData(dto);
			return response;
		} catch (Exception e) {
			System.err.println("Error updating Salary: " + e.getMessage());
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setResponseMessage(constants.SOMETHING_WENT_WRONG);
			return response;
		}
	}

	@Override
	public Message<EmployeeResponseDto> getSalaryDetails(String employeeId, String month) {
		Message<EmployeeResponseDto> response = new Message<>();
		try {
			Salary salary = salaryRepository.findEmployeeByEmployeeIdAndMonth(employeeId, month)
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Salary details not found"));
			Employee employee = employeeRepository.findByEmployeeId(employeeId);

			response.setStatus(HttpStatus.OK);
			response.setResponseMessage(constants.RECORD_FOUND);
			response.setData(salaryMapperImpl.salaryToEmployeeResponseDto(salary, employee));

			return response;
		} catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setResponseMessage(constants.SOMETHING_WENT_WRONG);
			return response;
		}

	}
}
