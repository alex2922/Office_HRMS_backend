package com.SaharaAmussmentPark.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString

public class Designation {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int did;
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "deptId", nullable = false)
	private Department department;

}
