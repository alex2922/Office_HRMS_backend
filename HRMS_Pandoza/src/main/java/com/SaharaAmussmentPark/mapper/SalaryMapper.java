package com.SaharaAmussmentPark.mapper;

import com.SaharaAmussmentPark.Dto.SalaryDto;
import com.SaharaAmussmentPark.model.Salary;

public interface SalaryMapper {

	public SalaryDto salaryToSalaryDto(Salary salary);
	public Salary salaryDtoToSalary(SalaryDto salaryDto);
}
