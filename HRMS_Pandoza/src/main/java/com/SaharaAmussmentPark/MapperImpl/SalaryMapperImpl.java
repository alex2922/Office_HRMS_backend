package com.SaharaAmussmentPark.MapperImpl;

import org.springframework.stereotype.Component;

import com.SaharaAmussmentPark.Dto.EmployeeResponseDto;
import com.SaharaAmussmentPark.Dto.SalaryDto;
import com.SaharaAmussmentPark.mapper.SalaryMapper;
import com.SaharaAmussmentPark.model.Employee;
import com.SaharaAmussmentPark.model.Salary;

@Component
public class SalaryMapperImpl implements SalaryMapper {

	@Override
	public SalaryDto salaryToSalaryDto(Salary salary) {
		return new SalaryDto()
				
				.setBasicSalary(salary.getBasicSalary())
				.setDeduction(salary.getDiduction())
				.setHra(salary.getHra())
				.setMonth(salary.getMonth())
				.setYear(salary.getYear())
				.setEmployeeId(salary.getEmployeeId())
				.setSId(salary.getSId())
				.setPf(salary.getPf())
				.setLop(salary.getLop())
				.setBasicSalary(salary.getBasicSalary())
				.setPresentDays(salary.getPresentDays())
				.setWorkingDays(salary.getWorkingDays())
				.setWorkingDays(salary.getWorkingDays());
				
				
	}

	@Override
	public Salary salaryDtoToSalary(SalaryDto salaryDto) {
		  double rawLop  = ((double) (salaryDto.getWorkingDays() - salaryDto.getPresentDays()) / salaryDto.getWorkingDays()) * salaryDto.getBasicSalary();
		  double lop = Math.floor(rawLop); 
		  double netSalary = salaryDto.getBasicSalary()  - salaryDto.getDeduction() - salaryDto.getPf() - lop;

		return new Salary()
				.setBasicSalary(salaryDto.getBasicSalary())
				.setDiduction(salaryDto.getDeduction())
				.setEmployeeId(salaryDto.getEmployeeId())
				.setHra(salaryDto.getHra())
				.setMonth(salaryDto.getMonth())
				.setYear(salaryDto.getYear())
				.setPf(salaryDto.getPf())
				.setPresentDays(salaryDto.getPresentDays())
				.setWorkingDays(salaryDto.getWorkingDays())
				.setLop(lop)
				.setNetSalary(netSalary)
				.setAbsentDays(salaryDto.getAbsentDays());
			
	}

	@Override
	public EmployeeResponseDto salaryToEmployeeResponseDto(Salary salary,Employee emp) {
		return new EmployeeResponseDto()
				.setEmployeeName(emp.getEmployeeName())
				.setDepartment(emp.getDepartment())
				.setDesignation(emp.getDesignation())
				.setAccountNumber(emp.getAccountNumber())
				.setBankName(emp.getBankName())
				.setUanNo(emp.getUanNo())
				.setSId(emp.getAttendanceCode())
				.setEmployeeId(emp.getEmployeeId())
				.setMonth(salary.getMonth())
				.setYear(salary.getYear())
				.setBasicSalary(salary.getBasicSalary())
				.setHra(salary.getHra())
				.setNetSalary(salary.getNetSalary())
				.setDeduction(salary.getDiduction())
				.setPf(salary.getPf())
				.setLop(salary.getLop())
				.setPresentDays(salary.getPresentDays())
				.setAbsentDays(salary.getAbsentDays())
				.setPresentDays(salary.getPresentDays())
				.setWorkingDays(salary.getWorkingDays());
				
						
				
				
		
		
	}


	}


