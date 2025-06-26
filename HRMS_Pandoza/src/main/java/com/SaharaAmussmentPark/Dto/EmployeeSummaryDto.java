package com.SaharaAmussmentPark.Dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSummaryDto {
	 private int count;
	    private List<String> employeeName;
}
