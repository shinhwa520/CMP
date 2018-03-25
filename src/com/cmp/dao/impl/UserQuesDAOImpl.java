package com.cmp.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.UserQuesDAO;
import com.cmp.model.UserQues;


@Repository
@Transactional
public class UserQuesDAOImpl extends BaseDaoHibernate implements UserQuesDAO {

	@Override
	public UserQues saveUserQues(UserQues userQues) {
		return (UserQues) getHibernateTemplate().merge(userQues);
//	   sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public List<UserQues> listUserQues() {
		StringBuffer sb = new StringBuffer();
		sb.append(" from UserQues t ")
		  .append(" where 1=1 ")
		  .append(" order by t.questionDetail.question.sort ");
		List<UserQues> returnList = (List<UserQues>)getHibernateTemplate().find(sb.toString());
		return returnList;
	}
	

}
