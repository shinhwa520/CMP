package com.cmp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.CustomerDAO;
import com.cmp.model.Customer;
import com.cmp.service.CustService;

@Service("custService")
@Transactional
public class CustServiceImpl implements CustService {
	@Autowired
	private CustomerDAO customerDAO;
	
	@Transactional(readOnly = true)
	@Override
	public List<Customer> findCustByChannelId(String channelId, Integer start,Integer length){
		return customerDAO.findCustByChannelId(channelId, start, length);
	}
	
	@Transactional(readOnly = true)
	@Override
	public int countCustByChannelId(String channelId){
		return customerDAO.countCustByChannelId(channelId);
	}
}
