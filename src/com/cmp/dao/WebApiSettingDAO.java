package com.cmp.dao;

import com.cmp.model.WebApiSetting;

public interface WebApiSettingDAO {

	public WebApiSetting findWebApiSettingByModuleId(String moduleId);
}
