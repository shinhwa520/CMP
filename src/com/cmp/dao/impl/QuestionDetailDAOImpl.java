package com.cmp.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.QuestionDetailDAO;
import com.cmp.model.QuestionDetail;
import com.cmp.model.User;


@Repository
@Transactional
public class QuestionDetailDAOImpl extends BaseDaoHibernate implements QuestionDetailDAO {

	@Override
	public QuestionDetail saveQuestionDetail(QuestionDetail detail) {
		return (QuestionDetail) getHibernateTemplate().merge(detail);
//	   sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public QuestionDetail findQuestionDetailById(String id) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from QuestionDetail d ")
		  .append(" where 1=1 ")
		  .append(" and d.id = ? ");
		List<QuestionDetail> returnList = (List<QuestionDetail>)getHibernateTemplate().find(sb.toString(), new String[] {id});
		return returnList.isEmpty() ? null : returnList.get(0);
	}
	
	@Override
	public List<QuestionDetail> listQuestionDetail() {
		StringBuffer sb = new StringBuffer();
		sb.append(" from QuestionDetail d ")
		  .append(" where 1=1 ")
		  .append(" order by d.question.sort, d.sort ");
		List<QuestionDetail> returnList = (List<QuestionDetail>)getHibernateTemplate().find(sb.toString());
		return returnList;
	}
	

}
