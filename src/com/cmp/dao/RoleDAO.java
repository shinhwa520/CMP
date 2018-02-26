package com.cmp.dao;

import java.util.List;

import com.cmp.model.Role;

public interface RoleDAO {
	Role findRoleById(int id);
	Role findRoleByName(String name);//role_name_UNIQUE
	List<Role> listRole(Integer start, Integer length);
	long countRole();
}
