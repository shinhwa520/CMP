package com.cmp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.CustomerDAO;
import com.cmp.model.Customer;
import com.cmp.security.SecurityUser;
import com.cmp.security.SecurityUtil;
import com.cmp.service.CustService;

@Service("custService")
@Transactional
public class CustServiceImpl implements CustService {
	@Autowired
	private CustomerDAO customerDAO;
	
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
}
