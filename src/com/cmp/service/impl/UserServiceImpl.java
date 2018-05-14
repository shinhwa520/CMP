package com.cmp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.StatusDAO;
import com.cmp.dao.UserDAO;
import com.cmp.dao.UserKpiDAO;
import com.cmp.model.User;
import com.cmp.model.UserKpi;
import com.cmp.security.SecurityUtil;
import com.cmp.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private UserKpiDAO userKpiDAO;
	@Autowired
	private StatusDAO statusDAO;	
	
	@Override
	public User findUserAndKpiById(String id, String yearMonth){
//		SecurityUser securityUser = SecurityUtil.getSecurityUser();
		Object[] objArray = userDAO.findUserAndKpiById(id, yearMonth);
		User user = (User) objArray[0];
		user.set_agent_user(((Long) objArray[1]).intValue());
		user.set_agent_cust(((Long) objArray[2]).intValue());
		user.set_volume(((Long) objArray[3]).intValue());
		UserKpi kpi = userKpiDAO.findKpiByUserAndYearMonth(id, yearMonth);//查詢KPI設定
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
			UserKpi kpi = userKpiDAO.findKpiByUserAndYearMonth(user.getId(), yearMonth);//查詢KPI設定
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
	
	@Override
	public List<User> findUser4MA(String roleName, String yearMonth, Integer start, Integer length){
		List<User> resultList = new ArrayList<User>();
		List<Object[]> objArrayList = userDAO.findUser4MA(roleName, yearMonth, start, length);
		for(Object[] objArray : objArrayList){
			User user = (User) objArray[0];
			user.set_agent_user(((Long) objArray[1]).intValue());
			user.set_agent_cust(((Long) objArray[2]).intValue());
			user.set_volume(((Long) objArray[3]).intValue());
			UserKpi kpi = userKpiDAO.findKpiByUserAndYearMonth(user.getId(), yearMonth);//查詢KPI設定
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
	public long countUser4MA(String roleName){
		return userDAO.countUser4MA(roleName);
	}
	
	@Override
	public void updateKpi(String userId, String yearMonth, int agent_user, int agent_cust, int volume, Date current, String remark){
		String editorId = SecurityUtil.getSecurityUser().getUser().getId();
		User user = findUserById(userId);
		user.setRemark(remark);
		user.setUpdateBy(editorId);
		user.setUpdateDateTime(new Date());
		userDAO.saveUser(user);
		UserKpi userKpi = userKpiDAO.findKpiByUserAndYearMonth(userId, yearMonth);//查詢KPI設定
		if(null!=userKpi){
			userKpi.setAgent_user(agent_user);
			userKpi.setAgent_cust(agent_cust);
			userKpi.setVolume(volume);
			userKpi.setUpdateBy(editorId);
			userKpi.setUpdateDateTime(current);
		}else{
			userKpi= new UserKpi(
				String.valueOf(current.getTime())
				,user
				,yearMonth
				,agent_user
				,agent_cust
				,volume
				,editorId
				,current
				,editorId
				,current
				);
		}
		userKpiDAO.saveUserKpi(userKpi);
	}
	
	@Override
	public void update(String userId, String userName, String password, String phone, String email, String weChat, String status, String remark){
		String editorId = SecurityUtil.getSecurityUser().getUser().getId();
		User user = findUserById(userId);
		user.setName(userName);
		user.setPassword(password);
		user.setPhone(phone);
		user.setEmail(email);
		user.setWeChat(weChat);
		user.setStatus(statusDAO.findStatus("USER", Integer.valueOf(status)));
		user.setRemark(remark);
		user.setUpdateBy(editorId);
		user.setUpdateDateTime(new Date());
		userDAO.saveUser(user);
	}

	@Override
	public User findUserByApiId(String apiId) {
		User user = null;
		try {
			List<User> userList = userDAO.findUserByApiModelId(apiId);
			
			if (userList != null && !userList.isEmpty()) {
				user = userList.get(0);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}

	
	public User closeGuide() {
		User user = SecurityUtil.getSecurityUser().getUser();
		try {
			user.setShowGuide("N");
			user = userDAO.saveUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}
