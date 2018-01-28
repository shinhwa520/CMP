package com.cmp.service;

import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public interface BaseJobService extends Job {
	public static final String jobClassName = "com.cmp.service.impl.ApiJobServiceImpl";
	public static final String jobGroupName = "1";
	
	public void addJob(String webName, String jobGroupName, String cronExpression) throws Exception;

	public void execute(JobExecutionContext context) throws JobExecutionException;
	
	public List<Object[]> queryJob(String webName) throws Exception;
	
	public void deleteJob(String jobName, String jobGroupName) throws Exception;
	
	public void modifyJob(String jobName, String jobGroupName, String newCronExpression) throws Exception;
	
	public void pauseJob(String jobName, String jobGroupName) throws Exception;
	
	public void resumeJob(String jobName, String jobGroupName) throws Exception;
}
