package com.cmp.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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
		return customerDAO.findCustByUserId(userId, start, length);
	}
	
	@Override
	public long countCustByUserId(String userId){
		return customerDAO.countCustByUserId(userId);
	}
	
	@Override
	public List<Customer> findCust4MA(String roleName, Integer start,Integer length){
//		SecurityUser securityUser = SecurityUtil.getSecurityUser();
		return customerDAO.findCust4MA(roleName, start, length);
	}
	
	@Override
	public long countCust4MA(String roleName){
		return customerDAO.countCust4MA(roleName);
	}
	
	@Override
	public List<Customer> findCust4Search(String keyword, Integer start,Integer length){
//		SecurityUser securityUser = SecurityUtil.getSecurityUser();
		return customerDAO.findCust4Search(keyword, start, length);
	}
	
	@Override
	public long countCust4Search(String keyword){
		return customerDAO.countCust4Search(keyword);
	}
	
	@Override
	public void createCust(String name, String gender, Date birthday, String phone, String email, String weChat
			, Integer identity1_id, String identity1_code, String identity1_name, Integer identity2_id, String identity2_code, String identity2_name
			, String city, String census, String address, String remark){
		User user = userDao.findUserById(SecurityUtil.getSecurityUser().getUser().getId());
		Customer cust = new Customer();
		cust.setName(name);
		cust.setGender(gender);
		cust.setBirthday(birthday);
		cust.setPhone(phone);
		cust.setEmail(email);
		cust.setWeChat(weChat);
		
		cust.setIdentity1_id(null==identity1_id ? 0 : identity1_id);
		cust.setIdentity1_code(identity1_code);
		cust.setIdentity1_name(identity1_name);
		cust.setIdentity2_id(null==identity2_id ? 0 : identity2_id);
		cust.setIdentity2_code(identity2_code);
		cust.setIdentity2_name(identity2_name);
		
		cust.setCity(city);
		cust.setCensus(census);
		cust.setAddress(address);
		cust.setRemark(remark);
		cust.setUser(user);
		
		cust.setStatus(statusDAO.findStatus("CUST", 1));
		cust.setCreateTime(new Timestamp(System.currentTimeMillis()));
		cust.setCreateBy(user.getName());
		cust.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		cust.setUpdateBy(user.getName());
		
		customerDAO.saveCust(cust);
	}
	
	
	
	@Override
	public void updateCust(int id, String name, String gender, Date birthday, String phone, String email, String weChat
			, Integer identity1_id, String identity1_code, String identity1_name, Integer identity2_id, String identity2_code, String identity2_name
			, String city, String census, String address, String remark, String status){
		User user = userDao.findUserById(SecurityUtil.getSecurityUser().getUser().getId());
		Customer cust = customerDAO.findCustById(id);
		cust.setName(name);
		cust.setGender(gender);
		cust.setBirthday(birthday);
		cust.setPhone(phone);
		cust.setEmail(email);
		cust.setWeChat(weChat);
		
		cust.setIdentity1_id(null==identity1_id ? 0 : identity1_id);
		cust.setIdentity1_code(identity1_code);
		cust.setIdentity1_name(identity1_name);
		cust.setIdentity2_id(null==identity2_id ? 0 : identity2_id);
		cust.setIdentity2_code(identity2_code);
		cust.setIdentity2_name(identity2_name);
		
		cust.setCity(city);
		cust.setCensus(census);
		cust.setAddress(address);
		cust.setRemark(remark);
		cust.setUser(user);
		if(null!=status){
			cust.setStatus(statusDAO.findStatus("CUST", Integer.valueOf(status)));
		}
//		cust.setCreateTime(new Timestamp(System.currentTimeMillis()));
//		cust.setCreateBy(user.getName());
		cust.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		cust.setUpdateBy(user.getName());
		customerDAO.saveCust(cust);
	}
}
