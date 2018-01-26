package com.cmp.dao;

import java.util.List;

import com.cmp.model.Customer;


public interface CustomerDAO {
	List<Customer> findCustByChannelId(String channelId, Integer start,Integer length);
	long countCustByChannelId(String channelId);
	
	public List<Customer> findCustByUserThroughApiModelId(String apiModelId);
	
	public void insertCustByModel(Customer customer);
}
