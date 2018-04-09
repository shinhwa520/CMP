package com.cmp.service;

import java.util.HashMap;
import java.util.List;

import com.cmp.model.SysMail;

public interface SysMailService {
	public void sendSysMail(String newSubject, String newContent, String mailTo);
	public SysMail findSysMailById(Long id);
	public List<SysMail> listSysMailByUser(String mailFromId, String mailToId, boolean alive, Boolean beenRead, Integer start,Integer length);
	public long countSysMailByUser(String mailFromId, String mailToId, boolean alive, Boolean beenRead);
	public HashMap<String, Object> getSysMailInfo();
	
	public void deleteSysMail(String mailIds);
	public void trashSysMail(String mailIds);
}