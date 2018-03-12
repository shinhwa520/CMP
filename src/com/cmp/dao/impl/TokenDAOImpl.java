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
	public void deleteTokens(List<Token> tokens) {
		getHibernateTemplate().deleteAll(tokens);
	}
	
	@Override
	public Token findTokenById(String id) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from Token t ")
		  .append(" where 1=1 ")
		  .append(" and t.id = ? ");
		List<Token> returnList = (List<Token>)getHibernateTemplate().find(sb.toString(), new String[] {id});
		return returnList.isEmpty() ? null : returnList.get(0);
	}
	
	@Override
	public Token findTokenByUserAndId(String userId, String id) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from Token t ")
		  .append(" where 1=1 ")
		  .append(" and t.user.id = ? ")
		  .append(" and SUBSTRING(t.id,1,4) = ? ");
		List<Token> returnList = (List<Token>)getHibernateTemplate().find(sb.toString(), new String[] {userId, id});
		return returnList.isEmpty() ? null : returnList.get(0);
	}
	
	@Override
	public List<Token> findTokenByUserAndType(String userId, String type) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from Token t ")
		  .append(" where 1=1 ")
		  .append(" and t.user.id = ? ")
		  .append(" and t.type = ? ");
		return (List<Token>)getHibernateTemplate().find(sb.toString(), new String[] {userId, type});
	}
	
}
