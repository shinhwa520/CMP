package com.cmp.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.CommissionDAO;
import com.cmp.model.Commission;

@Repository
@Transactional
public class CommissionDAOImpl extends BaseDaoHibernate implements CommissionDAO {
	
	@Override
	public Commission saveCommission(Commission commission) {
		return (Commission) getHibernateTemplate().merge(commission);
//	   sessionFactory.getCurrentSession().save(user);
	}
	
	@Override
	public Commission findCommissionById(String id) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from Commission t ")
		  .append(" where 1=1 ")
		  .append(" and t.id = ? ");
		List<Commission> returnList = (List<Commission>)getHibernateTemplate().find(sb.toString(), id);
		return returnList.isEmpty() ? null : returnList.get(0);
	}
	
	public List<Commission> findCommissionByUserId(String userId){
		StringBuffer sb = new StringBuffer();
		sb.append(" select c ")
		  .append(" from Commission c ")
		  .append(" where 1 = 1 ")
		  .append(" and c.user.id = ? ");
		List<Commission> returnList = (List<Commission>)getHibernateTemplate().find(sb.toString(), new String[] {userId});
		return returnList;
	}

}
