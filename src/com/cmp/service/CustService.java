package com.cmp.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.cmp.model.Customer;

public interface CustService {
	Customer findCustById(int id);
	List<Customer> findCustByUserId(String userId, Integer start,Integer length);
	long countCustByUserId(String userId);
	void createCust(String name, String gender, String birthday, String phone, String email, String weChat, String city, String address);
}
