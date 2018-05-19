package com.cmp.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
	public UserKpi findKpiByUserAndYearMonth(String userId, String yearMonth) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from UserKpi k ")
		  .append(" where 1=1 ")
		  .append(" and k.user.id = ? ")
		  .append(" and k.yearMonth = ? ");
		List<UserKpi> returnList = (List<UserKpi>)getHibernateTemplate().find(sb.toString(), new String[] {userId, yearMonth});
		return returnList.isEmpty() ? null : returnList.get(0);
	}

	@Override
	public UserKpi findTheMostRecentlyKpiByUser(String userId) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select k.* ")
		  .append(" from User_Kpi k ")
		  .append(" where 1=1 ")
		  .append(" and k.user_id = :userId ")
		  .append(" order by cast(k.year_mon as SIGNED INTEGER) desc ");
		
		SessionFactory sessionFactory = getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		SQLQuery query = session.createSQLQuery(sb.toString());
		query.setString("userId", userId);
		
		List<UserKpi> returnList = query.list();
		return returnList.isEmpty() ? null : returnList.get(0);
	}
}
