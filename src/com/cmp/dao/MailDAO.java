package com.cmp.dao;

import java.util.List;

import com.cmp.model.Mail;

public interface MailDAO {
	public Mail saveMail(Mail mail);
	public void deleteMail(Mail mail);
	public void deleteMailById(long id);
	public void deleteMails(List<Mail> mail);
	public Mail findMailById(long id);
	public List<Mail> findMailByUser(String mailFromId, String mailToId, boolean alive, Boolean beenRead, Integer start,Integer length);
	public long countMailByUser(String mailFromId, String mailToId, boolean alive, Boolean beenRead);
}
