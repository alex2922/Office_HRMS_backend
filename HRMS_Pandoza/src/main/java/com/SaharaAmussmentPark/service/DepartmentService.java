package com.SaharaAmussmentPark.service;

import java.util.List;

import com.SaharaAmussmentPark.dto.DepartmentDto;
import com.SaharaAmussmentPark.dto.Message;

public interface DepartmentService {
	public Message<DepartmentDto> AddDepartment(DepartmentDto request);
	public Message<DepartmentDto> DeleteDepartment(int dId);
	public Message<DepartmentDto> UpdateDepartment(DepartmentDto request);
	public Message<DepartmentDto> GetDepartmentById(int dId);
	public List<Message<DepartmentDto>> GetAllDepartment();

}
