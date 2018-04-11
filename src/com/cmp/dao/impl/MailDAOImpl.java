package com.cmp.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.MailDAO;
import com.cmp.model.Mail;

@Repository
@Transactional
public class MailDAOImpl extends BaseDaoHibernate implements MailDAO {
	
	@Override
	public Mail saveMail(Mail mail) {
		return (Mail) getHibernateTemplate().merge(mail);
	}
	
	@Override
	public void deleteMail(Mail mail) {
		getHibernateTemplate().delete(mail);
	}
	
	@Override
	public void deleteMailById(long id) {
		deleteMail(findMailById(id));
	}
	
	@Override
	public void deleteMails(List<Mail> mail) {
		getHibernateTemplate().deleteAll(mail);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Mail findMailById(long id) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from Mail m ")
		  .append(" where 1=1 ")
		  .append(" and m.id = ? ");
		List<Mail> returnList = (List<Mail>)getHibernateTemplate().find(sb.toString(), id);
		return returnList.isEmpty() ? null : returnList.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Mail> findMailByUser(String mailFromId, String mailToId, int status, Boolean beenRead, Integer start,Integer length) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select m ")
		.append(" from Mail m ")
		.append(" where 1=1 ")
		.append(" and m.status = :status ");
		if(StringUtils.isNotBlank(mailFromId)){
			sb.append(" and m.mailFrom.id = :mailFromId ");
		}
		if(StringUtils.isNotBlank(mailToId)){
			sb.append(" and m.mailTo.id = :mailToId ");
		}
		if(null!=beenRead){
			sb.append(" and m.beenRead = :beenRead ");
		}
		sb.append(" order by m.updateTime desc ");
	    Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
	    Query<?> q = session.createQuery(sb.toString());
		if(StringUtils.isNotBlank(mailFromId)){
			q.setParameter("mailFromId", mailFromId);
		}
		if(StringUtils.isNotBlank(mailToId)){
			q.setParameter("mailToId", mailToId);
		}
		if(null!=beenRead){
			q.setParameter("beenRead", beenRead);
		}
		q.setParameter("status", status);
	    q.setFirstResult(start).setMaxResults(length);
	    return (List<Mail>) q.list();
	}
	
	public long countMailByUser(String mailFromId, String mailToId, int status, Boolean beenRead) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(*) ")
		.append(" from Mail m ")
		.append(" where 1=1 ")
		.append(" and m.status = :status ");
		if(StringUtils.isNotBlank(mailFromId)){
			sb.append(" and m.mailFrom.id = :mailFromId ");
		}
		if(StringUtils.isNotBlank(mailToId)){
			sb.append(" and m.mailTo.id = :mailToId ");
		}
		if(null!=beenRead){
			sb.append(" and m.beenRead = :beenRead ");
		}
	    Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
	    Query<?> q = session.createQuery(sb.toString());
		if(StringUtils.isNotBlank(mailFromId)){
			q.setParameter("mailFromId", mailFromId);
		}
		if(StringUtils.isNotBlank(mailToId)){
			q.setParameter("mailToId", mailToId);
		}
		if(null!=beenRead){
			q.setParameter("beenRead", beenRead);
		}
		q.setParameter("status", status);
	    return (long) q.uniqueResult();
	}
}
