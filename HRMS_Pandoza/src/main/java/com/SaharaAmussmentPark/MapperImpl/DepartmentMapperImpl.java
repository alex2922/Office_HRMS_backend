package com.SaharaAmussmentPark.MapperImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.SaharaAmussmentPark.Dto.DepartmentDto;
import com.SaharaAmussmentPark.Dto.DesignationDto;
import com.SaharaAmussmentPark.mapper.DepartmentMapper;
import com.SaharaAmussmentPark.model.Department;
import com.SaharaAmussmentPark.model.Designation;

@Component

public class DepartmentMapperImpl implements DepartmentMapper {
	@Override

	public DepartmentDto departmentToDepartmentDto(Department department) {
		DepartmentDto dto = new DepartmentDto();
		dto.setDeptId(department.getDeptId());
		dto.setDname(department.getDname());

		if (department.getDesignation() != null && !department.getDesignation().isEmpty()) {
			List<DesignationDto> designationDtos = department.getDesignation().stream().map(designation -> {
				DesignationDto designationDto = new DesignationDto();
				designationDto.setDid(designation.getDid());
				designationDto.setName(designation.getName());
				return designationDto;
			}).collect(Collectors.toList());

			dto.setDesignation(designationDtos);
		}

		return dto;
	}

	@Override
	public Department departmentDtoToDepartment(DepartmentDto dto) {
		Department department = new Department();
		department.setDname(dto.getDname());

		if (dto.getDesignation() != null && !dto.getDesignation().isEmpty()) {
			List<Designation> designations = dto.getDesignation().stream().map(designationDto -> {
				Designation designation = new Designation();
				designation.setName(designationDto.getName());
				designation.setDepartment(department);
				return designation;
			}).collect(Collectors.toList());

			department.setDesignation(designations);
		}

		return department;
	}
}
