package com.cmp.dao;

import java.util.List;

public interface WebApiMasterDAO {

	public List<Object[]> findWebApiMasterAndDetailByWebName(String webName);
}
