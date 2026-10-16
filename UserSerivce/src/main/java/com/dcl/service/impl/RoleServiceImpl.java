package com.dcl.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dcl.dto.RoleDto;
import com.dcl.entity.Role;
import com.dcl.enums.RoleType;
import com.dcl.exception.AppException;
import com.dcl.repo.RoleRepo;
import com.dcl.request.AddRoleRequest;
import com.dcl.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepo rrepo;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public RoleDto AddRole(AddRoleRequest request) {
		Role alreadyExists = rrepo.findByRoleName(request.getRoleName()).orElse(null);
		
		if(alreadyExists!=null) {
			throw new AppException("Role name already exists", HttpStatus.CONFLICT);
		}
		
		Role newRole = mapper.map(request, Role.class);
		newRole = rrepo.save(newRole);
		RoleDto dto = mapper.map(newRole, RoleDto.class);
		return dto;
	}

	@Override
	public RoleDto getRoleByName(RoleType roleName) {
		Role role = rrepo.findByRoleName(roleName).orElseThrow(()->new AppException("Role not found!", HttpStatus.NOT_FOUND));
		return mapper.map(role, RoleDto.class);
	}

}
