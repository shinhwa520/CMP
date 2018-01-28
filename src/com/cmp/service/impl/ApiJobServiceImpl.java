package com.cmp.service.impl;

import java.util.Date;
import java.util.List;

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
import com.cmp.utils.ApplicationContextUtil;

@Service("jobService")
@DisallowConcurrentExecution
public class ApiJobServiceImpl implements BaseJobService {
	private String webName;
	
	//加入Qulifier註解，通過名稱注入bean
    @Autowired @Qualifier("Scheduler")
    private Scheduler scheduler;
	private ApiService apiService;
	
	@Autowired
	private QuartzDAO quartzDAO;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			System.out.println("Run ApiJobService ... "+new Date());
			
			apiService = (ApiService) ApplicationContextUtil.getBean("apiService");
			apiService.doRetrieveFromJob("MAKA");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addJob(String webName, String jobGroupName, String cronExpression) throws Exception {
		try {
			// 啟動調度器  
	        scheduler.start(); 
	
	        //構建job信息
	        JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass()).withIdentity(jobClassName, jobGroupName).build();
	        
	        //表達式調度構建器(即任務執行的時間)
	        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
	
	        //按新的cronExpression表達式構建一個新的trigger
	        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName, jobGroupName)
	            .withSchedule(scheduleBuilder).build();

        
            scheduler.scheduleJob(jobDetail, trigger);

        } catch (Exception e) {
            System.out.println("創建定時任務失敗"+e);
            throw new Exception("創建定時任務失敗");
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
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(newCronExpression);

            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
            
        } catch (SchedulerException e) {
            System.out.println("更新定时任务失败"+e);
            throw new Exception("更新定时任务失败");
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
