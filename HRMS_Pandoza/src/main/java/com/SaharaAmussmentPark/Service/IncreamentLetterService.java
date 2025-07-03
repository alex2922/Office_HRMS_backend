package com.SaharaAmussmentPark.Service;

import com.SaharaAmussmentPark.Dto.IncreamentLetterDto;
import com.SaharaAmussmentPark.Dto.Message;

public interface IncreamentLetterService {
	public Message<IncreamentLetterDto> addIncreamentLetter(IncreamentLetterDto request);
	public Message<IncreamentLetterDto> updateIncreamentLetter(IncreamentLetterDto request);
	public Message<IncreamentLetterDto> getIncreamentLetterById(int id);

}
