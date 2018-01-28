package com.cmp.service;

import java.util.List;

import com.cmp.model.Role;

public interface RoleService {
	List<Role> listRole(Integer start, Integer length);
	long countRole();
}