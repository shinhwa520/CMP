package com.cmp.dao;

import com.cmp.model.WebApiDetail;

public interface WebApiDetailDAO {
	public WebApiDetail saveWebApiDetail(WebApiDetail webApiDetail);
	public WebApiDetail findWebApiDetailByParameterValues(String parameterValues);
	public WebApiDetail findWebApiDetailByUserId(String userId);
	
}
