package com.dcl.service;

import com.dcl.dto.RoleDto;
import com.dcl.enums.RoleType;
import com.dcl.request.AddRoleRequest;

public interface RoleService {

	public RoleDto AddRole(AddRoleRequest request);
	
	public RoleDto getRoleByName(RoleType roleName);
	
}
