package com.cmp.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.RoleDAO;
import com.cmp.model.Role;


@Repository
@Transactional
public class RoleDAOImpl extends BaseDaoHibernate implements RoleDAO {

	@Override
	public List<Role> listRole(Integer start, Integer length){
		StringBuffer sb = new StringBuffer();
		sb.append(" from Role r where 1=1 ");
	    Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
	    Query<?> q = session.createQuery(sb.toString()).setFirstResult(start).setMaxResults(length);
	    return (List<Role>) q.list();
//		List<Customer> returnList = (List<Customer>)getHibernateTemplate().find(sb.toString(), new String[] {userId});
//		return returnList;
	}
	
	@Override
	public long countRole(){
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(*) from Role r where 1=1 ");
		return DataAccessUtils.longResult(getHibernateTemplate().find(sb.toString()));
	}
}
