package com.cmp.dao;

import java.util.List;

import com.cmp.model.WebApiSetting;

public interface WebApiSettingDAO {

	public List<WebApiSetting> findWebApiSettingByWebNameAndModuleId(String webName, String moduleId);
}
