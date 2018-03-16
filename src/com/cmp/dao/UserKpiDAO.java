package com.cmp.dao;

import com.cmp.model.UserKpi;

public interface UserKpiDAO {
	UserKpi saveUserKpi(UserKpi userKpi);
	UserKpi findKpiByUserAndYearMonth(String userId, String yearMonth);
}
