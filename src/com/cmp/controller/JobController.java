package com.cmp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cmp.MenuItem;
import com.cmp.form.JobForm;
import com.cmp.service.BaseJobService;

@Controller
public class JobController extends BaseController {
	private static Log log = LogFactory.getLog(JobController.class);
	
	private BaseJobService jobService;
	
	@RequestMapping(value = { "/job/manage" }, method = RequestMethod.GET)
    public String retrieveManage(Model model, @ModelAttribute("JobForm") JobForm form, HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("oh ya~~~");
    	try {
    		jobService.queryJob("MAKA");
    		
    	} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			setActiveMenu(model, MenuItem.API_MANAGE);
		}
    	
        return "api/job_manage";
    }
	
	@RequestMapping(value = { "/job/add" }, method = RequestMethod.POST)
	public String doAddJob(Model model, @ModelAttribute("JobForm") JobForm form, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("doAddJob");
		try {
			jobService.addJob("MAKA", BaseJobService.jobGroupName, "0 0/1 * * * ?");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retrieveManage(model, form, request, response);
	}
	
	@RequestMapping(value = { "/job/delete" }, method = RequestMethod.POST)
	public String doDeleteJob(Model model, @ModelAttribute("JobForm") JobForm form, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("doDeleteJob");
		try {
			jobService.deleteJob(BaseJobService.jobClassName, BaseJobService.jobGroupName);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retrieveManage(model, form, request, response);
	}
	
	@RequestMapping(value = { "/job/modify" }, method = RequestMethod.POST)
	public String doModifyJob(Model model, @ModelAttribute("JobForm") JobForm form, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("doModifyJob");
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retrieveManage(model, form, request, response);
	}
	
	@RequestMapping(value = { "/job/pause" }, method = RequestMethod.POST)
	public String doPauseJob(Model model, @ModelAttribute("JobForm") JobForm form, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("doPauseJob");
		try {
			jobService.pauseJob(BaseJobService.jobClassName, BaseJobService.jobGroupName);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retrieveManage(model, form, request, response);
	}
	
	@RequestMapping(value = { "/job/resume" }, method = RequestMethod.POST)
	public String doResumeJob(Model model, @ModelAttribute("JobForm") JobForm form, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("doResumeJob");
		try {
			jobService.resumeJob(BaseJobService.jobClassName, BaseJobService.jobGroupName);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retrieveManage(model, form, request, response);
	}

	@Autowired
	public void setJobService(BaseJobService jobService) {
		this.jobService = jobService;
	}
}
