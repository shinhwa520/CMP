package com.cmp.service;

import java.util.List;

import com.cmp.model.Role;

public interface RoleService {
	
	public static final String ROLE_NAME_SU = "SU";
	public static final String ROLE_NAME_ADMIN = "ADMIN";
	public static final String ROLE_NAME_ASST = "ASST";
	public static final String ROLE_NAME_USER = "USER";
	public static final String ROLE_NAME_MA = "MA";
	
	List<Role> listRole(Integer start, Integer length);
	long countRole();
}