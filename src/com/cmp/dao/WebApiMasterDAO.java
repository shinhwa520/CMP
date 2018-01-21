package com.cmp.dao;

import java.util.List;

import com.cmp.dao.vo.WebApiDAOVO;

public interface WebApiMasterDAO {

	public List<Object[]> findWebApiMasterAndDetailByWebApiDAOVO(WebApiDAOVO webApiDAOVO);
}
