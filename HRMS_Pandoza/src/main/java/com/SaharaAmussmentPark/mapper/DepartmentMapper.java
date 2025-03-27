package com.SaharaAmussmentPark.mapper;

import com.SaharaAmussmentPark.dto.DepartmentDto;
import com.SaharaAmussmentPark.model.Department;

public interface DepartmentMapper {
	public DepartmentDto departmentToDepartmentDto(Department department);
	public Department departmentDtoToDepartment(DepartmentDto dto);

}
