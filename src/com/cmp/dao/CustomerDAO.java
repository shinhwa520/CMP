package com.cmp.dao;

import java.util.List;

import com.cmp.model.Customer;


public interface CustomerDAO {
	Customer findCustById(int id);
	List<Customer> findCustByUserId(String userId, Integer start,Integer length);
	List<Customer> findCust4SysMsg(String userId);
	long countCustByUserId(String userId);
	List<Customer> findCust4Search(String keyword, Integer start,Integer length);
	long countCust4Search(String keyword);
	List<Customer> findCust4MA(String roleName, Integer start,Integer length);
	long countCust4MA(String roleName);
	
	public List<Customer> findCustByUserThroughApiModelId(String apiModelId);
	
	public void insertCustByModel(Customer customer);
	public Customer saveCust(Customer cust);
}
