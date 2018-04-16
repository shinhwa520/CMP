package com.cmp.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.ContactDAO;
import com.cmp.model.Contact;

@Repository
@Transactional
public class ContactDAOImpl extends BaseDaoHibernate implements ContactDAO {
	
	@Override
	public Contact saveContact(Contact contact) {
		return (Contact) getHibernateTemplate().merge(contact);
	}
	
}
