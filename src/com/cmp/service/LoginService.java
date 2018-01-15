package com.cmp.service;

import java.util.List;

import com.cmp.model.User;

public interface LoginService {
	List<User> findUserByAccount(String account);
}
