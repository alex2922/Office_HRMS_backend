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
		dto.setOname(officialLetter.getOname());
		dto.setTemplate(officialLetter.getTemplate());
		return dto;
		
	}

	@Override
	public OfficialLetter officialLetterDtoToOfficialLetter(OfficialLetterDto dto) {
		OfficialLetter officialLetter = new OfficialLetter();
		officialLetter.setOname(dto.getOname());
		officialLetter.setTemplate(dto.getTemplate());
		return officialLetter;
		
	}

}
