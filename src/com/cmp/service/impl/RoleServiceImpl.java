package com.cmp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.RoleDAO;
import com.cmp.model.Role;
import com.cmp.service.RoleService;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDAO roleDAO;
	@Override
	public List<Role> listRole(Integer start, Integer length){
//		SecurityUser securityUser = SecurityUtil.getSecurityUser();
//		System.out.println(securityUser.getUser().getId());
		return roleDAO.listRole(start, length);
	}
	
	@Override
	public long countRole(){
		return roleDAO.countRole();
	}
}
