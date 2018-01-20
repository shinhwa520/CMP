package com.cmp.service;

import com.cmp.model.User;
import com.cmp.service.vo.RegistrationUserVO;

public interface RegistrationService {
	
	void initUser(String mailAddress, String mailContent) throws Exception;
	User findUserByToken(String tokenId) throws Exception;
	void saveUserInfo(RegistrationUserVO vo) throws Exception;
}
