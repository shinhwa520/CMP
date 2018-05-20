package com.cmp.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.VisitDAO;
import com.cmp.model.VisitDetail;
import com.cmp.model.VisitInfo;
import com.cmp.model.VisitSetting;
import com.cmp.service.vo.VisitServiceVO;

@Repository
@Transactional
public class VisitDAOImpl extends BaseDaoHibernate implements VisitDAO {

	@Override
	public VisitInfo findVisitByVisitId(Integer visitId) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select vi from VisitInfo vi ")
		  .append(" where 1=1 ")
		  .append(" and vi.deleteFlag = 'N' ")
		  .append(" and vi.visitId = :visitId ");
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
	    Query<?> q = session.createQuery(sb.toString());
	    q.setParameter("visitId", visitId);
	    
	    List<VisitInfo> reList = (List<VisitInfo>)q.list();
		return (reList != null && !reList.isEmpty()) ? reList.get(0) : null;
	}
	
	@Override
	public List<VisitInfo> findVisit(VisitServiceVO visitServiceVO) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select vi from VisitInfo vi ")
		  .append(" where 1=1 ")
		  .append(" and vi.deleteFlag = 'N' ");
		
		if (!visitServiceVO.getCanSeeAll()) {
			sb.append(" and vi.createBy in (:createBy) ");
		}
		if (visitServiceVO.getStatusId() != null) {
			sb.append(" and vi.status.id = :statusId ");
		}
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
	    Query<?> q = session.createQuery(sb.toString());
	    if (!visitServiceVO.getCanSeeAll()) {
	    	q.setParameter("createBy", visitServiceVO.getUserAccounts());
		}
	    if (visitServiceVO.getStatusId() != null) {
	    	q.setParameter("statusId", visitServiceVO.getStatusId());
		}
	    
		return (List<VisitInfo>)q.list();
	}

	@Override
	public void saveVisit(VisitInfo visitInfo, VisitSetting visitSetting) {
		getHibernateTemplate().save(visitSetting);

		visitInfo.setVisitSetting(visitSetting);
		getHibernateTemplate().save(visitInfo);
	}

	@Override
	public Integer deleteVisit(VisitServiceVO visitServiceVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long countVisitDetail(Integer visitId) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(id) from VisitDetail vd ")
		  .append(" where 1=1 ")
		  .append(" and vd.visitInfo.visitId = :visitId ");
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
	    Query<?> q = session.createQuery(sb.toString());
	    q.setParameter("visitId", visitId);
	    
		return (long)q.list().get(0);
	}

	@Override
	public List<VisitDetail> findVisitDetailByVisitIdAndUserId(Integer visitId, String userId) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select vd from VisitDetail vd ")
		  .append(" where 1=1 ")
		  .append(" and vd.visitInfo.visitId = :visitId ");
		
		if (StringUtils.isNotBlank(userId)) {
			sb.append(" and vd.cust.user.id = :userId ");
		}
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
	    Query<?> q = session.createQuery(sb.toString());
	    q.setParameter("visitId", visitId);
	    
	    if (StringUtils.isNotBlank(userId)) {
	    	q.setParameter("userId", userId);
	    }
	    
		return (List<VisitDetail>)q.list();
	}
	
	@Override
	public long countVisitDetailByVisitIdAndUserId(Integer visitId, String userId) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(id) from VisitDetail vd ")
		  .append(" where 1=1 ")
		  .append(" and vd.visitInfo.visitId = :visitId ")
		  .append(" and vd.cust.user.id = :userId ");
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
	    Query<?> q = session.createQuery(sb.toString());
	    q.setParameter("visitId", visitId);
	    q.setParameter("userId", userId);
	    
		return (long)q.list().get(0);
	}

	@Override
	public long checkCustHasJoinTheVisitOrNot(Integer visitId, Integer custId) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(id) from VisitDetail vd ")
		  .append(" where 1=1 ")
		  .append(" and vd.visitInfo.visitId = :visitId ")
		  .append(" and vd.cust.id = :custId ");
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
	    Query<?> q = session.createQuery(sb.toString());
	    q.setParameter("visitId", visitId);
	    q.setParameter("custId", custId);
	    
		return (long)q.list().get(0);
	}

	@Override
	public Integer deleteVisitDetail(List<VisitDetail> deleteModelList) {
		for (VisitDetail model : deleteModelList) {
			if (model != null) {
				getHibernateTemplate().delete(model);
			}
		}
		return deleteModelList.size();
	}

	@Override
	public boolean addVisitDetail(List<VisitDetail> addModelList) {
		for (VisitDetail model : addModelList) {
			if (model != null) {
				getHibernateTemplate().save(model);
			}
		}
		return true;
	}

	@Override
	public List<VisitDetail> findVisitDetailByCustIds(Integer visitId, List<Integer> custIdList) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select vd from VisitDetail vd ")
		  .append(" where 1=1 ")
		  .append(" and vd.visitInfo.visitId = :visitId ")
		  .append(" and vd.cust.id in ( :custIds ) ");
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
	    Query<?> q = session.createQuery(sb.toString());
	    q.setParameter("visitId", visitId);
	    q.setParameter("custIds", custIdList);
	    
		return (List<VisitDetail>)q.list();
	}

	@Override
	public void updateVisitDetail(VisitDetail visitDetail) {
		getHibernateTemplate().update(visitDetail);
	}
}
