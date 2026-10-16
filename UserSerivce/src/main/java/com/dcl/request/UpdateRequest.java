package com.dcl.request;

import lombok.Data;

@Data
public class UpdateRequest {

	private String username;
	
	private String email;
	
	private String password;
}
