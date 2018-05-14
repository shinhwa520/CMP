package com.cmp.service;

import java.util.Date;
import java.util.List;

import com.cmp.model.User;

public interface UserService {
	User findUserAndKpiById(String id, String yearMonth);
	User findUserById(String id);
	User saveUserInfo(User user);
	List<User> findUserByChannelId(String channelId, String yearMonth, Integer start, Integer length);
	long countUserByChannelId(String channelId);
	List<User> findUser4MA(String roleName, String yearMonth, Integer start,Integer length);
	long countUser4MA(String roleName);
	
	public void updateKpi(String userId, String yearMonth, int agent_user, int agent_cust, int volume, Date current, String remark);
	public void update(String userId, String userName, String password, String phone, String email, String weChat, String status, String remark);
	
	public User findUserByApiId(String apiId);
	public User closeGuide();
}
