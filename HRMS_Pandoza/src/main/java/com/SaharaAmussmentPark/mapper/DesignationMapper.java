package com.SaharaAmussmentPark.mapper;

import com.SaharaAmussmentPark.Dto.DesignationDto;
import com.SaharaAmussmentPark.model.Designation;

public interface DesignationMapper {
	public DesignationDto designatioToDesignationDto(Designation designation);
	public Designation designationDtoToDesignation(DesignationDto dto);

}
