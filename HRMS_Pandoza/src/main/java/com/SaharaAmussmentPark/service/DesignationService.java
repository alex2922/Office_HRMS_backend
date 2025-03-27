package com.SaharaAmussmentPark.service;

import java.util.List;

import com.SaharaAmussmentPark.dto.DesignationDto;
import com.SaharaAmussmentPark.dto.Message;

public interface DesignationService {
	public Message<DesignationDto> AddDesignation(DesignationDto request);
	public Message<DesignationDto> DeleteDesignation(int did);
	public Message<DesignationDto> UpdateDesignation(DesignationDto request);
	public Message<DesignationDto> GetDesignationById(int did);
	public List<Message<DesignationDto>> GetAllDesignation();
	
	

}
