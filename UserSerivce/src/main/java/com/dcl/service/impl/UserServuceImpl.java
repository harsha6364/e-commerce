package com.dcl.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dcl.dto.ProfileDto;
import com.dcl.dto.RoleDto;
import com.dcl.dto.UserDto;
import com.dcl.entity.Profile;
import com.dcl.entity.Role;
import com.dcl.entity.User;
import com.dcl.exception.AppException;
import com.dcl.repo.UserRepo;
import com.dcl.request.LoginRequest;
import com.dcl.request.RegisterRequest;
import com.dcl.request.UpdateRequest;
import com.dcl.response.ApiResponse;
import com.dcl.service.UserService;

@Service
public class UserServuceImpl implements UserService {
	
	@Autowired
	private UserRepo urepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private RoleServiceImpl rservice;
	
	@Autowired
	private ProfileServiceImpl pservice;

	@Override
	public UserDto register(RegisterRequest request) {
User alreadyExists = urepo.findByEmail(request.getEmail()).orElse(null);
		
		if(alreadyExists!= null) {
			throw new AppException("User already exists!", HttpStatus.CONFLICT);
		}
		User newUser = mapper.map(request, User.class);
		
		//Fetching the role from the DB
		RoleDto rdto = rservice.getRoleByName(request.getRoleName());
		Role role = mapper.map(rdto, Role.class);
		newUser.setRole(role);
		newUser = urepo.save(newUser);
		
		//Adding profile
		Profile profile = mapper.map(request, Profile.class);
		profile.setUser(newUser);
		ProfileDto pdto = pservice.addProfile(profile);
		
		UserDto udto = mapper.map(newUser, UserDto.class);
		udto.setPdto(pdto);
		udto.setRdto(rdto);
		return udto;
	}

	@Override
	public UserDto login(LoginRequest request) {
		User user = urepo.findByEmail(request.getEmail()).orElseThrow(()-> new AppException("User not found with this mail!", HttpStatus.NOT_FOUND));
		
		if(!user.getPassword().equals(request.getPassword())){
			throw new AppException("Password is incorrect!", HttpStatus.CONFLICT);
		}
		UserDto udto = mapper.map(user, UserDto.class);
		return udto;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto update(UpdateRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUserById(Integer userId) {
		// TODO Auto-generated method stub

	}

}
