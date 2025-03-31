package com.SaharaAmussmentPark.Service;

import java.util.List;

import com.SaharaAmussmentPark.Dto.DepartmentDto;
import com.SaharaAmussmentPark.Dto.Message;



public interface DepartmentService {
	public Message<DepartmentDto> AddDepartment(DepartmentDto request);
	public Message<DepartmentDto> DeleteDepartment(int dId);
	public Message<DepartmentDto> UpdateDepartment(DepartmentDto request);
	public Message<DepartmentDto> GetDepartmentById(int dId);
	public List<Message<DepartmentDto>> GetAllDepartment();

}
