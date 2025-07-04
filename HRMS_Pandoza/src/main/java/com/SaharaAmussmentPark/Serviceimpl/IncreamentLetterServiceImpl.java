package com.SaharaAmussmentPark.Serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.SaharaAmussmentPark.Dto.IncreamentLetterDto;
import com.SaharaAmussmentPark.Dto.Message;
import com.SaharaAmussmentPark.Repository.EmployeeRepository;
import com.SaharaAmussmentPark.Repository.InceamentLetterRepository;
import com.SaharaAmussmentPark.Service.IncreamentLetterService;
import com.SaharaAmussmentPark.mapper.IncreamentLetterMapper;
import com.SaharaAmussmentPark.model.Employee;
import com.SaharaAmussmentPark.model.IncreamentLetter;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class IncreamentLetterServiceImpl implements IncreamentLetterService {
	private final EmployeeRepository employeeRepository;
	private final InceamentLetterRepository increamentLetterRepository;
	private final IncreamentLetterMapper increamentLetterMapper;

	@Override
	public Message<IncreamentLetterDto> addIncreamentLetter(IncreamentLetterDto request) {
		Message<IncreamentLetterDto> response = new Message<>();

		try {
			// 1. Fetch employee
			Employee employee = employeeRepository.findByEmployeeName(request.getEmployeeName());

			if (employee == null) {
				return response.setStatus(HttpStatus.NOT_FOUND).setResponseMessage("Employee not found.");
			}

			double grossSalary = request.getSalary();
			double basicSalary = grossSalary * 0.5;
			double hra = basicSalary * 0.4;
			double da = basicSalary * 0.2;
			double otherAllowance = grossSalary - (basicSalary + hra + da);

			IncreamentLetter letter = increamentLetterMapper.increamentLetterDtoToIncreamentLetter(request);

			letter.setEmployeeId(employee.getEmployeeId()).setGrossSalary(grossSalary).setBasicSalary(basicSalary)
					.setHra(hra).setDa(da).setOtherAllowance(otherAllowance).setDesignation(employee.getDesignation())
					.setGender(employee.getGender()).setOldSalary(employee.getEmployeeSalary());

			increamentLetterRepository.save(letter);

			IncreamentLetterDto responseDto = increamentLetterMapper.increamentLetterToIncreamentLetterDto(letter);

			return response.setStatus(HttpStatus.CREATED).setResponseMessage("Increament letter added successfully.")
					.setData(responseDto);

		} catch (Exception e) {
			return response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR)
					.setResponseMessage("Failed to add increament letter: " + e.getMessage());
		}
	}

	@Override
	public Message<IncreamentLetterDto> updateIncreamentLetter(IncreamentLetterDto request) {
		Message<IncreamentLetterDto> response = new Message<>();

		try {

			Optional<IncreamentLetter> optional = increamentLetterRepository.findById(request.getId());

			if (optional.isEmpty()) {
				return response.setStatus(HttpStatus.NOT_FOUND).setResponseMessage("Increament letter not found.");
			}

			IncreamentLetter existing = optional.get();

			Employee employee = employeeRepository.findByEmployeeId(request.getEmployeeId());

			if (employee == null) {
				return response.setStatus(HttpStatus.NOT_FOUND).setResponseMessage("Employee not found.");
			}

			double grossSalary = request.getSalary();
			double basicSalary = grossSalary * 0.5;
			double hra = basicSalary * 0.4;
			double da = basicSalary * 0.2;
			double otherAllowance = grossSalary - (basicSalary + hra + da);

			IncreamentLetter updated = increamentLetterMapper.increamentLetterDtoToIncreamentLetter(request);

			updated.setId(existing.getId()).setGrossSalary(grossSalary).setBasicSalary(basicSalary).setHra(hra)
					.setDa(da).setOtherAllowance(otherAllowance).setDesignation(employee.getDesignation())
					.setGender(employee.getGender()).setOldSalary(employee.getEmployeeSalary());

			increamentLetterRepository.save(updated);

			IncreamentLetterDto responseDto = increamentLetterMapper.increamentLetterToIncreamentLetterDto(updated);

			return response.setStatus(HttpStatus.OK).setResponseMessage("Increament letter updated successfully.")
					.setData(responseDto);

		} catch (Exception e) {
			return response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR)
					.setResponseMessage("Failed to update increament letter: " + e.getMessage());
		}
	}

	@Override
	public Message<IncreamentLetterDto> getIncreamentLetterById(int id) {
		Message<IncreamentLetterDto> response = new Message<>();

		try {

			Optional<IncreamentLetter> optional = increamentLetterRepository.findById(id);

			if (optional.isEmpty()) {
				return response.setStatus(HttpStatus.NOT_FOUND)
						.setResponseMessage("Increament letter not found with ID: " + id);
			}

			IncreamentLetterDto dto = increamentLetterMapper.increamentLetterToIncreamentLetterDto(optional.get());

			return response.setStatus(HttpStatus.OK).setResponseMessage("Increament letter fetched successfully.")
					.setData(dto);

		} catch (Exception e) {
			return response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR)
					.setResponseMessage("Failed to fetch increament letter: " + e.getMessage());
		}
	}

	@Override
	public Message<List<IncreamentLetterDto>> getAllIncreamentLetter() {
		Message<List<IncreamentLetterDto>> response = new Message<>();

		try {
			List<IncreamentLetter> letterList = increamentLetterRepository.findAll();

			if (letterList.isEmpty()) {
				return response.setStatus(HttpStatus.NOT_FOUND).setResponseMessage("No increament letters found.");
			}

			
			List<IncreamentLetterDto> dtoList = letterList.stream()
					.map(increamentLetterMapper::increamentLetterToIncreamentLetterDto).collect(Collectors.toList());

			return response.setStatus(HttpStatus.OK).setResponseMessage("Increament letters fetched successfully.")
					.setData(dtoList);

		} catch (Exception e) {
			return response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR)
					.setResponseMessage("Failed to fetch increament letters: " + e.getMessage());
		}
	}

	

	@Override
	public Message<List<IncreamentLetterDto>> getIncreamentLettersByEmployeeId(String employeeId) {
	    Message<List<IncreamentLetterDto>> response = new Message<>();

	    try {
	        List<IncreamentLetter> letters = increamentLetterRepository.findByEmployeeId(employeeId);

	        if (letters.isEmpty()) {
	            return response.setStatus(HttpStatus.NOT_FOUND)
	                           .setResponseMessage("No increament letters found for employee: " + employeeId);
	        }

	        List<IncreamentLetterDto> dtoList = letters.stream()
	            .map(increamentLetterMapper::increamentLetterToIncreamentLetterDto)
	            .collect(Collectors.toList());

	        return response.setStatus(HttpStatus.OK)
	                       .setResponseMessage("Increament letters fetched successfully.")
	                       .setData(dtoList);

	    } catch (Exception e) {
	        return response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	                       .setResponseMessage("Failed to fetch increament letters: " + e.getMessage());
	    }
	}
		
	}



