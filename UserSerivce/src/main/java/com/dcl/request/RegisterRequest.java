package com.dcl.request;

import java.time.LocalDate;

import com.dcl.enums.RoleType;

import lombok.Data;

@Data
public class RegisterRequest {
	
	private String email;

	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private String phone;
	
	private LocalDate dob;
	
	private RoleType roleName;
	
}
