package com.SaharaAmussmentPark.Serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.SaharaAmussmentPark.Dto.Message;
import com.SaharaAmussmentPark.Dto.OfficialLetterDto;
import com.SaharaAmussmentPark.Repository.OfficialLetterRepository;
import com.SaharaAmussmentPark.Service.OfficialLetterService;
import com.SaharaAmussmentPark.Util.constants;
import com.SaharaAmussmentPark.mapper.OfficialLetterMapper;
import com.SaharaAmussmentPark.model.OfficialLetter;

@Service




public class OfficialLetterServiceImpl implements OfficialLetterService {
	private final OfficialLetterMapper officialLetterMapperimpl;
	private final OfficialLetterRepository officialLetterRepository;

	public OfficialLetterServiceImpl(OfficialLetterMapper officialLetterMapperimpl,
			OfficialLetterRepository officialLetterRepository) {
		super();
		this.officialLetterMapperimpl = officialLetterMapperimpl;
		this.officialLetterRepository = officialLetterRepository;
	}

	@Override
	public Message<OfficialLetterDto> AddOfficialLetter(OfficialLetterDto request) {
		Message<OfficialLetterDto> response = new Message<>();
		try {
			if(request == null) {
				response.setStatus(HttpStatus.BAD_REQUEST);
				response.setResponseMessage(constants.INVALID_DATA);
				return response;
			}
			OfficialLetter officialLetter = officialLetterMapperimpl.officialLetterDtoToOfficialLetter(request);
			officialLetterRepository.save(officialLetter);
			OfficialLetterDto officialLetterDto=officialLetterMapperimpl.officialLetterToOfficialLetterDto(officialLetter);
			
			response.setStatus(HttpStatus.OK);
			response.setResponseMessage(constants.OFFICIAL_LETTER_ADDED);
			response.setData(officialLetterDto);
			return response;
		} catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setResponseMessage(constants.SOMETHING_WENT_WRONG);
			return response;
		}
		
	}

	@Override
	public Message<OfficialLetterDto> UpdateOfficialLetter(OfficialLetterDto request) {
		Message<OfficialLetterDto> response = new Message<>();
		OfficialLetter officialLetter = null;
		try {
			officialLetter=officialLetterRepository.getById(request.getOId());
			if(officialLetter == null) {
				response.setStatus(HttpStatus.BAD_REQUEST);
				response.setResponseMessage(constants.OFFICIAL_LETTER_NOT_FOUND);
				return response;
			}
			officialLetter.setOname(request.getOname());
			officialLetter.setTemplate(request.getTemplate());
			officialLetter.setCompanyLogo(request.getCompanyLogo());
			officialLetter.setCompanyName(request.getCompanyName());
			officialLetter.setDate(request.getDate());
			officialLetter.setDateOfJoining(request.getDateOfJoining());
			officialLetter.setDepartment(request.getDepartment());
			officialLetter.setDesignation(request.getDesignation());
			officialLetter.setEmployeeName(request.getEmployeeName());
			officialLetter.setHrManagerName(request.getHrManagerName());
			officialLetter.setSalary(request.getSalary());
			officialLetter.setTag(request.getTag());
			
			
			officialLetterRepository.save(officialLetter);
			OfficialLetterDto dto = officialLetterMapperimpl.officialLetterToOfficialLetterDto(officialLetter);
			
			response.setStatus(HttpStatus.OK);
			response.setResponseMessage(constants.OFFICIAL_LETTER_UPDATED);
			response.setData(dto);
			return response;
		} catch (Exception e) {
			System.err.println("Erroe updating OfficialLetter:" +e.getMessage());
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setResponseMessage(constants.SOMETHING_WENT_WRONG);
			return response;
			
		}
	
	}

	@Override
	public Message<OfficialLetterDto> GetOfficialLetterById(int oId) {
		Message<OfficialLetterDto> response = new Message<>();
		try {
			OfficialLetter officialLetter = new OfficialLetter();
			officialLetter=officialLetterRepository.getById(oId);
			
			if(officialLetter == null) {
				response.setStatus(HttpStatus.BAD_REQUEST);
				response.setResponseMessage(constants.OFFICIAL_LETTER_NOT_FOUND);
				return response;
				
			}
			OfficialLetterDto dto = officialLetterMapperimpl.officialLetterToOfficialLetterDto(officialLetter);
			response.setStatus(HttpStatus.OK);
			response.setResponseMessage(constants.OFFICIAL_LETTER_FOUND);
			response.setData(dto);
			return response;
		} catch (Exception e) {
			System.err.println("Error fetching OfficialLetter:" +e.getMessage());
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setResponseMessage(constants.SOMETHING_WENT_WRONG);
			return response;
			
		}
	
	}

	@Override
	public Message<OfficialLetterDto> GetOfficialLetterByName(String oname) {
		Message<OfficialLetterDto> response = new Message<>();
		try {
			OfficialLetter officialLetter = new OfficialLetter();
			officialLetter=officialLetterRepository.findByoname(oname);
			
			if(officialLetter == null) {
				response.setStatus(HttpStatus.BAD_REQUEST);
				response.setResponseMessage(constants.OFFICIAL_LETTER_NOT_FOUND);
				return response;
			}
			OfficialLetterDto dto = officialLetterMapperimpl.officialLetterToOfficialLetterDto(officialLetter);
			response.setStatus(HttpStatus.OK);
			response.setResponseMessage(constants.OFFICIAL_LETTER_FOUND);
			response.setData(dto);
			return response;
		} catch (Exception e) {
			System.err.println("Error fetching Official Letter" +e.getMessage());
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setResponseMessage(constants.SOMETHING_WENT_WRONG);
			return response;
			
		}
	}

	@Override
	public List<Message<OfficialLetterDto>> GetAllOfficialLetter(){
		 List<Message<OfficialLetterDto>> responseList = new ArrayList<>();
		    try {
		        Iterable<OfficialLetter> officialLetters = officialLetterRepository.findAll();

		        for (OfficialLetter letter : officialLetters) {
		            OfficialLetterDto dto = officialLetterMapperimpl.officialLetterToOfficialLetterDto(letter);
		            Message<OfficialLetterDto> message = new Message<>();
		            message.setStatus(HttpStatus.OK);
		            message.setResponseMessage(constants.OFFICIAL_LETTER_FOUND);
		            message.setData(dto);
		            responseList.add(message);
		        }

		        // If no letters found, add a message indicating so
		        if (responseList.isEmpty()) {
		            Message<OfficialLetterDto> emptyMessage = new Message<>();
		            emptyMessage.setStatus(HttpStatus.NOT_FOUND);
		            emptyMessage.setResponseMessage(constants.OFFICIAL_LETTER_NOT_FOUND);
		            responseList.add(emptyMessage);
		        }

		        return responseList;
		    } catch (Exception e) {
		        System.err.println("Error fetching Official Letter: " + e.getMessage());

		        Message<OfficialLetterDto> errorMessage = new Message<>();
		        errorMessage.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		        errorMessage.setResponseMessage(constants.SOMETHING_WENT_WRONG);
		        responseList.add(errorMessage);

		        return responseList;
		    }
		}
		


}
