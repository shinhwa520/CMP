package com.cmp.service;

import java.util.List;

import com.cmp.model.SysLog;

public interface SysLogService {
	public static final String LOGIN = "登入";
	public static final String LOGOUT = "登出";
	public SysLog saveSysLog(SysLog sysLog);
	public List<SysLog> findSysLogByUserAction(String userId, String action, Integer start,Integer length);
	public long countSysLogByUserAction(String userId, String action);
}