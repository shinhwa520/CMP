package com.cmp.dao;

import java.util.List;

import com.cmp.model.Customer;


public interface CustomerDAO {
	Customer findCustById(int id);
	List<Customer> findCustByUserId(String userId, Integer start,Integer length);
	long countCustByUserId(String userId);
	List<Customer> findCust4Search(String keyword, Integer start,Integer length);
	long countCust4Search(String keyword);
	
	public List<Customer> findCustByUserThroughApiModelId(String apiModelId);
	
	public void insertCustByModel(Customer customer);
	public Customer saveCust(Customer cust);
}
