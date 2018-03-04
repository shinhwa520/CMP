package com.cmp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.StatusDAO;
import com.cmp.model.Status;
import com.cmp.service.StatusService;

@Service("statusService")
@Transactional
public class StatusServiceImpl implements StatusService {
	@Autowired
	private StatusDAO statusDAO;
	@Override
	public List<Status> listStatus(Integer start, Integer length){
//		SecurityUser securityUser = SecurityUtil.getSecurityUser();
		Status s = statusDAO.findStatus("USER", 6);
		return statusDAO.listStatus(start, length);
	}
	
	@Override
	public long countStatus(){
		return statusDAO.countStatus();
	}
}
