package com.SaharaAmussmentPark.Serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.SaharaAmussmentPark.Dto.DepartmentDto;
import com.SaharaAmussmentPark.Dto.DesignationDto;
import com.SaharaAmussmentPark.Dto.Message;
import com.SaharaAmussmentPark.Repository.DepartmentRepository;
import com.SaharaAmussmentPark.Service.DepartmentService;
import com.SaharaAmussmentPark.Util.constants;
import com.SaharaAmussmentPark.mapper.DepartmentMapper;
import com.SaharaAmussmentPark.mapper.DesignationMapper;
import com.SaharaAmussmentPark.model.Department;
import com.SaharaAmussmentPark.model.Designation;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

	private final DepartmentMapper departmentMapperimpl;
	private final DepartmentRepository departmentrepository;
	private final DesignationMapper designationMapper;

	

	@Override
	public Message<DepartmentDto> AddDepartment(DepartmentDto request) {
        Message<DepartmentDto> response = new Message<>();
		try {
			if(request == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setResponseMessage(constants.INVALID_DEPARTMENT_DATA);
			return response;
		}
		Department department = departmentMapperimpl.departmentDtoToDepartment(request);
		department.setDesignation(null);
		departmentrepository.save(department);
		DepartmentDto departmentDto=departmentMapperimpl.departmentToDepartmentDto(department);
		
		response.setStatus(HttpStatus.OK);
		response.setResponseMessage(constants.DEPARTMENT_ADDED);
		response.setData(departmentDto);
		return response;
		
	} catch (Exception e) {
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		response.setResponseMessage(constants.SOMETHING_WENT_WRONG);
		return response;
	  }
	}	
		

	@Override
	public Message<DepartmentDto> DeleteDepartment(int dId) {
		Message<DepartmentDto> response= new Message<>();
			try {
				Department department = new Department();
				department=departmentrepository.getById(dId);
				if(department == null) {
					response.setStatus(HttpStatus.BAD_REQUEST);
					response.setResponseMessage(constants.DEPARTMENT_NOT_FOUND);
					return response;
				}
				DepartmentDto dto = departmentMapperimpl.departmentToDepartmentDto(department);
				departmentrepository.deleteById(dId);
				
				response.setStatus(HttpStatus.OK);
				response.setResponseMessage(constants.DEPARTMENT_DELETED);
				return response;
			} catch (Exception e) {
				response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
				response.setResponseMessage(constants.SOMETHING_WENT_WRONG);
				return response;
			}
		}
	


	@Override
	public Message<DepartmentDto> UpdateDepartment(DepartmentDto request) {
		  Message<DepartmentDto> response = new Message<>();
	        try {
	            Department department = departmentrepository.findById(request.getDeptId()).orElse(null);
	            if (department == null) {
	                response.setStatus(HttpStatus.BAD_REQUEST);
	                response.setResponseMessage(constants.DEPARTMENT_NOT_FOUND);
	                return response;
	            }

	            department.setDname(request.getDname());

	            department.getDesignation().clear();
	            department.getDesignation().addAll(request.getDesignation().stream().map(d -> {Designation designation = new Designation();
	            designation.setName(d.getName());
	            designation.setDepartment(department);
	            return designation;
	                    }).collect(Collectors.toList())
	            );

	            departmentrepository.save(department);

	            response.setStatus(HttpStatus.OK);
	            response.setResponseMessage(constants.DEPARTMENT_UPDATED);
	            response.setData(departmentMapperimpl.departmentToDepartmentDto(department));
	            return response;
	        } catch (Exception e) {
	            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
	            response.setResponseMessage(constants.SOMETHING_WENT_WRONG);
	            return response;
	        }
	    }

	@Override
	public Message<DepartmentDto> GetDepartmentById(int dId) {
		Message<DepartmentDto> response = new Message<>();
		try {
			Department department = new Department();
			department=departmentrepository.getById(dId);
			
			if(department == null) {
				response.setStatus(HttpStatus.BAD_REQUEST);
				response.setResponseMessage(constants.DEPARTMENT_NOT_FOUND);
				return response;
			}
			DepartmentDto dto = departmentMapperimpl.departmentToDepartmentDto(department);
			response.setStatus(HttpStatus.OK);
			response.setResponseMessage(constants.DEPARTMENT_FOUND);
			response.setData(dto);
			return response;
		} catch (Exception e) {
			System.err.println("Error fetching Department:" +e.getMessage());
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setResponseMessage(constants.SOMETHING_WENT_WRONG);
			return response;
		}
	}

	@Override
	public List<Message<DepartmentDto>> GetAllDepartment() {
		List<Message<DepartmentDto>> message = new ArrayList<>();
		try {
			List<Department> departments = departmentrepository.findAll();
			for (Department department : departments) {
				DepartmentDto dto = departmentMapperimpl.departmentToDepartmentDto(department);
				List<DesignationDto> designationDtos = department.getDesignation().stream()
		                .map((designationMapper::designatioToDesignationDto) )
		                .collect(Collectors.toList());
		            
		            dto.setDesignation(designationDtos);
				
				message.add(new Message<DepartmentDto>(HttpStatus.OK,"Department found successfully",dto));
			}
			return message;
		
	} catch (Exception e) {
		message.add(new Message<DepartmentDto>(HttpStatus.INTERNAL_SERVER_ERROR,
				constants.SOMETHING_WENT_WRONG + e.getMessage(),null));
		return message;
	}	

	
	}

  }





