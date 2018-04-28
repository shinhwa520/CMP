package com.cmp.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.SalonDAO;
import com.cmp.model.SalonDetail;
import com.cmp.model.SalonInfo;
import com.cmp.model.SalonSetting;
import com.cmp.service.vo.SalonServiceVO;

@Repository
@Transactional
public class SalonDAOImpl extends BaseDaoHibernate implements SalonDAO {

	@Override
	public SalonInfo findSalonBySalonId(Integer salonId) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select si from SalonInfo si ")
		  .append(" where 1=1 ")
		  .append(" and si.deleteFlag = 'N' ")
		  .append(" and si.salonId = :salonId ");
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
	    Query<?> q = session.createQuery(sb.toString());
	    q.setParameter("salonId", salonId);
	    
	    List<SalonInfo> reList = (List<SalonInfo>)q.list();
		return (reList != null && !reList.isEmpty()) ? reList.get(0) : null;
	}

	@Override
	public List<SalonInfo> findSalon(SalonServiceVO salonServiceVO) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select si from SalonInfo si ")
		  .append(" where 1=1 ")
		  .append(" and si.deleteFlag = 'N' ");
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
	    Query<?> q = session.createQuery(sb.toString());
	    
		return (List<SalonInfo>)q.list();
	}

	@Override
	public void saveSalon(SalonInfo salonInfo, SalonSetting salonSetting) {
		getHibernateTemplate().save(salonSetting);

		salonInfo.setSalonSetting(salonSetting);
		getHibernateTemplate().save(salonInfo);
	}

	@Override
	public Integer deleteSalon(SalonServiceVO salonServiceVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long countSalonDetail(Integer salonId) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(id) from SalonDetail sd ")
		  .append(" where 1=1 ")
		  .append(" and sd.salonInfo.salonId = :salonId ");
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
	    Query<?> q = session.createQuery(sb.toString());
	    q.setParameter("salonId", salonId);
	    
		return (long)q.list().get(0);
	}

	@Override
	public long checkCustHasJoinTheSalonOrNot(Integer salonId, Integer custId) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(id) from SalonDetail sd ")
		  .append(" where 1=1 ")
		  .append(" and sd.salonInfo.salonId = :salonId ")
		  .append(" and sd.cust.id = :custId ");
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
	    Query<?> q = session.createQuery(sb.toString());
	    q.setParameter("salonId", salonId);
	    q.setParameter("custId", custId);
	    
		return (long)q.list().get(0);
	}

	@Override
	public List<SalonDetail> findSalonDetailBySalonIdAndUserId(Integer salonId, String userId) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select sd from SalonDetail sd ")
		  .append(" where 1=1 ")
		  .append(" and sd.salonInfo.salonId = :salonId ");
		
		if (StringUtils.isNotBlank(userId)) {
			sb.append(" and sd.cust.user.id = :userId ");
		}
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
	    Query<?> q = session.createQuery(sb.toString());
	    q.setParameter("salonId", salonId);
	    
	    if (StringUtils.isNotBlank(userId)) {
	    	q.setParameter("userId", userId);
	    }
	    
		return (List<SalonDetail>)q.list();
	}

	@Override
	public long countSalonDetailBySalonIdAndUserId(Integer salonId, String userId) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(id) from SalonDetail sd ")
		  .append(" where 1=1 ")
		  .append(" and sd.salonInfo.salonId = :salonId ")
		  .append(" and sd.cust.user.id = :userId ");
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
	    Query<?> q = session.createQuery(sb.toString());
	    q.setParameter("salonId", salonId);
	    q.setParameter("userId", userId);
	    
		return (long)q.list().get(0);
	}

	@Override
	public Integer deleteSalonDetail(List<SalonDetail> deleteModelList) {
		for (SalonDetail model : deleteModelList) {
			if (model != null) {
				getHibernateTemplate().delete(model);
			}
		}
		return deleteModelList.size();
	}

	@Override
	public boolean addSalonDetail(List<SalonDetail> addModelList) {
		for (SalonDetail model : addModelList) {
			if (model != null) {
				getHibernateTemplate().save(model);
			}
		}
		return true;
	}

	@Override
	public List<SalonDetail> findSalonDetailByCustIds(Integer salonId, List<Integer> custIdList) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select sd from SalonDetail sd ")
		  .append(" where 1=1 ")
		  .append(" and sd.salonInfo.salonId = :salonId ")
		  .append(" and sd.cust.id in ( :custIds ) ");
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
	    Query<?> q = session.createQuery(sb.toString());
	    q.setParameter("salonId", salonId);
	    q.setParameter("custIds", custIdList);
	    
		return (List<SalonDetail>)q.list();
	}

	@Override
	public void updateSalonDetail(SalonDetail salonDetail) {
		getHibernateTemplate().update(salonDetail);
		
	}

}
