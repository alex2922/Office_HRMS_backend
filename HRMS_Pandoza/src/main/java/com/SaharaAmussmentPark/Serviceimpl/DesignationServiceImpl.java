package com.SaharaAmussmentPark.Serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.SaharaAmussmentPark.Dto.DesignationDto;
import com.SaharaAmussmentPark.Dto.Message;
import com.SaharaAmussmentPark.Repository.DepartmentRepository;
import com.SaharaAmussmentPark.Repository.DesignationRepository;
import com.SaharaAmussmentPark.Service.DesignationService;
import com.SaharaAmussmentPark.Util.constants;
import com.SaharaAmussmentPark.mapper.DesignationMapper;
import com.SaharaAmussmentPark.model.Department;
import com.SaharaAmussmentPark.model.Designation;


@Service
public class DesignationServiceImpl implements DesignationService {
	private final DesignationMapper designationMapperimpl;
	private final DesignationRepository designationrepository;
	private final DepartmentRepository departmentrepository;



	public DesignationServiceImpl(DesignationMapper designationMapperimpl, DesignationRepository designationrepository,
			DepartmentRepository departmentrepository) {
		super();
		this.designationMapperimpl = designationMapperimpl;
		this.designationrepository = designationrepository;
		this.departmentrepository = departmentrepository;
	}

	@Override
	public Message<DesignationDto> AddDesignation(DesignationDto request) {
		Message<DesignationDto> response = new Message<>();
		    try {
		        if (request == null) {
		            response.setStatus(HttpStatus.BAD_REQUEST);
		            response.setResponseMessage(constants.INVALID_DESIGNATION_DATA);
		            return response;
		        }

		        Department department = departmentrepository.findById(request.getDeptId()).orElse(null);
		        if (department == null) {
		            response.setStatus(HttpStatus.NOT_FOUND);
		            response.setResponseMessage(constants.DEPARTMENT_NOT_FOUND);
		            return response;
		        }

		        List<Designation> existingDesignations = designationrepository.findAll();
		        for (Designation d : existingDesignations) {
		            if (d.getDepartment().getDeptId() == department.getDeptId() && d.getName().equalsIgnoreCase(request.getName())) {
		                response.setStatus(HttpStatus.CONFLICT);
		                response.setResponseMessage("The designation already exists in this department.");
		                return response;
		            }
		        }
		        Designation designation = designationMapperimpl.designationDtoToDesignation(request);
		        designation.setDepartment(department);
		        designationrepository.save(designation);
		        DesignationDto designationDto = designationMapperimpl.designatioToDesignationDto(designation);

		        response.setStatus(HttpStatus.OK);
		        response.setResponseMessage(constants.DESIGNATION_ADDED);
		        response.setData(designationDto);
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
