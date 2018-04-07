package com.cmp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.SysLogDAO;
import com.cmp.model.SysLog;
import com.cmp.service.SysLogService;

@Service("sysLogServiceImpl")
@Transactional
public class SysLogServiceImpl implements SysLogService {
	@Autowired
	private SysLogDAO sysLogDAO;
	
	@Override
	public SysLog saveSysLog(SysLog sysLog) {
		return sysLogDAO.saveSysLog(sysLog);
	}
	
	@Override
	public List<SysLog> findSysLogByUserAction(String userId, String action, Integer start,Integer length) {
		return sysLogDAO.findSysLogByUserAction(userId, action, start, length);
	}
	
	@Override
	public long countSysLogByUserAction(String userId, String action) {
		return sysLogDAO.countSysLogByUserAction(userId, action);
	}


}
