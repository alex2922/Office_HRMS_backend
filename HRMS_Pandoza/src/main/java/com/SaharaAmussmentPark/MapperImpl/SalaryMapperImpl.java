package com.SaharaAmussmentPark.MapperImpl;

import org.springframework.stereotype.Component;

import com.SaharaAmussmentPark.Dto.EmployeeResponseDto;
import com.SaharaAmussmentPark.Dto.SalaryDto;
import com.SaharaAmussmentPark.mapper.SalaryMapper;
import com.SaharaAmussmentPark.model.Employee;
import com.SaharaAmussmentPark.model.Salary;

@Component
public class SalaryMapperImpl implements SalaryMapper {

	@Override
	public SalaryDto salaryToSalaryDto(Salary salary) {
		return new SalaryDto()
				
				.setBasicSalary(salary.getBasicSalary())
				.setEmployeeId(salary.getEmployeeId())
				.setBonus(salary.getBonus())
				.setReimbursement(salary.getReimbursement())
				.setConveyance(salary.getConveyance())
				.setOtheAllownce(salary.getOtheAllownce())
				.setInsuranceCorporation(salary.getInsuranceCorporation())
				.setProfessionalTax(salary.getProfessionalTax())
				.setTds(salary.getTds())
				.setSalaryAdvance(salary.getSalaryAdvance())
				.setPersonalLoan(salary.getPersonalLoan())
				.setOtherDiductions(salary.getOtherDiductions())
				.setHra(salary.getHra())
				.setMonth(salary.getMonth())
				.setYear(salary.getYear())
				.setPf(salary.getPf())
				.setPresentDays(salary.getPresentDays())
				.setWorkingDays(salary.getWorkingDays())
				.setLop(salary.getLop())
				.setNetSalary(salary.getNetSalary())
				.setAbsentDays(salary.getAbsentDays());
			
				
	}

	@Override
	public Salary salaryDtoToSalary(SalaryDto salaryDto) {
		  double rawLop  = ((double) (salaryDto.getWorkingDays() - salaryDto.getPresentDays()) / salaryDto.getWorkingDays()) * salaryDto.getBasicSalary();
		  double lop = Math.floor(rawLop); 
		  double earnigs=salaryDto.getBasicSalary()+salaryDto.getHra()+salaryDto.getBonus()+salaryDto.getReimbursement()+salaryDto.getConveyance()+salaryDto.getOtheAllownce();
		  double diductions=salaryDto.getInsuranceCorporation()+salaryDto.getProfessionalTax()+salaryDto.getTds()+salaryDto.getSalaryAdvance()+salaryDto.getPersonalLoan()+salaryDto.getOtherDiductions()+salaryDto.getPf();
		  double netSalary = earnigs-diductions - lop;

		return new Salary()
				.setBasicSalary(salaryDto.getBasicSalary())
				.setEmployeeId(salaryDto.getEmployeeId())
				.setBonus(salaryDto.getBonus())
				.setReimbursement(salaryDto.getReimbursement())
				.setConveyance(salaryDto.getConveyance())
				.setOtheAllownce(salaryDto.getOtheAllownce())
				.setInsuranceCorporation(salaryDto.getInsuranceCorporation())
				.setProfessionalTax(salaryDto.getProfessionalTax())
				.setTds(salaryDto.getTds())
				.setSalaryAdvance(salaryDto.getSalaryAdvance())
				.setPersonalLoan(salaryDto.getPersonalLoan())
				.setOtherDiductions(salaryDto.getOtherDiductions())
				.setHra(salaryDto.getHra())
				.setMonth(salaryDto.getMonth())
				.setYear(salaryDto.getYear())
				.setPf(salaryDto.getPf())
				.setPresentDays(salaryDto.getPresentDays())
				.setWorkingDays(salaryDto.getWorkingDays())
				.setLop(lop)
				.setNetSalary(netSalary)
				.setAbsentDays(salaryDto.getAbsentDays());
			
	}

	@Override
	public EmployeeResponseDto salaryToEmployeeResponseDto(Salary salary,Employee emp) {
		return new EmployeeResponseDto()
				.setEmployeeName(emp.getEmployeeName())
				.setDepartment(emp.getDepartment())
				.setDesignation(emp.getDesignation())
				.setAccountNumber(emp.getAccountNumber())
				.setBankName(emp.getBankName())
				.setUanNo(emp.getUanNo())
				.setEmployeeId(emp.getEmployeeId())
				.setMonth(salary.getMonth())
				.setYear(salary.getYear())
				.setBasicSalary(salary.getBasicSalary())
				.setHra(salary.getHra())
				.setNetSalary(salary.getNetSalary())
				.setPf(salary.getPf())
				.setLop(salary.getLop())
				.setPresentDays(salary.getPresentDays())
				.setAbsentDays(salary.getAbsentDays())
				.setPresentDays(salary.getPresentDays())
				.setWorkingDays(salary.getWorkingDays());
				
						
				
				
		
		
	}


	}


