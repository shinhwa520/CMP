package com.cmp.service;

import java.util.List;

import com.cmp.model.User;

public interface UserService {
	User findUserById(String id);
	List<User> findUserByChannelId(String channelId, Integer start, Integer length);
	long countUserByChannelId(String channelId);
}
