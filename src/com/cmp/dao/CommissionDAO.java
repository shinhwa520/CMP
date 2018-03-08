package com.cmp.dao;

import com.cmp.model.Commission;

public interface CommissionDAO {
	Commission saveCommission(Commission commission);
	Commission findCommissionById(String id);
}
