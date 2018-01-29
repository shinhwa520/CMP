package com.cmp.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.StatusDAO;
import com.cmp.model.Status;


@Repository
@Transactional
public class StatusDAOImpl extends BaseDaoHibernate implements StatusDAO {
	@Override
	public Status findStatusById(int id) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from Status s ")
		  .append(" where 1=1 ")
		  .append(" and s.id = ? ");
		List<Status> returnList = (List<Status>)getHibernateTemplate().find(sb.toString(), id);
		return returnList.isEmpty() ? null : returnList.get(0);
	}
	@Override
	public List<Status> listStatus(Integer start, Integer length){
		StringBuffer sb = new StringBuffer();
		sb.append(" from Status s where 1=1 order by s.type, s.sort ");
	    Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
	    Query<?> q = session.createQuery(sb.toString()).setFirstResult(start).setMaxResults(length);
	    return (List<Status>) q.list();
//		List<Customer> returnList = (List<Customer>)getHibernateTemplate().find(sb.toString(), new String[] {userId});
//		return returnList;
	}
	
	@Override
	public long countStatus(){
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(*) from Status s where 1=1 ");
		return DataAccessUtils.longResult(getHibernateTemplate().find(sb.toString()));
	}
}
