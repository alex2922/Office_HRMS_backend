package com.SaharaAmussmentPark.Service;

import java.util.List;

import com.SaharaAmussmentPark.Dto.IncreamentLetterDto;
import com.SaharaAmussmentPark.Dto.Message;

public interface IncreamentLetterService {
	public Message<IncreamentLetterDto> addIncreamentLetter(IncreamentLetterDto request);
	public Message<IncreamentLetterDto> updateIncreamentLetter(IncreamentLetterDto request);
	public Message<IncreamentLetterDto> getIncreamentLetterById(int id);
	public Message<List<IncreamentLetterDto>> getAllIncreamentLetter();
	public Message<List<IncreamentLetterDto>> getIncreamentLettersByEmployeeId(String employeeId);
	

}
