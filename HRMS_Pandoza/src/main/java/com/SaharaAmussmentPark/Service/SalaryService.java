package com.SaharaAmussmentPark.Service;

import com.SaharaAmussmentPark.Dto.Message;
import com.SaharaAmussmentPark.Dto.SalaryDto;

public interface SalaryService {
public Message<SalaryDto> AddSalary(SalaryDto request);
public Message<SalaryDto> getSalaryDetails(String employeeId, String month);
public Message<SalaryDto> UpdateSalary(SalaryDto request);
public Message<SalaryDto> DeleteSalary(int eId);
public Message<SalaryDto> getById(int eId);
}
