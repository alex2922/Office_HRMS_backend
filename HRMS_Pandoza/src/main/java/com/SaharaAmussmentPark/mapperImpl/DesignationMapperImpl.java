 package com.SaharaAmussmentPark.mapperImpl;

import org.springframework.stereotype.Component;

import com.SaharaAmussmentPark.dto.DesignationDto;
import com.SaharaAmussmentPark.mapper.DesignationMapper;
import com.SaharaAmussmentPark.model.Designation;

@Component
public class DesignationMapperImpl implements DesignationMapper {

	@Override
	public DesignationDto designatioToDesignationDto(Designation designation) {
		DesignationDto dto = new DesignationDto();
		dto.setDid(designation.getDid());
		dto.setName(designation.getName());
		return dto;
		
	}

	@Override
	public Designation designationDtoToDesignation(DesignationDto dto) {
		Designation designation = new Designation();
		designation.setName(dto.getName());
		return designation;
		
		
	}

}
