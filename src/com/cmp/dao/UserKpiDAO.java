package com.cmp.dao;

import com.cmp.model.UserKpi;

public interface UserKpiDAO {
	UserKpi saveUserKpi(UserKpi userKpi);
	UserKpi findTokenByUserAndYearMonth(String userId, String yearMonth);
}
