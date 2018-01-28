package com.cmp.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.QuartzDAO;

@Repository
@Transactional
public class QuartzDAOImpl extends BaseDaoHibernate implements QuartzDAO {

	@Override
	public List<Object[]> findQuartzData() throws Exception {

		StringBuffer sb = new StringBuffer();
		sb.append(" select ")
		  .append("   qt.SCHED_NAME ")
		  .append("  ,qt.TRIGGER_NAME ")
		  .append("  ,qt.TRIGGER_GROUP ")
		  .append("  ,qt.DESCRIPTION ")
		  .append("  ,FROM_UNIXTIME(qt.prev_fire_time / 1000) ")
		  .append("  ,FROM_UNIXTIME(qt.next_fire_time / 1000) ")
		  .append("  ,qt.PRIORITY ")
		  .append("  ,qt.TRIGGER_STATE ")
		  .append("  ,qt.TRIGGER_TYPE ")
		  .append("  ,FROM_UNIXTIME(qt.start_time / 1000) ")
		  .append("  ,FROM_UNIXTIME(qt.end_time / 1000) ")
		  .append("  ,qt.MISFIRE_INSTR ")
		  .append("  ,qct.CRON_EXPRESSION ")
		  .append("  ,qct.TIME_ZONE_ID ")
		  .append(" from qrtz_triggers qt ")
		  .append("     ,qrtz_cron_triggers qct ")
		  .append(" where 1=1 ")
		  .append(" and qt.SCHED_NAME = qct.SCHED_NAME ")
		  .append(" and qt.TRIGGER_NAME = qct.TRIGGER_NAME ")
		  .append(" and qt.TRIGGER_GROUP = qct.TRIGGER_GROUP ");
		
		SessionFactory sessionFactory = getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		SQLQuery query = session.createSQLQuery(sb.toString());
		List<Object[]> returnList = query.list();
		
		return returnList;
	}

	
}
