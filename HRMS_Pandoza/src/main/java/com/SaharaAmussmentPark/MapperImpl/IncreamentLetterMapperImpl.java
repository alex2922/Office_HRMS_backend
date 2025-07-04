package com.SaharaAmussmentPark.MapperImpl;

import org.springframework.stereotype.Component;

import com.SaharaAmussmentPark.Dto.IncreamentLetterDto;
import com.SaharaAmussmentPark.mapper.IncreamentLetterMapper;
import com.SaharaAmussmentPark.model.IncreamentLetter;

@Component
public class IncreamentLetterMapperImpl implements IncreamentLetterMapper {

	@Override
	public IncreamentLetterDto increamentLetterToIncreamentLetterDto(IncreamentLetter increamentLetter) {
		return new IncreamentLetterDto().setDate(increamentLetter.getDate())
				.setEmployeeId(increamentLetter.getEmployeeId()).setEmployeeName(increamentLetter.getEmployeeName())
				.setHrManagerName(increamentLetter.getHrManagerName()).setBasicSalary(increamentLetter.getBasicSalary())
				.setDa(increamentLetter.getDa()).setDesignation(increamentLetter.getDesignation())
				.setGender(increamentLetter.getGender()).setGrossSalary(increamentLetter.getGrossSalary())
				.setHra(increamentLetter.getHra()).setOtherAllowance(increamentLetter.getOtherAllowance())
				.setSalary(increamentLetter.getSalary());

	}

	@Override
	public IncreamentLetter increamentLetterDtoToIncreamentLetter(IncreamentLetterDto increamentLetterDto) {
		return new IncreamentLetter().setDate(increamentLetterDto.getDate())
				.setEmployeeId(increamentLetterDto.getEmployeeId())
				.setEmployeeName(increamentLetterDto.getEmployeeName())
				.setHrManagerName(increamentLetterDto.getHrManagerName())
				.setBasicSalary(increamentLetterDto.getBasicSalary()).setDa(increamentLetterDto.getDa())
				.setDesignation(increamentLetterDto.getDesignation()).setGender(increamentLetterDto.getGender())
				.setGrossSalary(increamentLetterDto.getGrossSalary()).setHra(increamentLetterDto.getHra())
				.setOtherAllowance(increamentLetterDto.getOtherAllowance()).setSalary(increamentLetterDto.getSalary());
	}

}
