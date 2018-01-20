package com.cmp.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.TokenDAO;
import com.cmp.model.Token;

@Repository
@Transactional
public class TokenDAOImpl extends BaseDaoHibernate implements TokenDAO {
	
	@Override
	public Token saveToken(Token token) {
		return (Token) getHibernateTemplate().merge(token);
//	   sessionFactory.getCurrentSession().save(user);
	}
	
	@Override
	public Token findTokenById(String id) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from User u ")
		  .append(" where 1=1 ")
		  .append(" and u.id = ? ");
		List<Token> returnList = (List<Token>)getHibernateTemplate().find(sb.toString(), new String[] {id});
		return returnList.isEmpty() ? null : returnList.get(0);
	}
}
