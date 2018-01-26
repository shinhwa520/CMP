package com.cmp.dao.impl;

import java.util.List;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.CustomerDAO;
import com.cmp.model.Customer;


@Repository
@Transactional
public class CustDAOImpl extends BaseDaoHibernate implements CustomerDAO {

	@Override
	public List<Customer> findCustByChannelId(String channelId, Integer start,Integer length) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from Customer c ")
		  .append(" where 1=1 ")
		  .append(" and c.user.id = ? ");
		
		List<Customer> returnList = (List<Customer>)getHibernateTemplate().find(sb.toString(), new String[] {channelId});
		return returnList;
	}
	
	@Override
	public long countCustByChannelId(String channelId){
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(*) from Customer c ")
		  .append(" where 1=1 ")
		  .append(" and c.channel.id = ? ");
		
		return DataAccessUtils.longResult(getHibernateTemplate().find(sb.toString(), new String[] {channelId}));
	}
	
	@Override
	public List<Customer> findCustByUserThroughApiModelId(String apiModelId) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select c ")
		  .append(" from Customer c, WebApiDetail wad ")
		  .append(" where 1=1 ")
		  .append(" and c.user.id = wad.user.id  ")
		  .append(" and wad.parameterValues = ? ");
		
		List<Customer> returnList = (List<Customer>)getHibernateTemplate().find(sb.toString(), new String[] {apiModelId});
		return returnList;
	}

	@Override
	public void insertCustByModel(Customer customer) {
		getHibernateTemplate().save(customer);
	}
}
