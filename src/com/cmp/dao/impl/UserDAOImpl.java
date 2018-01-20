package com.cmp.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.UserDAO;
import com.cmp.model.User;


@Repository
@Transactional
public class UserDAOImpl extends BaseDaoHibernate implements UserDAO {

	@Override
	public User saveUser(User user) {
		return (User) getHibernateTemplate().merge(user);
//	   sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public List<User> listUsers() {
		return getHibernateTemplate().loadAll(User.class);
	}
	
	@Override
	public List<User> findUserByAccount(String account) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from User u ")
		  .append(" where 1=1 ")
		  .append(" and u.account = ? ");
		List<User> returnList = (List<User>)getHibernateTemplate().find(sb.toString(), new String[] {account});
		return returnList;
	}
	
	@Override
	public User findUserById(String id) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from User u ")
		  .append(" where 1=1 ")
		  .append(" and u.id = ? ");
		List<User> returnList = (List<User>)getHibernateTemplate().find(sb.toString(), new String[] {id});
		return returnList.isEmpty() ? null : returnList.get(0);
	}
}
