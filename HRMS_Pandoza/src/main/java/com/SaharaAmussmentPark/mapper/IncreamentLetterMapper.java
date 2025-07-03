package com.SaharaAmussmentPark.mapper;

import com.SaharaAmussmentPark.Dto.IncreamentLetterDto;
import com.SaharaAmussmentPark.model.IncreamentLetter;

public interface IncreamentLetterMapper {
	public IncreamentLetterDto increamentLetterToIncreamentLetterDto(IncreamentLetter increamentLetter);
	public IncreamentLetter increamentLetterDtoToIncreamentLetter(IncreamentLetterDto increamentLetterDto);

}
