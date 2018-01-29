package com.cmp.service;

import java.util.List;

import com.cmp.model.User;
import com.cmp.service.vo.RegistrationUserVO;

public interface RegistrationService {
	boolean checkEmailAvailable(String mailAddress) throws Exception;
	void initUser(String mailAddress, String mailContent) throws Exception;
	User verifyToken(String tokenId) throws Exception;
	void saveUserInfo(RegistrationUserVO vo) throws Exception;
	RegistrationUserVO initQuestList() throws Exception;
	List<User> findUserByAccount(String account);
	void saveUserQues(String userId, String results) throws Exception;
}
