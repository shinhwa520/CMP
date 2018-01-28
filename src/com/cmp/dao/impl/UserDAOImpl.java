package com.cmp.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.UserDAO;
import com.cmp.model.User;


@Repository
@Transactional
public class UserDAOImpl extends BaseDaoHibernate implements UserDAO {
	@Override
	public List<User> findUserByChannelId(String channelId, Integer start, Integer length) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from User u where 1=1 ");
		if(StringUtils.isNotBlank(channelId)){
			sb.append(" and u.channel.id = :channelId ");
		}
	    Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
	    Query<?> q = session.createQuery(sb.toString());
	    if(StringUtils.isNotBlank(channelId)){
	    	q.setParameter("channelId", channelId);
	    }
	    	q.setFirstResult(start).setMaxResults(length);
	    return (List<User>) q.list();
//		List<Customer> returnList = (List<Customer>)getHibernateTemplate().find(sb.toString(), new String[] {userId});
//		return returnList;
	}
	
	@Override
	public long countUserByChannelId(String channelId){
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(*) from User u where 1=1 ");
		if(StringUtils.isNotBlank(channelId)){
			  sb.append(" and u.channel.id = ? ");
			  return DataAccessUtils.longResult(getHibernateTemplate().find(sb.toString(), new String[] {channelId}));
		}
		return DataAccessUtils.longResult(getHibernateTemplate().find(sb.toString()));
	}
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

	@Override
	public List<User> findUserByApiModelId(String apiModelId) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select u ")
		  .append(" from User u, WebApiDetail wad ")
		  .append(" where 1 = 1 ")
		  .append(" and u.id = wad.user.id ")
		  .append(" and wad.parameterValues = ? ");
		List<User> returnList = (List<User>)getHibernateTemplate().find(sb.toString(), new String[] {apiModelId});
		return returnList;
	}
}
