package com.SaharaAmussmentPark.Service;

import java.util.List;
import java.util.Map;

import com.SaharaAmussmentPark.Dto.EmployeeResponseDto;
import com.SaharaAmussmentPark.Dto.Message;
import com.SaharaAmussmentPark.Dto.SalaryDto;

public interface SalaryService {
public Message<SalaryDto> AddSalary(SalaryDto request);
public Message<EmployeeResponseDto> getSalaryDetails(String employeeId, String month);
public Message<SalaryDto> UpdateSalary(SalaryDto request);
public Message<SalaryDto> DeleteSalary(int eId);
public Message<SalaryDto> getById(int eId);
public Map<String, Object> findAllSalaryByemployeeId(String employeeId);
public Map<String, Object> findAllSalaryBymonthAndsalary(String month, String year);
public Map<String, Object>getAllSalary();

}
