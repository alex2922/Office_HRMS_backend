package com.SaharaAmussmentPark.Service;

import com.SaharaAmussmentPark.Dto.Message;
import com.SaharaAmussmentPark.Dto.OfficialLetterDto;

public interface OfficialLetterService {
	public Message<OfficialLetterDto> AddOfficialLetter(OfficialLetterDto request);
	public Message<OfficialLetterDto> UpdateOfficialLetter(OfficialLetterDto request);
	public Message<OfficialLetterDto> GetOfficialLetterById(int oId);

}
