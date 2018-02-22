package com.cmp.service;

import java.util.List;

import com.cmp.model.User;

public interface UserService {
	User findUserAndKpiById(String id, String yearMonth);
	User findUserById(String id);
	User saveUserInfo(User user);
	List<User> findUserByChannelId(String channelId, String yearMonth, Integer start, Integer length);
	long countUserByChannelId(String channelId);
}
