package com.cmp.service;

import java.util.List;

import com.cmp.model.User;
import com.cmp.service.vo.RegistrationUserVO;

public interface RegistrationService {
	boolean checkEmailAvailable(String mailAddress) throws Exception;
	User initUser(String mailAddress, String mailContent) throws Exception;
	User verifyToken(String userId, String tokenId) throws Exception;
	void saveUserInfo(RegistrationUserVO vo) throws Exception;
	RegistrationUserVO initQuestList() throws Exception;
	List<User> findUserByAccount(String account);
	User findUserByUserId(String userId);
	void saveUserQues(String userId, String results) throws Exception;
	boolean upstream(String userId, String upstreamAccount) throws Exception;
	void agreement(String userId) throws Exception;
}
