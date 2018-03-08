package com.cmp.dao;

import java.util.List;

import com.cmp.model.Commission;

public interface CommissionDAO {
	public List<Commission> findCommissionByUserId(String userId);
	Commission saveCommission(Commission commission);
	Commission findCommissionById(String id);
}
