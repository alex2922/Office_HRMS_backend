package com.SaharaAmussmentPark.mapper;

import com.SaharaAmussmentPark.Dto.OfficialLetterDto;
import com.SaharaAmussmentPark.model.OfficialLetter;

public interface OfficialLetterMapper {
	public OfficialLetterDto officialLetterToOfficialLetterDto(OfficialLetter officialLetter);
	public OfficialLetter officialLetterDtoToOfficialLetter(OfficialLetterDto dto);

}
