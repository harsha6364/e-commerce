package com.dcl.service;

import java.util.List;

import com.dcl.dto.UserDto;
import com.dcl.entity.User;
import com.dcl.request.RegisterRequest;
import com.dcl.request.LoginRequest;
import com.dcl.request.UpdateRequest;

public interface UserService {

	public UserDto register(RegisterRequest request);
	
	public UserDto login(LoginRequest request);
	
	public UserDto getUserById(Integer userId);
	
	public List<User> getAllUsers();
	
	public UserDto update(UpdateRequest request);
	
	public void deleteUserById(Integer userId);
}
