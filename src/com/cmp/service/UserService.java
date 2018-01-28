package com.cmp.service;

import java.util.List;

import com.cmp.model.Role;
import com.cmp.model.User;

public interface UserService {
	List<User> findUserByChannelId(String channelId, Integer start, Integer length);
	long countUserByChannelId(String channelId);
}
