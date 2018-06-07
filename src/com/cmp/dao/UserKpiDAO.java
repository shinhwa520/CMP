package com.cmp.dao;

import com.cmp.model.UserKpi;

public interface UserKpiDAO {
	void saveUserKpi(UserKpi userKpi);
	UserKpi findKpiByUserAndYearMonth(String userId, String yearMonth);
	
	UserKpi findTheMostRecentlyKpiByUser(String userId);
	
}
