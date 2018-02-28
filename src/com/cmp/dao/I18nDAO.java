package com.cmp.dao;

import java.util.List;

import com.cmp.model.I18n;

public interface I18nDAO {
	I18n findI18n(String key, String locale);
	List<I18n> listI18n();
}
