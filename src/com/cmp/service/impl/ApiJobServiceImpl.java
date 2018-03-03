package com.cmp.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cmp.dao.QuartzDAO;
import com.cmp.service.ApiService;
import com.cmp.service.BaseJobService;
import com.cmp.service.vo.JobServiceVO;
import com.cmp.utils.ApplicationContextUtil;

@Service("jobService")
@DisallowConcurrentExecution
public class ApiJobServiceImpl implements BaseJobService {
	private String webName;
	
    @Autowired @Qualifier("Scheduler")
    private Scheduler scheduler;
	private ApiService apiService;
	
	@Autowired
	private QuartzDAO quartzDAO;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			apiService = (ApiService) ApplicationContextUtil.getBean("apiService");
			apiService.doRetrieveFromJob("MAKA");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addJob(String webName, String jobGroupName, String cronExpression) throws Exception {
		try {
			//start scheduler
	        scheduler.start(); 
	
	        //build job
	        JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass()).withIdentity(jobClassName, jobGroupName).build();
	        
	        //build cron expression
	        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
	
	        //build a new trigger with new cron expression 
	        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName, jobGroupName)
	            .withSchedule(scheduleBuilder).build();

        
            scheduler.scheduleJob(jobDetail, trigger);

        } catch (Exception e) {
            throw new Exception("Build job faild!");
        }
		
	}
	
	public static BaseJobService getClass(String classname) throws Exception 
    {
        Class<?> class1 = Class.forName(classname);
        return (BaseJobService)class1.newInstance();
    }
	
	@Override
	public List<Object[]> queryJob(String webName) throws Exception {
		List<Object[]> reList = null;
		try {
			reList = quartzDAO.findQuartzData();
			
			JobServiceVO jsVO = null;
			for (Object[] obj : reList) {
				jsVO = new JobServiceVO();
				jsVO.setScheduleName(ObjectUtils.toString(obj[0]));
				jsVO.setTriggerName(ObjectUtils.toString(obj[1]));
				jsVO.setTriggerGroup(ObjectUtils.toString(obj[2]));
//				jsVO.
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return reList;
	}

	@Override
	public void deleteJob(String jobName, String jobGroupName) throws Exception {
		scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));  
	}

	@Override
	public void modifyJob(String jobName, String jobGroupName, String newCronExpression) throws Exception {
		try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
            //build cron
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(newCronExpression);

            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            //build a new trigger with new cron
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            //reschedule job with new trigger
            scheduler.rescheduleJob(triggerKey, trigger);
            
        } catch (SchedulerException e) {
            throw new Exception("Modify job failed!");
        }
	}

	@Override
	public void pauseJob(String jobName, String jobGroupName) throws Exception {
		scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
	}

	@Override
	public void resumeJob(String jobName, String jobGroupName) throws Exception {
		scheduler.resumeJob(JobKey.jobKey(jobClassName, jobGroupName));
	}
}
