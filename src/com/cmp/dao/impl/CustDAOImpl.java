package com.cmp.dao.impl;

import java.util.List;

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
		  .append(" and c.channel.id = ? ");
		
		List<Customer> returnList = (List<Customer>)getHibernateTemplate().find(sb.toString(), new String[] {channelId});
		return returnList;
	}
	
	@Override
	public int countCustByChannelId(String channelId){
		StringBuffer sb = new StringBuffer();
		sb.append(" from Customer c ")
		  .append(" where 1=1 ")
		  .append(" and c.channel.id = ? ");
		
		List<Customer> returnList = (List<Customer>)getHibernateTemplate().find(sb.toString(), new String[] {channelId});
		return returnList.size();
	}
	
	@Override
	public List<Customer> findCustByUserThroughApiModelId(String apiModelId) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from Customer c, User u, WebApiDetail wad ")
		  .append(" where 1=1 ")
		  .append(" and c.channel.id = wad.userId  ")
		  .append(" wad.parameterValues = ? ");
		
		List<Customer> returnList = (List<Customer>)getHibernateTemplate().find(sb.toString(), new String[] {apiModelId});
		return returnList;
	}
}
