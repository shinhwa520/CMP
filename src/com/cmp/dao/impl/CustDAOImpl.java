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
		sb.append(" and c.dataStatus.id <> 21 ");
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> findCust4SysMsg(String userId) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from Customer c where 1=1 ");
		sb.append(" and c.dataStatus.id <> 21 ");
		sb.append(" and c.sysMsg=1 ");
		if(StringUtils.isNotBlank(userId)){
			sb.append(" and c.user.id = :userId ");
		}
	    Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
	    Query<?> q = session.createQuery(sb.toString());
	    if(StringUtils.isNotBlank(userId)){
	    	q.setParameter("userId", userId);
	    }
	    return (List<Customer>) q.list();
	}
	
	@Override
	public long countCustByUserId(String userId){
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(*) from Customer c where 1=1 ");
		if(StringUtils.isNotBlank(userId)){
			  sb.append(" and c.user.id = ? ");
			  return DataAccessUtils.longResult(getHibernateTemplate().find(sb.toString(), new String[] {userId}));
		}
		sb.append(" and c.dataStatus.id <> 21 ");
		return DataAccessUtils.longResult(getHibernateTemplate().find(sb.toString()));
	}
	
	@Override
	public List<Customer> findCust4Search(String keyword, Integer start,Integer length) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from Customer c where 1=1 ");
		sb.append(" and c.dataStatus.id <> 21 ");
		if(StringUtils.isNotBlank(keyword)){
			sb.append(" and c.name like :keyword ");
		}
	    Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
	    Query<?> q = session.createQuery(sb.toString());
	    if(StringUtils.isNotBlank(keyword)){
	    	q.setParameter("keyword", "%"+keyword+"%");
	    }
	    	q.setFirstResult(start).setMaxResults(length);
	    return (List<Customer>) q.list();
//		List<Customer> returnList = (List<Customer>)getHibernateTemplate().find(sb.toString(), new String[] {userId});
//		return returnList;
	}
	
	@Override
	public long countCust4Search(String keyword){
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(*) from Customer c where 1=1 ");
		sb.append(" and c.dataStatus.id <> 21 ");
		if(StringUtils.isNotBlank(keyword)){
			  sb.append(" and c.name = ? ");
			  return DataAccessUtils.longResult(getHibernateTemplate().find(sb.toString(), new String[] {"%"+keyword+"%"}));
		}
		return DataAccessUtils.longResult(getHibernateTemplate().find(sb.toString()));
	}
	
	public List<Customer> findCust4MA(String roleName, Integer start,Integer length){
		StringBuffer sb = new StringBuffer();
		sb.append(" from Customer c where 1=1 ");
		sb.append(" and c.dataStatus.id <> 21 ");
		if(StringUtils.isNotBlank(roleName)){
			sb.append(" and c.user.role.name = :roleName ");
		}
	    Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
	    Query<?> q = session.createQuery(sb.toString());
	    if(StringUtils.isNotBlank(roleName)){
	    	q.setParameter("roleName", roleName);
	    }
	    	q.setFirstResult(start).setMaxResults(length);
	    return (List<Customer>) q.list();
	}
	
	public long countCust4MA(String roleName){
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(*) from Customer c where 1=1 ");
		sb.append(" and c.dataStatus.id <> 21 ");
		if(StringUtils.isNotBlank(roleName)){
			  sb.append(" and c.user.role.name = ? ");
			  return DataAccessUtils.longResult(getHibernateTemplate().find(sb.toString(), new String[] {roleName}));
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
		  .append(" and wad.parameterValues = ? ")
		  .append(" and c.dataStatus.id <> 21 ");
		
		List<Customer> returnList = (List<Customer>)getHibernateTemplate().find(sb.toString(), new String[] {apiModelId});
		return returnList;
	}

	@Override
	public void insertCustByModel(Customer customer) {
		getHibernateTemplate().save(customer);
	}
	
	@Override
	public Customer saveCust(Customer cust) {
		return (Customer) getHibernateTemplate().merge(cust);
//	   sessionFactory.getCurrentSession().save(user);
	}
}
