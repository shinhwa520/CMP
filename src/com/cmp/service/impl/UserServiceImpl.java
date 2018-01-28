package com.cmp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.UserDAO;
import com.cmp.model.User;
import com.cmp.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public List<User> findUserByChannelId(String channelId, Integer start, Integer length){
//		SecurityUser securityUser = SecurityUtil.getSecurityUser();
//		System.out.println(securityUser.getUser().getId());
		return userDAO.findUserByChannelId(channelId, start, length);
	}
	
	@Override
	public long countUserByChannelId(String channelId){
		return userDAO.countUserByChannelId(channelId);
	}
}
