package com.cmp.service;

import java.util.List;

import com.cmp.service.vo.MsgServiceVO;

public interface MsgService {
	
	static final String MAKA = "MAKA";

	public List<MsgServiceVO> findMakaIds();
}
