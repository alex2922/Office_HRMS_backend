package com.SaharaAmussmentPark.mapper;

import com.SaharaAmussmentPark.Dto.EmployeeDto;
import com.SaharaAmussmentPark.model.Employee;

public interface EmployeeMapper {
	public EmployeeDto employeeToEmployeeDto(Employee employee);
	public Employee employeeDtoToEmployee(EmployeeDto employeeDto);

}
