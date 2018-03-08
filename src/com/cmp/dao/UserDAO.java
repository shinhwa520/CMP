package com.cmp.dao;

import java.util.List;

import com.cmp.model.Commission;
import com.cmp.model.User;

public interface UserDAO {
	List<Object[]> findUserByChannelId(String channelId, String yearMonth, Integer start, Integer length);
	long countUserByChannelId(String channelId);
	List<Object[]> findUser4MA(String roleName, String yearMonth, Integer start,Integer length);
	long countUser4MA(String roleName);
	User saveUser(User user);
	List<User> listUsers();
	public List<User> findUserByAccount(String account);
	public List<User> findUserByRoleName(String roleName);
	User findUserById(String id);
	Object[] findUserAndKpiById(String id, String yearMonth);
	User findUserByEmail(String mailAddress);
	public List<User> findUserByApiModelId(String apiModelId);
	public List<User> findUserByAccountAndStatus(String account, int statusSort);
	public List<Commission> findCommissionByUserId(String userId);
}
