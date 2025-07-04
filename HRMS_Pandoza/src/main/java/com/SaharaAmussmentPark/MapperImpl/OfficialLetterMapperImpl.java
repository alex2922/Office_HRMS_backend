package com.SaharaAmussmentPark.MapperImpl;

import org.springframework.stereotype.Component;

import com.SaharaAmussmentPark.Dto.OfficialLetterDto;
import com.SaharaAmussmentPark.mapper.OfficialLetterMapper;
import com.SaharaAmussmentPark.model.OfficialLetter;

@Component

public class OfficialLetterMapperImpl implements OfficialLetterMapper {

	@Override
	public OfficialLetterDto officialLetterToOfficialLetterDto(OfficialLetter officialLetter) {
		OfficialLetterDto dto = new OfficialLetterDto();
		dto.setOId(officialLetter.getOId());
		dto.setCompanyLogo(officialLetter.getCompanyLogo());
		dto.setCompanyName(officialLetter.getCompanyName());
		dto.setDate(officialLetter.getDate());
		dto.setDateOfJoining(officialLetter.getDateOfJoining());
		dto.setDepartment(officialLetter.getDepartment());
		dto.setDesignation(officialLetter.getDesignation());
		dto.setEmployeeName(officialLetter.getEmployeeName());
		dto.setHrManagerName(officialLetter.getHrManagerName());
		dto.setSalary(officialLetter.getSalary());
		dto.setStatus(officialLetter.getStatus());
		dto.setGender(officialLetter.getGender());
		return dto;

	}

	@Override
	public OfficialLetter officialLetterDtoToOfficialLetter(OfficialLetterDto dto) {
		OfficialLetter officialLetter = new OfficialLetter();

		officialLetter.setCompanyLogo(dto.getCompanyLogo());
		officialLetter.setCompanyName(dto.getCompanyName());
		officialLetter.setDate(dto.getDate());
		officialLetter.setDateOfJoining(dto.getDateOfJoining());
		officialLetter.setDepartment(dto.getDepartment());
		officialLetter.setDesignation(dto.getDesignation());
		officialLetter.setEmployeeName(dto.getEmployeeName());
		officialLetter.setHrManagerName(dto.getHrManagerName());
		officialLetter.setSalary(dto.getSalary());
		officialLetter.setStatus(dto.getStatus());
		officialLetter.setGender(dto.getGender());
		return officialLetter;

	}

}
