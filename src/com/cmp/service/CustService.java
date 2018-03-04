package com.cmp.service;

import java.util.Date;
import java.util.List;

import com.cmp.model.Customer;

public interface CustService {
	Customer findCustById(int id);
	List<Customer> findCustByUserId(String userId, Integer start,Integer length);
	long countCustByUserId(String userId);
	List<Customer> findCust4Search(String keyword, Integer start,Integer length);
	long countCust4Search(String keyword);
	
	void createCust(String name, String gender, String birthday, String phone, String email, String weChat, String city, String address);
	void updateCust(int id, String name, String gender, Date birthday, String phone, String email, String weChat
			, Integer identity1_id, String identity1_code, String identity1_name, Integer identity2_id, String identity2_code, String identity2_name
			, String city, String census, String address, String remark, String status);
}
