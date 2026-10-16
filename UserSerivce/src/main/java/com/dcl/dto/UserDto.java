package com.dcl.dto;

import lombok.Data;

@Data
public class UserDto {

	private String userId;
	
	private String email;
	
	private String createdAt;
	
	private ProfileDto pdto;
	
	private RoleDto rdto;
	
}
