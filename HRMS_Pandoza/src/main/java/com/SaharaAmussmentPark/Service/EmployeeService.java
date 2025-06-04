package com.SaharaAmussmentPark.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.SaharaAmussmentPark.Dto.EmployeeDto;
import com.SaharaAmussmentPark.Dto.Message;

public interface EmployeeService {
public Message<EmployeeDto> registerUser(EmployeeDto request);
public Message<EmployeeDto> updateEmployee(EmployeeDto request);
public Message<EmployeeDto>getByemployeeId(String employeeId);
public List<Message<EmployeeDto>> getAllEmployee();
public Message<EmployeeDto> ApproveEdit(int eid);

}
