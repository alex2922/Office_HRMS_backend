package com.SaharaAmussmentPark.Dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class DesignationDto {
	private int did;
	private int deptId;
	private String name;
	
	}

