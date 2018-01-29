package com.cmp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.UserDAO;
import com.cmp.model.User;
import com.cmp.service.LoginService;

@Service("loginService")
@Transactional
public class LoginServiceImpl implements LoginService {
	@Autowired
	private UserDAO userDao;
	
	@Override
	public List<User> findUserByAccount(String account) {
	   return userDao.findUserByAccount(account);
	}
}
