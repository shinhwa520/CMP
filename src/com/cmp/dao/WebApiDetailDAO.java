package com.cmp.dao;

import com.cmp.model.WebApiDetail;

public interface WebApiDetailDAO {

	public WebApiDetail findWebApiDetailByParameterValues(String parameterValues);
	
}
