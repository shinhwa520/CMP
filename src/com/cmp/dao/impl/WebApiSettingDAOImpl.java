package com.cmp.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.WebApiSettingDAO;
import com.cmp.model.WebApiSetting;

@Repository
@Transactional
public class WebApiSettingDAOImpl extends BaseDaoHibernate implements WebApiSettingDAO {

	@Override
	public WebApiSetting findWebApiSettingByModuleId(String moduleId) {
		List<String> paraList = new ArrayList<String>();
		
		StringBuffer sb = new StringBuffer();
		sb.append(" from WebApiSetting was ")
		  .append(" where 1=1 ");
		 
		if (StringUtils.isNotBlank(moduleId)) {
			sb.append(" and was.moduleId = ? ");
			paraList.add(moduleId);
		}
		
		List<WebApiSetting> returnList = (List<WebApiSetting>)getHibernateTemplate().find(sb.toString(), paraList.toArray());
		WebApiSetting retVal = 
				returnList != null && !returnList.isEmpty() ? returnList.get(0) : null;
		
		return retVal;
	}

}
