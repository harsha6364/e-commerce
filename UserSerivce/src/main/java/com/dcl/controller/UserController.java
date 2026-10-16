package com.dcl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dcl.dto.RoleDto;
import com.dcl.dto.UserDto;
import com.dcl.request.AddRoleRequest;
import com.dcl.request.LoginRequest;
import com.dcl.request.RegisterRequest;
import com.dcl.response.ApiResponse;
import com.dcl.service.RoleService;
import com.dcl.service.UserService;

@RestController("/user")
public class UserController {

	@Autowired
	private UserService uservice;
	
	@Autowired
	private RoleService rservice;
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequest request){
		UserDto dto = uservice.register(request);
		return new ResponseEntity<>(new ApiResponse<>("User registered successfully!",dto,HttpStatus.OK),HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request){
		UserDto udto = uservice.login(request);
		return new ResponseEntity<>(new ApiResponse<>("Login successfull!", udto, HttpStatus.OK), HttpStatus.OK);
	}
	
	@PostMapping("/addrole")
	public ResponseEntity<?> addRole(@RequestBody AddRoleRequest request){
		RoleDto rdto = rservice.AddRole(request);
		return new ResponseEntity<>(new ApiResponse<>("Role added successfully!", rdto, HttpStatus.OK),HttpStatus.OK);
	}
}
