package com.SaharaAmussmentPark.Dto;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)

public class DepartmentDto {
	private int dId;
	private String dname;
	
	private List<DesignationDto> designation;

}
