package com.cmp.dao;

import java.util.List;

import com.cmp.model.Token;

public interface TokenDAO {
	Token saveToken(Token token);
	void deleteTokens(List<Token> tokens);
	Token findTokenById(String id);
	Token findTokenByUserAndId(String userId, String id);
	List<Token> findTokenByUserAndType(String userId, String type);
}
