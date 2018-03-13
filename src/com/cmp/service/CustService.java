package com.cmp.service;

import java.util.Date;
import java.util.List;

import com.cmp.model.Customer;
import com.cmp.service.vo.CustServiceVO;

public interface CustService {
	Customer findCustById(int id);
	List<Customer> findCustByUserId(String userId, Integer start,Integer length);
	long countCustByUserId(String userId);
	List<Customer> findCust4Search(String keyword, Integer start,Integer length);
	long countCust4Search(String keyword);
	List<Customer> findCust4MA(String roleName, Integer start,Integer length);
	long countCust4MA(String roleName);
	
	
	void createCust(String name, String gender, Date birthday, String phone, String email, String weChat
			, Integer identity1_id, String identity1_code, String identity1_name, Integer identity2_id, String identity2_code, String identity2_name
			, String city, String census, String address, String remark);
	void updateCust(int id, String name, String gender, Date birthday, String phone, String email, String weChat
			, Integer identity1_id, String identity1_code, String identity1_name, Integer identity2_id, String identity2_code, String identity2_name
			, String city, String census, String address, String remark, String status);
	void deleteCust(CustServiceVO custServiceVO);
}
