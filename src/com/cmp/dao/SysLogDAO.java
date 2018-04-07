package com.cmp.dao;

import java.util.List;

import com.cmp.model.SysLog;

public interface SysLogDAO {
	public SysLog saveSysLog(SysLog sysLog);
	public List<SysLog> findSysLogByUserAction(String userId, String action, Integer start,Integer length);
	public long countSysLogByUserAction(String userId, String action);
}
