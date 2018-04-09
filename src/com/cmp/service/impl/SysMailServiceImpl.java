package com.cmp.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.SysMailDAO;
import com.cmp.dao.UserDAO;
import com.cmp.model.SysMail;
import com.cmp.model.User;
import com.cmp.security.SecurityUtil;
import com.cmp.service.SysMailService;

@Service("sysMailService")
@Transactional
public class SysMailServiceImpl implements SysMailService {
	@Autowired
	private SysMailDAO sysMailDAO;
	@Autowired
	private UserDAO userDao;
	
	@Override
	public void sendSysMail(String newSubject, String newContent, String mailTo) {
		User editor = SecurityUtil.getSecurityUser().getUser();
		String[] tmpArr = mailTo.split(",");
		for(int i=0; i<tmpArr.length; i++){
			if(StringUtils.isBlank(tmpArr[i]))
				continue;
			sysMailDAO.saveSysMail(new SysMail(editor, userDao.findUserById(tmpArr[i]), newSubject, newContent));
		}
	}
	
	@Override
	public SysMail findSysMailById(Long id){
		SysMail mail = sysMailDAO.findSysMailById(id);
		mail.setBeenRead(true);
		mail = sysMailDAO.saveSysMail(mail);
		return mail;
	}
	
	@Override
	public List<SysMail> listSysMailByUser(String mailFromId, String mailToId, boolean alive, Boolean beenRead, Integer start,Integer length){
		return sysMailDAO.findSysMailByUser(mailFromId, mailToId, alive, beenRead, start, length);
	}
	
	@Override
	public long countSysMailByUser(String mailFromId, String mailToId, boolean alive, Boolean beenRead){
		return sysMailDAO.countSysMailByUser(mailFromId, mailToId, alive, beenRead);
	}
	
	public HashMap<String, Object> getSysMailInfo(){
		HashMap<String, Object> data = new HashMap<String, Object>();
		String userId = SecurityUtil.getSecurityUser().getUser().getId();
		data.put("listUnread", sysMailDAO.findSysMailByUser(null, userId, true, false, 0, 3));
		data.put("countUnread", sysMailDAO.countSysMailByUser(null, userId, true, false));
		data.put("countNotAlive", sysMailDAO.countSysMailByUser(null, userId, false, null));
		data.put("mailTo", userDao.findUserByChannelId(userId));
		return data;
	}

	@Override
	public void deleteSysMail(String mailIds) {
		String[] tmpArr = mailIds.split(",");
		for(int i=0; i<tmpArr.length; i++){
			if(StringUtils.isBlank(tmpArr[i]))
				continue;
			sysMailDAO.deleteSysMailById(Long.parseLong(tmpArr[i]));
		}
	}
	
	@Override
	public void trashSysMail(String mailIds) {
		User editor = SecurityUtil.getSecurityUser().getUser();
		Date current = new Date();
		String[] tmpArr = mailIds.split(",");
		for(int i=0; i<tmpArr.length; i++){
			if(StringUtils.isBlank(tmpArr[i]))
				continue;
			SysMail mail = sysMailDAO.findSysMailById(Long.parseLong(tmpArr[i]));
			mail.setAlive(false);
			mail.setUpdateBy(editor);
			mail.setUpdateTime(current);
			sysMailDAO.saveSysMail(mail);
		}
	}
}
