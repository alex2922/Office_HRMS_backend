package com.SaharaAmussmentPark.Service;

import com.SaharaAmussmentPark.Dto.Message;
import com.SaharaAmussmentPark.Dto.SalaryDto;
import com.SaharaAmussmentPark.Dto.SalaryResponse;

public interface SalaryService {
public Message<SalaryDto> AddSalary(SalaryDto request);
public Message<SalaryDto> getSalaryDetails(String employeeId, String month);
}
