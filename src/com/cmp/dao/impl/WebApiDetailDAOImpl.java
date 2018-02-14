package com.cmp.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.WebApiDetailDAO;
import com.cmp.model.WebApiDetail;

@Repository
@Transactional
public class WebApiDetailDAOImpl extends BaseDaoHibernate implements WebApiDetailDAO {

	@Override
	public WebApiDetail findWebApiDetailByParameterValues(String parameterValues) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from WebApiDetail d ")
		  .append(" where 1=1 ")
		  .append(" and d.parameterValues = ? ");
		List<WebApiDetail> returnList = (List<WebApiDetail>)getHibernateTemplate().find(sb.toString(), new String[] {parameterValues});
		return returnList.isEmpty() ? null : returnList.get(0);
	}
	
	@Override
	public WebApiDetail findWebApiDetailByUserId(String userId) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from WebApiDetail d ")
		  .append(" where 1=1 ")
		  .append(" and d.user.id = ? ");
		List<WebApiDetail> returnList = (List<WebApiDetail>)getHibernateTemplate().find(sb.toString(), new String[] {userId});
		return returnList.isEmpty() ? null : returnList.get(0);
	}
}
