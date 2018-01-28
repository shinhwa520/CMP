package com.cmp.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.WebApiMasterDAO;
import com.cmp.dao.vo.WebApiDAOVO;
import com.cmp.model.Customer;
import com.mchange.lang.IntegerUtils;

@Repository
@Transactional
public class WebApiMasterDAOImpl extends BaseDaoHibernate implements WebApiMasterDAO {

	@Override
	public List<Object[]> findWebApiMasterAndDetailByWebApiDAOVO(WebApiDAOVO webApiDAOVO) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select wam, wad from WebApiMaster wam, WebApiDetail wad ")
		  .append(" where 1=1 ")
		  .append(" and wam.apiMasterId = wad.apiMasterId ");
		 
		if (StringUtils.isNotBlank(webApiDAOVO.getWebName())) {
			sb.append(" and wam.webName = :webName ");
		}
		if (StringUtils.isNotBlank(webApiDAOVO.getMasterSeqNo())) {
			sb.append(" and wam.seqNo = :seqNo ");
		}
		  
		sb.append(" order by wam.seqNo, wad.seqNo ");
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
	    Query<?> q = session.createQuery(sb.toString());
	    if (StringUtils.isNotBlank(webApiDAOVO.getWebName())) {
	    	q.setParameter("webName", webApiDAOVO.getWebName());
		}
		if (StringUtils.isNotBlank(webApiDAOVO.getMasterSeqNo())) {
			q.setParameter("seqNo", Integer.parseInt(webApiDAOVO.getMasterSeqNo()));
		}
	    if (webApiDAOVO.getStartRow() != null && webApiDAOVO.getPageLength() != null) {
	    	q.setFirstResult(webApiDAOVO.getStartRow());
		    q.setMaxResults(webApiDAOVO.getPageLength());
	    }
	    
		return (List<Object[]>) q.list();
	}

	@Override
	public long countWebApiMasterAndDetailByWebApiDAOVO(WebApiDAOVO webApiDAOVO) {
		List<Object> paraList = new ArrayList<Object>();
		
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(*) from WebApiMaster wam, WebApiDetail wad ")
		  .append(" where 1=1 ")
		  .append(" and wam.apiMasterId = wad.apiMasterId ");
		 
		if (StringUtils.isNotBlank(webApiDAOVO.getWebName())) {
			sb.append(" and wam.webName = ? ");
			paraList.add(webApiDAOVO.getWebName());
		}
		if (StringUtils.isNotBlank(webApiDAOVO.getMasterSeqNo())) {
			sb.append(" and wam.seqNo = ? ");
			paraList.add(webApiDAOVO.getMasterSeqNo());
		}
		
		return DataAccessUtils.longResult(getHibernateTemplate().find(sb.toString(), paraList.toArray()));
	}

}
