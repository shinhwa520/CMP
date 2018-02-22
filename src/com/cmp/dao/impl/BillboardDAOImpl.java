package com.cmp.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.BillboardDAO;
import com.cmp.dao.vo.BillboardDAOVO;
import com.cmp.model.BillboardContent;
import com.cmp.model.BillboardPermission;
import com.cmp.model.BillboardSetting;

@Repository
@Transactional
public class BillboardDAOImpl extends BaseDaoHibernate implements BillboardDAO {

	@Override
	public List<BillboardContent> findAllBillboardRecords(boolean isAdmin, Integer startRow, Integer pageLength) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select bc from BillboardContent bc ")
		  .append(" where 1=1 ");
		  if (!isAdmin) {
			  sb.append(" and (bc.billboardSetting.activationBegin is null or bc.billboardSetting.activationBegin <= sysdate()) ");
		  }
		  
		sb.append(" and (bc.billboardSetting.activationEnd is null or bc.billboardSetting.activationEnd >= sysdate()) ")
		  .append(" order by bc.billboardSetting.onTop desc, bc.seqNo desc ");
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
	    Query<?> q = session.createQuery(sb.toString());
	    if (startRow != null && pageLength != null) {
	    	q.setFirstResult(startRow);
		    q.setMaxResults(pageLength);
	    }
	    
		return (List<BillboardContent>) q.list();
	}

	@Override
	public List<BillboardContent> findBillboardRecordsByDAOVO(BillboardDAOVO billboardDAOVO) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select bc from BillboardContent bc ")
		  .append(" where 1=1 ");
		
		if (billboardDAOVO.getSeqNo() != null) {
			sb.append(" and bc.seqNo = :seqNo ");
		}
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query<?> q = session.createQuery(sb.toString());
		
		if (billboardDAOVO.getSeqNo() != null) {
	    	q.setParameter("seqNo", billboardDAOVO.getSeqNo());
		}
	    
		return (List<BillboardContent>) q.list();
	}

	@Override
	public void modifyBillboardRecord(boolean isAdd, BillboardContent billContent, BillboardSetting billSetting, List<BillboardPermission> oldBillPermissions, List<BillboardPermission> newBillPermissions) {
		if (isAdd) {
			getHibernateTemplate().save(billSetting);
			
		} else {
			getHibernateTemplate().update(billSetting);
		}
		
		billContent.setBillboardSetting(billSetting);
		
		if (isAdd) {
			getHibernateTemplate().save(billContent);
		} else {
			getHibernateTemplate().update(billContent);
		}
		
//		if (oldBillPermissions != null) {
//			for (BillboardPermission oldBillPermission : oldBillPermissions) {
//				getHibernateTemplate().delete(oldBillPermission);
//			}
//		}
		
		if (isAdd) {
			for (BillboardPermission newBillPermission : newBillPermissions) {
				newBillPermission.setBillboardContent(billContent);
				getHibernateTemplate().save(newBillPermission);
			}
		}
	}

	/**
	 * 
	 * @param keys
	 * key[0] = BillboardContent
	 * key[1] = BillboardSetting
	 * key[2] = BillboardPermission
	 */
	public void deleteBillboardRecords(List<Object> modelList) {
		for (Object model : modelList) {
			if (model != null) {
				getHibernateTemplate().delete(model);
			}
		}
	}

}
