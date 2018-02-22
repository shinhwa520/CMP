package com.cmp.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.UserKpiDAO;
import com.cmp.model.UserKpi;

@Repository
@Transactional
public class UserKpiDAOImpl extends BaseDaoHibernate implements UserKpiDAO {
	
	@Override
	public UserKpi saveUserKpi(UserKpi userKpi) {
		return (UserKpi) getHibernateTemplate().merge(userKpi);
//	   sessionFactory.getCurrentSession().save(user);
	}
	
	@Override
	public UserKpi findTokenByUserAndYearMonth(String userId, String yearMonth) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from UserKpi k ")
		  .append(" where 1=1 ")
		  .append(" and k.user.id = ? ")
		  .append(" and k.yearMonth = ? ");
		List<UserKpi> returnList = (List<UserKpi>)getHibernateTemplate().find(sb.toString(), new String[] {userId, yearMonth});
		return returnList.isEmpty() ? null : returnList.get(0);
	}
}
