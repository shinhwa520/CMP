package com.cmp.dao;

import java.util.List;

import com.cmp.model.UserQues;

public interface UserQuesDAO {
	UserQues saveUserQues(UserQues userQues);
	List<UserQues> listUserQues();
}
