package com.cmp.dao;

import java.util.List;

import com.cmp.model.User;

public interface UserDAO {
	List<User> findUserByChannelId(String channelId, Integer start, Integer length);
	long countUserByChannelId(String channelId);
	User saveUser(User user);
	List<User> listUsers();
	public List<User> findUserByAccount(String account);
	User findUserById(String id);
	
	public List<User> findUserByApiModelId(String apiModelId);
}
