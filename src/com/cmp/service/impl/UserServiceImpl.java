package com.cmp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.UserDAO;
import com.cmp.dao.UserKpiDAO;
import com.cmp.model.User;
import com.cmp.model.UserKpi;
import com.cmp.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private UserKpiDAO userKpiDAO;
	
	
	@Override
	public User findUserAndKpiById(String id, String yearMonth){
//		SecurityUser securityUser = SecurityUtil.getSecurityUser();
//		System.out.println(securityUser.getUser().getId());
		Object[] objArray = userDAO.findUserAndKpiById(id, yearMonth);
		User user = (User) objArray[0];
		user.set_agent_user(((Long) objArray[1]).intValue());
		user.set_agent_cust(((Long) objArray[2]).intValue());
		user.set_volume(((Long) objArray[3]).intValue());
		UserKpi kpi = userKpiDAO.findTokenByUserAndYearMonth(id, yearMonth);//查詢KPI設定
		if( null!=kpi){
			user.setAgent_user(kpi.getAgent_user());
			user.setAgent_cust(kpi.getAgent_cust());
			user.setVolume(kpi.getVolume());
		}
		
		return user;
	}
	
	@Override
	public User findUserById(String id){
//		SecurityUser securityUser = SecurityUtil.getSecurityUser();
//		System.out.println(securityUser.getUser().getId());
		return userDAO.findUserById(id);
	}
	
	@Override
	public User saveUserInfo(User user){
		return userDAO.saveUser(user);
	}
	
	@Override
	public List<User> findUserByChannelId(String channelId, String yearMonth, Integer start, Integer length){
		List<User> resultList = new ArrayList<User>();
		List<Object[]> objArrayList = userDAO.findUserByChannelId(channelId, yearMonth, start, length);
		for(Object[] objArray : objArrayList){
			User user = (User) objArray[0];
			user.set_agent_user(((Long) objArray[1]).intValue());
			user.set_agent_cust(((Long) objArray[2]).intValue());
			user.set_volume(((Long) objArray[3]).intValue());
			UserKpi kpi = userKpiDAO.findTokenByUserAndYearMonth(user.getId(), yearMonth);//查詢KPI設定
			if( null!=kpi){
				user.setAgent_user(kpi.getAgent_user());
				user.setAgent_cust(kpi.getAgent_cust());
				user.setVolume(kpi.getVolume());
			}
			resultList.add(user);
		}

		return resultList;
	}
	
	@Override
	public long countUserByChannelId(String channelId){
		return userDAO.countUserByChannelId(channelId);
	}
}
