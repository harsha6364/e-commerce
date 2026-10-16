package com.dcl.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ProfileDto {
	
	private Integer profileId;

	private String firstName;
	
	private String lastName;
	
	private String phone;
	
	private LocalDate dob;
}
