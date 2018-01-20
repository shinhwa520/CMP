package com.cmp.dao;

import com.cmp.model.Token;

public interface TokenDAO {
	Token saveToken(Token token);
	Token findTokenById(String id);
}
