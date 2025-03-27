package com.SaharaAmussmentPark.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.SaharaAmussmentPark.Util.constants;
import com.SaharaAmussmentPark.dto.DesignationDto;
import com.SaharaAmussmentPark.dto.Message;
import com.SaharaAmussmentPark.mapper.DesignationMapper;
import com.SaharaAmussmentPark.model.Designation;
import com.SaharaAmussmentPark.repository.DesignationRepository;
import com.SaharaAmussmentPark.service.DesignationService;

@Service
public class DesignationServiceImpl implements DesignationService {
	private final DesignationMapper designationMapperimpl;
	private final DesignationRepository designationrepository;

	public DesignationServiceImpl(DesignationMapper designationMapperimpl, DesignationRepository designationrepository) {
		super();
		this.designationMapperimpl = designationMapperimpl;
		this.designationrepository = designationrepository;
	}

	@Override
	public Message<DesignationDto> AddDesignation(DesignationDto request) {
		Message<DesignationDto> response = new Message<>();
		try {
			if(request == null) {
				response.setStatus(HttpStatus.BAD_REQUEST);
				response.setResponseMessage(constants.INVALID_DESIGNATION_DATA);
				return response;
			}
			Designation designation = designationMapperimpl.designationDtoToDesignation(request);
			designationrepository.save(designation);
			DesignationDto designationDto=designationMapperimpl.designatioToDesignationDto(designation);
			
			response.setStatus(HttpStatus.OK);
			response.setResponseMessage(constants.DESIGNATION_ADDED);
			return response;
		} catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setResponseMessage(constants.SOMETHING_WENT_WRONG);
			return response;
		}
		
	}

	@Override
	public Message<DesignationDto> DeleteDesignation(int did) {
		Message<DesignationDto> response=new Message<>();
		try {
			Designation designation = new Designation();
			designation=designationrepository.getById(did);
			if(designation == null) {
				response.setStatus(HttpStatus.BAD_REQUEST);
				response.setResponseMessage(constants.DESIGNATION_NOT_FOUND);
				return response;
			}
			DesignationDto dto = designationMapperimpl.designatioToDesignationDto(designation);
			designationrepository.save(designation);
			
			response.setStatus(HttpStatus.OK);
			response.setResponseMessage(constants.DESIGNATION_DELETED);
			return response;
		} catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setResponseMessage(constants.SOMETHING_WENT_WRONG);
			return response;
		}
		
	}

	@Override
	public Message<DesignationDto> UpdateDesignation(DesignationDto request) {
		Message<DesignationDto> response = new Message<>();
		Designation designation = null;
		try {
			designation=designationrepository.getById(request.getDid());
			if (designation == null) {
				response.setStatus(HttpStatus.BAD_REQUEST);
				response.setResponseMessage(constants.DESIGNATION_NOT_FOUND);
				return response;
			}
			designation.setName(request.getName());
			
			designationrepository.save(designation);
			DesignationDto dto = designationMapperimpl.designatioToDesignationDto(designation);
			
			response.setStatus(HttpStatus.OK);
			response.setResponseMessage(constants.DESIGNATION_UPDATED);
			return response;
		} catch (Exception e) {
			System.err.println("Error updating designation" + e.getMessage());
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setResponseMessage(constants.SOMETHING_WENT_WRONG);
			return response;
		}
		
		
	}

	@Override
	public Message<DesignationDto> GetDesignationById(int did) {
		Message<DesignationDto> response = new Message<>();
		try {
			Designation designation = new Designation();
			designation=designationrepository.getById(did);
			
			if(designation == null) {
				response.setStatus(HttpStatus.BAD_REQUEST);
				response.setResponseMessage(constants.DESIGNATION_NOT_FOUND);
				return response;
			}
			DesignationDto dto = designationMapperimpl.designatioToDesignationDto(designation);
			response.setStatus(HttpStatus.OK);
			response.setResponseMessage(constants.DESIGNATION_FOUND);
			response.setData(dto);
			return response;
		} catch (Exception e) {
			System.err.println("Error fetching Desgnation:" +e.getMessage());
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setResponseMessage(constants.SOMETHING_WENT_WRONG);
			return response;
		}
		
	}

	@Override
	public List<Message<DesignationDto>> GetAllDesignation() {
		List<Message<DesignationDto>> message = new ArrayList<>();
		try {
			List<Designation> designations = designationrepository.findAll();
			for(Designation designation : designations) {
				DesignationDto dto = designationMapperimpl.designatioToDesignationDto(designation);
				
				message.add(new Message<DesignationDto>(HttpStatus.OK,"Designation found successfully",dto));
			}
			return message;
		} catch (Exception e) {
			message.add(new Message<DesignationDto>(HttpStatus.INTERNAL_SERVER_ERROR,
					constants.SOMETHING_WENT_WRONG + e.getMessage(),null));
			return message;
		}
	
	}

}
