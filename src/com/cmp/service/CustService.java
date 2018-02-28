package com.cmp.service;

import java.util.Date;
import java.util.List;

import com.cmp.model.Customer;
import com.cmp.service.vo.CustServiceVO;

public interface CustService {
	Customer findCustById(int id);
	List<Customer> findCustByUserId(String userId, Integer start,Integer length);
	long countCustByUserId(String userId);
	void createCust(String name, String gender, String birthday, String phone, String email, String weChat, String city, String address);
	void updateCust(int id, String name, String gender, Date birthday, String phone, String email, String weChat, String city, String address, String status);
}
