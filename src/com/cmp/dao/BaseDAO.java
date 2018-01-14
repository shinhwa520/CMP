package com.cmp.dao;
import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class BaseDAO<T extends Object> {
	private static final Logger logger = LoggerFactory.getLogger(BaseDAO.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	private Class<T> entityClass;
	
	public BaseDAO(Class<T> entityType) {
		super();
		
		entityClass = entityType;
	}
	
	public Session getSession() {
		Session s = sessionFactory.getCurrentSession();
		logger.debug("getEntityCount: " + s.getStatistics().getEntityCount());
		return s;
	}


	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public List<T> findAll() {
		return getSession().createCriteria(entityClass).list();
	}
	
	public List<T> findAll(int start, int length) {
		return getSession().createCriteria(entityClass).setFirstResult(start).setMaxResults(length).list();
	}
	
	public List<T> findAllOrderAsc(String orderCol, int start, int length) {
		return getSession().createCriteria(entityClass).addOrder(Order.asc(orderCol)).setFirstResult(start).setMaxResults(length).list();
	}
	
	public List<T> findAllOrderDesc(String orderCol, int start, int length) {
		return getSession().createCriteria(entityClass).addOrder(Order.desc(orderCol)).setFirstResult(start).setMaxResults(length).list();
	}
	
	public T findById(Serializable id) {
		return (T)getSession().get(entityClass, id);
	}
	
	public void persist(T entity) {
		getSession().persist(entity);
		
		logger.debug("entity persisted");
	}
	
	public void save(T entity) {
		getSession().save(entity);
		
		logger.debug("entity saved");
	}

	public void update(T entity) {
		getSession().update(entity);
	}
	
	public void delete(T entity) {
		getSession().delete(entity);
	}

	public long countAll() {
		return (Long)getSession().createCriteria(entityClass).setProjection(Projections.rowCount()).uniqueResult();
	}
	
	public List<? extends Object> paginationQuery(Query query, Integer start, Integer length) {
		return query.setFirstResult(start).setMaxResults(length).list();
	}
}
