package com.cmp.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.MailDAO;
import com.cmp.dao.UserDAO;
import com.cmp.model.Mail;
import com.cmp.model.User;
import com.cmp.security.SecurityUtil;
import com.cmp.service.MailService;

@Service("mailService")
@Transactional
public class MailServiceImpl implements MailService {
	@Autowired
	private MailDAO mailDAO;
	@Autowired
	private UserDAO userDao;
	
	@Override
	public void sendMail(String newSubject, String newContent, String mailTo) {
		User editor = SecurityUtil.getSecurityUser().getUser();
		String[] tmpArr = mailTo.split(",");
		for(int i=0; i<tmpArr.length; i++){
			if(StringUtils.isBlank(tmpArr[i]))
				continue;
			mailDAO.saveMail(new Mail(editor, userDao.findUserById(tmpArr[i]), newSubject, newContent));
		}
	}
	
	@Override
	public Mail findMailById(Long id){
		Mail mail = mailDAO.findMailById(id);
		mail.setBeenRead(true);
		mail = mailDAO.saveMail(mail);
		return mail;
	}
	
	@Override
	public List<Mail> listMailByUser(String mailFromId, String mailToId, int status, Boolean beenRead, Integer start,Integer length){
		return mailDAO.findMailByUser(mailFromId, mailToId, status, beenRead, start, length);
	}
	
	@Override
	public long countMailByUser(String mailFromId, String mailToId, int status, Boolean beenRead){
		return mailDAO.countMailByUser(mailFromId, mailToId, status, beenRead);
	}
	
	public HashMap<String, Object> getMailInfo(){
		HashMap<String, Object> data = new HashMap<String, Object>();
		String userId = SecurityUtil.getSecurityUser().getUser() != null ? SecurityUtil.getSecurityUser().getUser().getId() : "";
		data.put("listUnread", mailDAO.findMailByUser(null, userId, 1, false, 0, 3));
		data.put("countUnread", mailDAO.countMailByUser(null, userId, 1, false));
		data.put("countSaved", mailDAO.countMailByUser(null, userId, 2, null));
		data.put("countNotAlive", mailDAO.countMailByUser(null, userId, 0, null));
		data.put("mailTo", userDao.findUserByChannelId(userId));
		return data;
	}

	@Override
	public void deleteMail(String mailIds) {
		String[] tmpArr = mailIds.split(",");
		for(int i=0; i<tmpArr.length; i++){
			if(StringUtils.isBlank(tmpArr[i]))
				continue;
			mailDAO.deleteMailById(Long.parseLong(tmpArr[i]));
		}
	}
	
	@Override
	public void trashMail(String mailIds) {
		changeMailStatus(mailIds, 0);
	}
	
	@Override
	public void saveMail(String mailIds) {
		changeMailStatus(mailIds, 2);
	}
	
	@Override
	public void inboxMail(String mailIds) {
		changeMailStatus(mailIds, 1);
	}
	
	private void changeMailStatus(String mailIds, int status) {
		User editor = SecurityUtil.getSecurityUser().getUser();
		Date current = new Date();
		String[] tmpArr = mailIds.split(",");
		for(int i=0; i<tmpArr.length; i++){
			if(StringUtils.isBlank(tmpArr[i]))
				continue;
			Mail mail = mailDAO.findMailById(Long.parseLong(tmpArr[i]));
			mail.setStatus(status);
			mail.setUpdateBy(editor);
			mail.setUpdateTime(current);
			mailDAO.saveMail(mail);
		}
	}
}
