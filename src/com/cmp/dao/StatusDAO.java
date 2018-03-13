package com.cmp.dao;

import java.util.List;

import com.cmp.model.Status;

public interface StatusDAO {
	Status findStatusById(int id);
	Status findStatus(String type, int sort);
	List<Status> listStatus(Integer start, Integer length);
	long countStatus();
	List<Status> findStatus(String type);
}
