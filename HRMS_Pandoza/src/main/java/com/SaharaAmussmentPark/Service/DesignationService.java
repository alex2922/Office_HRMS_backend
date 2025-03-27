package com.SaharaAmussmentPark.Service;

import java.util.List;

import com.SaharaAmussmentPark.Dto.DesignationDto;
import com.SaharaAmussmentPark.Dto.Message;



public interface DesignationService {
	public Message<DesignationDto> AddDesignation(DesignationDto request);
	public Message<DesignationDto> DeleteDesignation(int did);
	public Message<DesignationDto> UpdateDesignation(DesignationDto request);
	public Message<DesignationDto> GetDesignationById(int did);
	public List<Message<DesignationDto>> GetAllDesignation();
	
	

}
