package com.cmp.service;

import java.util.List;

import com.cmp.service.vo.ApiServiceVO;

public interface ApiService {
	
	static final String MAKA = "MAKA";
	static final String LOGIN_API_SEQNO = "1";
	static final String SEPERATOR = "@~";

	public List<ApiServiceVO> findData(String webName);
	
	public ApiServiceVO doRetrieve(ApiServiceVO apiServiceVO);
}
