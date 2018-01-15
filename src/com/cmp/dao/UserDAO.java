package com.cmp.dao;

import java.util.List;

import com.cmp.model.User;

public interface UserDAO {
	void add(User user);
	List<User> listUsers();
	public List<User> findUserByAccount(String account);
}
