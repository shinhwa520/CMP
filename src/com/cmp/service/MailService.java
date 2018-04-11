package com.cmp.service;

import java.util.HashMap;
import java.util.List;

import com.cmp.model.Mail;

public interface MailService {
	public void sendMail(String newSubject, String newContent, String mailTo);
	public Mail findMailById(Long id);
	public List<Mail> listMailByUser(String mailFromId, String mailToId, int status, Boolean beenRead, Integer start,Integer length);
	public long countMailByUser(String mailFromId, String mailToId, int status, Boolean beenRead);
	public HashMap<String, Object> getMailInfo();
	
	public void deleteMail(String mailIds);
	public void trashMail(String mailIds);
	public void saveMail(String mailIds);
	public void inboxMail(String mailIds);
}