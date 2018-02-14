package com.cmp.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.CustomerDAO;
import com.cmp.dao.StatusDAO;
import com.cmp.dao.UserDAO;
import com.cmp.model.Customer;
import com.cmp.model.User;
import com.cmp.security.SecurityUtil;
import com.cmp.service.CustService;

@Service("custService")
@Transactional
public class CustServiceImpl implements CustService {
	@Autowired
	private CustomerDAO customerDAO;
	@Autowired
	private UserDAO userDao;
	@Autowired
	private StatusDAO statusDAO;
	
	@Override
	public Customer findCustById(int id){
		return customerDAO.findCustById(id);
	}
	
	@Override
	public List<Customer> findCustByUserId(String userId, Integer start,Integer length){
//		SecurityUser securityUser = SecurityUtil.getSecurityUser();
//		System.out.println(securityUser.getUser().getId());
		return customerDAO.findCustByUserId(userId, start, length);
	}
	
	@Override
	public long countCustByUserId(String userId){
		return customerDAO.countCustByUserId(userId);
	}
	
	@Override
	public void createCust(String name, String gender, String birthday, String phone, String email, String weChat, String city, String address){
		System.out.println(SecurityUtil.getSecurityUser().getUser().getId());
		User user = userDao.findUserById(SecurityUtil.getSecurityUser().getUser().getId());
		Customer cust = new Customer();
		cust.setName(name);
		cust.setGender(gender);
//		cust.setBirthday(birthday);
		cust.setPhone(phone);
		cust.setEmail(email);
		cust.setWeChat(weChat);
		cust.setCity(city);
		cust.setAddress(address);
		cust.setUser(user);
		cust.setStatus(statusDAO.findStatusById(1));
		cust.setCreateTime(new Timestamp(System.currentTimeMillis()));
		cust.setCreateBy(user.getName());
		cust.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		cust.setUpdateBy(user.getName());
		
		customerDAO.saveCust(cust);
	}
	
	
	
	@Override
	public void updateCust(int id, String name, String gender, Date birthday, String phone, String email, String weChat, String city, String address){
		System.out.println(SecurityUtil.getSecurityUser().getUser().getId());
		User user = userDao.findUserById(SecurityUtil.getSecurityUser().getUser().getId());
		Customer cust = customerDAO.findCustById(id);
		cust.setName(name);
		cust.setGender(gender);
		cust.setBirthday(birthday);
		cust.setPhone(phone);
		cust.setEmail(email);
		cust.setWeChat(weChat);
		cust.setCity(city);
		cust.setAddress(address);
		cust.setUser(user);
		cust.setStatus(statusDAO.findStatusById(1));
//		cust.setCreateTime(new Timestamp(System.currentTimeMillis()));
//		cust.setCreateBy(user.getName());
		cust.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		cust.setUpdateBy(user.getName());
		customerDAO.saveCust(cust);
	}
}
