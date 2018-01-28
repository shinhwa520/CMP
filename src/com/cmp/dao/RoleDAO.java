package com.cmp.dao;

import java.util.List;

import com.cmp.model.Role;

public interface RoleDAO {
	List<Role> listRole(Integer start, Integer length);
	long countRole();
}
