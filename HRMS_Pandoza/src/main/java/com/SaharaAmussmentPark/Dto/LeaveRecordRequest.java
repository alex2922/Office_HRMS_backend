package com.SaharaAmussmentPark.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class LeaveRecordRequest {
	 private String employeeId;
	    private String paidLeaves;

}
