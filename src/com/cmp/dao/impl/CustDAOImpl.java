package com.cmp.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.CustomerDAO;
import com.cmp.model.Customer;
import com.cmp.model.User;


@Repository
@Transactional
public class CustDAOImpl extends BaseDaoHibernate implements CustomerDAO {

	@Override
	public Customer findCustById(int id) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from Customer c ")
		  .append(" where 1=1 ")
		  .append(" and c.id = ? ");
		List<Customer> returnList = (List<Customer>)getHibernateTemplate().find(sb.toString(), id);
		return returnList.isEmpty() ? null : returnList.get(0);
	}
	
	
	
	
	
	@Override
	public List<Customer> findCustByUserId(String userId, Integer start,Integer length) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from Customer c where 1=1 ");
		if(StringUtils.isNotBlank(userId)){
			sb.append(" and c.user.id = :userId ");
		}
	    Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
	    Query<?> q = session.createQuery(sb.toString());
	    if(StringUtils.isNotBlank(userId)){
	    	q.setParameter("userId", userId);
	    }
	    	q.setFirstResult(start).setMaxResults(length);
	    return (List<Customer>) q.list();
//		List<Customer> returnList = (List<Customer>)getHibernateTemplate().find(sb.toString(), new String[] {userId});
//		return returnList;
	}
	
	@Override
	public long countCustByUserId(String userId){
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(*) from Customer c where 1=1 ");
		if(StringUtils.isNotBlank(userId)){
			  sb.append(" and c.user.id = ? ");
			  return DataAccessUtils.longResult(getHibernateTemplate().find(sb.toString(), new String[] {userId}));
		}
		return DataAccessUtils.longResult(getHibernateTemplate().find(sb.toString()));
	}
	
	@Override
	public List<Customer> findCustByUserThroughApiModelId(String apiModelId) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select c ")
		  .append(" from Customer c, WebApiDetail wad ")
		  .append(" where 1=1 ")
		  .append(" and c.user.id = wad.user.id  ")
		  .append(" and wad.parameterValues = ? ");
		
		List<Customer> returnList = (List<Customer>)getHibernateTemplate().find(sb.toString(), new String[] {apiModelId});
		return returnList;
	}

	@Override
	public void insertCustByModel(Customer customer) {
		getHibernateTemplate().save(customer);
	}
}
