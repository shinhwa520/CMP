package com.cmp.service;

import java.util.List;

import com.cmp.model.Status;

public interface StatusService {
	List<Status> listStatus(Integer start, Integer length);
	long countStatus();
	List<Status> findStatus(String type);
}