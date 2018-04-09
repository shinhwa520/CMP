package com.cmp.dao;

import java.util.List;

import com.cmp.model.SysMail;

public interface SysMailDAO {
	public SysMail saveSysMail(SysMail mail);
	public void deleteSysMail(SysMail mail);
	public void deleteSysMailById(long id);
	public void deleteSysMails(List<SysMail> mail);
	public SysMail findSysMailById(long id);
	public List<SysMail> findSysMailByUser(String mailFromId, String mailToId, boolean alive, Boolean beenRead, Integer start,Integer length);
	public long countSysMailByUser(String mailFromId, String mailToId, boolean alive, Boolean beenRead);
}
