package com.SaharaAmussmentPark.Dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class OfficialLetterDto {
	private int oId;
	private String oname;
	private String template;

}
