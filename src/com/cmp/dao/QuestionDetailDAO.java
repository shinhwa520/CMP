package com.cmp.dao;

import java.util.List;

import com.cmp.model.QuestionDetail;

public interface QuestionDetailDAO {
	QuestionDetail saveQuestionDetail(QuestionDetail detail);
	QuestionDetail findQuestionDetailById(String id);
	List<QuestionDetail> listQuestionDetail();
}
