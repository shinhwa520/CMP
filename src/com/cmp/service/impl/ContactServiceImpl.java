package com.cmp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.ContactDAO;
import com.cmp.model.Contact;
import com.cmp.model.User;
import com.cmp.security.SecurityUtil;
import com.cmp.service.ContactService;

@Service("contactService")
@Transactional
public class ContactServiceImpl implements ContactService {
	@Autowired
	private ContactDAO contactDAO;
	
	@Override
	public void contactUs(String subject, String content) {
		User editor = SecurityUtil.getSecurityUser().getUser();
		contactDAO.saveContact(new Contact(subject, content, editor));
	}
}
