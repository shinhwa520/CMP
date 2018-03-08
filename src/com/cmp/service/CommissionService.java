package com.cmp.service;

import java.util.List;

import com.cmp.model.Commission;

public interface CommissionService {
	public List<Commission> initCommissionList(String userId);
	public void updateCommission(String ori, String results);
}
