package com.SaharaAmussmentPark.mapper;

import com.SaharaAmussmentPark.Dto.EmployeeResponseDto;
import com.SaharaAmussmentPark.Dto.SalaryDto;
import com.SaharaAmussmentPark.model.Employee;
import com.SaharaAmussmentPark.model.Salary;

public interface SalaryMapper {

	public EmployeeResponseDto salaryToEmployeeResponseDto(Salary salary,Employee emp );
	public Salary salaryDtoToSalary(SalaryDto salaryDto);
	public SalaryDto salaryToSalaryDto(Salary salary);
	
}
