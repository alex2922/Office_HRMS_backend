package com.SaharaAmussmentPark.MapperImpl;

import org.springframework.stereotype.Component;

import com.SaharaAmussmentPark.Dto.EmployeeDto;
import com.SaharaAmussmentPark.mapper.EmployeeMapper;
import com.SaharaAmussmentPark.model.Employee;

@Component
public class EmployeeMapperImpl implements EmployeeMapper {

	@Override
	public EmployeeDto employeeToEmployeeDto(Employee employee) {
		return new  EmployeeDto().setAadharNumber(employee.getAadharNumber())
				.setEId(employee.getEId())
				.setAccountNumber(employee.getAccountNumber())
				.setAddress(employee.getAddress())
				.setBankName(employee.getBankName())
				.setCompanyName(employee.getCompanyName())
				.setContactNumber(employee.getContactNumber())
				.setCosttoCompany(employee.getCosttoCompany())
				.setDateOfBirth(employee.getDateOfBirth())
				.setDateOfJoining(employee.getDateOfJoining())
				.setDateOfLiving(employee.getDateOfLiving())
				.setDepartment(employee.getDepartment())
				.setDesignation(employee.getDesignation())
				.setEmail(employee.getEmail())
				.setEmployeeId(employee.getEmployeeId())
				.setEmployeeName(employee.getEmployeeName())
				.setEmployeeSalary(employee.getEmployeeSalary())
				.setEmployeeStatus(employee.getEmployeeStatus())
				.setGender(employee.getGender())
				.setIfscCode(employee.getIfscCode())
				.setUanNo(employee.getUanNo())
				.setUId(employee.getUId())
				.setPolicyNumber(employee.getPolicyNumber())
				.setPanNumber(employee.getPanNumber())
				.setInsuranceCompany(employee.getInsuranceCompany())
				.setEditableAccess(employee.isEditableAccess())
				.setEmergencyContactNumber(employee.getEmergencyContactNumber())
				.setEmergencyContactName(employee.getEmergencyContactName())
				.setEmergencyContactRelation(employee.getEmergencyContactRelation())
				.setEmergencyContactAddress(employee.getEmergencyContactAddress());
		
	}

	@Override
	public Employee employeeDtoToEmployee(EmployeeDto employeeDto) {
		return new Employee().setAadharNumber(employeeDto.getAadharNumber())
				.setAccountNumber(employeeDto.getAccountNumber())
				.setAddress(employeeDto.getAddress())
				.setBankName(employeeDto.getBankName())
				.setCompanyName(employeeDto.getCompanyName())
				.setContactNumber(employeeDto.getContactNumber())
				.setCosttoCompany(employeeDto.getCosttoCompany())
				.setDateOfBirth(employeeDto.getDateOfBirth())
				.setDateOfJoining(employeeDto.getDateOfJoining())
				.setDateOfLiving(employeeDto.getDateOfLiving())
				.setDepartment(employeeDto.getDepartment())
				.setDesignation(employeeDto.getDesignation())
				.setEmail(employeeDto.getEmail())
				.setEmployeeId(employeeDto.getEmployeeId())
				.setEmployeeName(employeeDto.getEmployeeName())
				.setEmployeeSalary(employeeDto.getEmployeeSalary())
				.setEmployeeStatus(employeeDto.getEmployeeStatus())
				.setGender(employeeDto.getGender())
				.setIfscCode(employeeDto.getIfscCode())
				.setUanNo(employeeDto.getUanNo())
				.setUId(employeeDto.getUId())
				.setPanNumber(employeeDto.getPanNumber())
				.setPolicyNumber(employeeDto.getPolicyNumber())
				.setInsuranceCompany(employeeDto.getInsuranceCompany())
				.setEditableAccess(true)
				.setEmergencyContactNumber(employeeDto.getEmergencyContactNumber())
				.setEmergencyContactName(employeeDto.getEmergencyContactName())
				.setEmergencyContactRelation(employeeDto.getEmergencyContactRelation())
				.setEmergencyContactAddress(employeeDto.getEmergencyContactAddress());
	}


}
