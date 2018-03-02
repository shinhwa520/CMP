package com.cmp.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.FileDAO;
import com.cmp.dao.vo.FileDAOVO;
import com.cmp.model.FilesBaseConfig;
import com.cmp.model.FilesCustomer;
import com.cmp.model.FilesPermission;
import com.cmp.model.FilesPublic;
import com.cmp.model.FilesSetting;

@Repository
@Transactional
public class FileDAOImpl extends BaseDaoHibernate implements FileDAO {

	@Override
	public List<Object> findAllPublicFile(boolean isAdmin, Integer startRow, Integer pageLength) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select fp from FilesPublic fp ")
		  .append(" where 1=1 ");
		  if (!isAdmin) {
			  sb.append(" and (fp.filesSetting.activationBegin is null or fp.filesSetting.activationBegin <= sysdate()) ");
		  }
		  
		sb.append(" and (fp.filesSetting.activationEnd is null or fp.filesSetting.activationEnd >= sysdate()) ")
		  .append(" order by fp.filesSetting.onTop desc, fp.seqNo desc ");
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
	    Query<?> q = session.createQuery(sb.toString());
	    if (startRow != null && pageLength != null) {
	    	q.setFirstResult(startRow);
		    q.setMaxResults(pageLength);
	    }
	    
		return (List<Object>) q.list();
	}

	@Override
	public List<Object> findAllCustomerFile(boolean isAdmin, Integer startRow, Integer pageLength) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> findPublicFileByDAOVO(FileDAOVO fileDAOVO) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select fp from FilesPublic fp ")
		  .append(" where 1=1 ");
		  
		if (fileDAOVO.getSeqNo() != null) {
			sb.append(" and fp.seqNo = :seqNo ");
		}
		if (fileDAOVO.getUpperFileName() != null) {
			sb.append(" and fp.upperFileName = :upperFileName ");
		}
		sb.append(" order by fp.filesSetting.onTop desc, fp.seqNo desc ");
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
	    Query<?> q = session.createQuery(sb.toString());
	    
	    if (fileDAOVO.getSeqNo() != null) {
	    	q.setParameter("seqNo", fileDAOVO.getSeqNo());
		}
	    if (StringUtils.isNoneBlank(fileDAOVO.getUpperFileName())) {
	    	q.setParameter("upperFileName", fileDAOVO.getUpperFileName());
		}
	    
		return (List<Object>) q.list();
	}

	@Override
	public List<Object> findCustomerFileByDAOVO(FileDAOVO fileDAOVO, Integer startRow, Integer pageLength) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select fc from FilesCustomer fc ")
		  .append(" where 1=1 ");
	
		if (fileDAOVO.getSeqNo() != null) {
			sb.append(" and fc.seqNo = :seqNo ");
		}
		if (StringUtils.isNotBlank(fileDAOVO.getUpperFileName())) {
			sb.append(" and fc.upperFileName = :upperFileName ");
		}
		if (fileDAOVO.getCustId() != null) {
			sb.append(" and fc.custId = :custId ");
		}
		
		sb.append(" and (fc.filesSetting.activationBegin is null or fc.filesSetting.activationBegin <= sysdate()) ")
		  .append(" and (fc.filesSetting.activationEnd is null or fc.filesSetting.activationEnd >= sysdate()) ")
		  .append(" order by fc.filesSetting.onTop desc, fc.seqNo desc ");
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
	    Query<?> q = session.createQuery(sb.toString());
	    if (startRow != null && pageLength != null) {
	    	q.setFirstResult(startRow);
		    q.setMaxResults(pageLength);
	    }
	    
	    if (fileDAOVO.getSeqNo() != null) {
	    	q.setParameter("seqNo", fileDAOVO.getSeqNo());
		}
	    if (StringUtils.isNoneBlank(fileDAOVO.getUpperFileName())) {
	    	q.setParameter("upperFileName", fileDAOVO.getUpperFileName());
		}
	    if (fileDAOVO.getCustId() != null) {
	    	q.setParameter("custId", fileDAOVO.getCustId());
		}
	    
		return (List<Object>) q.list();
	}

	@Override
	public Integer addFile(Object entity, FilesSetting fSetting, List<FilesPermission> fPermissions) {
		getHibernateTemplate().save(fSetting);
		
		if (entity instanceof FilesPublic) {
			((FilesPublic)entity).setFilesSetting(fSetting);
			
		} else {
			((FilesCustomer)entity).setFilesSetting(fSetting);
		}
		
		getHibernateTemplate().save(entity);
		
		for (FilesPermission fm : fPermissions) {
			fm.setFilesSetting(fSetting);
			fm.setSeqNo(
					entity instanceof FilesPublic
						? ((FilesPublic)entity).getSeqNo()
						: ((FilesCustomer)entity).getSeqNo());
			
			getHibernateTemplate().save(fm);
		}
		
		return entity instanceof FilesPublic ? ((FilesPublic)entity).getSeqNo() : ((FilesCustomer)entity).getSeqNo();
	}

	/**
	 * 
	 * @param keys
	 * key[0] = FilesPublic / FilesCustomer
	 * key[1] = FilesSetting
	 * key[2] = FilesPermission
	 */
	public void deleteFile(List<Object> modelList) {
		for (Object entity : modelList) {
			getHibernateTemplate().delete(entity);
		}
	}

	/**
	 * 
	 * @param keys
	 * key[0] = FilesPublic / FilesCustomer
	 * key[1] = FilesSetting
	 * key[2] = FilesPermission
	 */
	public void modifyFile(List<Object> modelList) {
		for (Object entity : modelList) {
			getHibernateTemplate().update(entity);
		}
	}

	@Override
	public FilesBaseConfig findFilesBaseConfigByConfigName(String configName) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select fbc from FilesBaseConfig fbc ")
		  .append(" where 1=1 ")
		  .append(" and configName = :configName ");
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
	    Query<?> q = session.createQuery(sb.toString());
	    q.setParameter("configName", configName);

	    List retList = q.list();
		return (retList != null && !retList.isEmpty()) ? (FilesBaseConfig)retList.get(0) : null;
	}

	@Override
	public Integer addDownloadCount(String entity, Integer seqNo) {
		StringBuffer sb = new StringBuffer();
		sb.append(" update ")
		  .append(    entity).append(" et ")
		  .append(" set et.downloadTimes = et.downloadTimes + 1 ")
		  .append(" where 1=1 ")
		  .append(" and et.seqNo = :seqNo ");
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
	    Query<?> q = session.createQuery(sb.toString());
	    q.setParameter("seqNo", seqNo);
	    
		Integer ret = q.executeUpdate();
		return ret;
	}

}
