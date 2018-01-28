package com.cmp.service;

import java.util.List;

import com.cmp.model.Customer;

public interface CustService {
	
	List<Customer> findCustByUserId(String userId, Integer start,Integer length);
	long countCustByUserId(String userId);
}
