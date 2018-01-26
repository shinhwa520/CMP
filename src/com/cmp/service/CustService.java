package com.cmp.service;

import java.util.List;

import com.cmp.model.Customer;

public interface CustService {
	
	List<Customer> findCustByChannelId(String channelId, Integer start,Integer length);
	long countCustByChannelId(String channelId);
}
