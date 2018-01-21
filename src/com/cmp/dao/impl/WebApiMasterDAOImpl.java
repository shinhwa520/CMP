package com.cmp.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.WebApiMasterDAO;
import com.cmp.dao.vo.WebApiDAOVO;

@Repository
@Transactional
public class WebApiMasterDAOImpl extends BaseDaoHibernate implements WebApiMasterDAO {

	@Override
	public List<Object[]> findWebApiMasterAndDetailByWebApiDAOVO(WebApiDAOVO webApiDAOVO) {
		List<Object> paraList = new ArrayList<Object>();
		
		StringBuffer sb = new StringBuffer();
		sb.append(" select wam, wad from WebApiMaster wam, WebApiDetail wad ")
		  .append(" where 1=1 ")
		  .append(" and wam.apiMasterId = wad.apiMasterId ");
		 
		if (StringUtils.isNotBlank(webApiDAOVO.getWebName())) {
			sb.append(" and wam.webName = ? ");
			paraList.add(webApiDAOVO.getWebName());
		}
		if (StringUtils.isNotBlank(webApiDAOVO.getMasterSeqNo())) {
			sb.append(" and wam.seqNo = ? ");
			paraList.add(Integer.parseInt(webApiDAOVO.getMasterSeqNo()));
		}
		  
		sb.append(" order by wam.seqNo, wad.seqNo ");
		
		List<Object[]> returnList = (List<Object[]>)getHibernateTemplate().find(sb.toString(), paraList.toArray());
		return returnList;
	}

}
