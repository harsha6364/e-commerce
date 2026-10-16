package com.dcl.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UpdateProfileRequest {

	private String firstName;
	
	private String lastName;
	
	private String phone;
	
	private LocalDate dob;
	
}
