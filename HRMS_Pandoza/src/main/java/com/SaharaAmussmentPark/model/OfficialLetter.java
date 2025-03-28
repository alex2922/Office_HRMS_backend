package com.SaharaAmussmentPark.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString
public class OfficialLetter {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private int oId;
	private String oname;
	private String template;

}
