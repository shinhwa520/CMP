package com.cmp.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.FileDAO;
import com.cmp.dao.vo.FileDAOVO;
import com.cmp.model.BillboardContent;
import com.cmp.model.FilesCustomer;
import com.cmp.model.FilesPermission;
import com.cmp.model.FilesPublic;
import com.cmp.model.FilesSetting;

@Repository
@Transactional
public class FileDAOImpl extends BaseDaoHibernate implements FileDAO {

	@Override
	public List<FilesPublic> findAllPublicFile(boolean isAdmin, Integer startRow, Integer pageLength) {
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
	    
		return (List<FilesPublic>) q.list();
	}

	@Override
	public List<FilesPublic> findAllCustomerFile(boolean isAdmin, Integer startRow, Integer pageLength) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FilesPublic> findPublicFileByDAOVO(FileDAOVO fileDAOVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FilesCustomer> findCustomerFileByDAOVO(FileDAOVO fileDAOVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPublicFile(FilesPublic fPublic, FilesSetting fSetting, FilesPermission fPermission) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCustomerFile(FilesCustomer fCustomer, FilesSetting fSetting, FilesPermission fPermission) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePublicFile(FilesPublic fPublic, FilesSetting fSetting, FilesPermission fPermission) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCustomerFile(FilesCustomer fCustomer, FilesSetting fSetting, FilesPermission fPermission) {
		// TODO Auto-generated method stub
		
	}

}
