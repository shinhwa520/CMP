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
@RequestMapping("/job")
public class JobController extends BaseController {
	private static Log log = LogFactory.getLog(JobController.class);
	
	@Autowired
	private BaseJobService jobService;
	
	@RequestMapping(value = { "manage" }, method = RequestMethod.GET)
    public String retrieveManage(Model model, @ModelAttribute("JobForm") JobForm form, HttpServletRequest request, HttpServletResponse response) {
    	try {
    		jobService.queryJob("MAKA");
    		
    	} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			setActiveMenu(model, MenuItem.API_MANAGE);
		}
    	
        return "api/job_manage";
    }
	
	@RequestMapping(value = { "add" }, method = RequestMethod.POST)
	public String doAddJob(Model model, @ModelAttribute("JobForm") JobForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			jobService.addJob("MAKA", BaseJobService.jobGroupName, "0 0/1 * * * ?");
			model.addAttribute("message", "新增成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "[ERROR]"+e.toString());
		}
		
		return retrieveManage(model, form, request, response);
	}
	
	@RequestMapping(value = { "delete" }, method = RequestMethod.POST)
	public String doDeleteJob(Model model, @ModelAttribute("JobForm") JobForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			jobService.deleteJob(BaseJobService.jobClassName, BaseJobService.jobGroupName);
			model.addAttribute("message", "刪除成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "[ERROR]"+e.toString());
		}
		
		return retrieveManage(model, form, request, response);
	}
	
	@RequestMapping(value = { "modify" }, method = RequestMethod.POST)
	public String doModifyJob(Model model, @ModelAttribute("JobForm") JobForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retrieveManage(model, form, request, response);
	}
	
	@RequestMapping(value = { "pause" }, method = RequestMethod.POST)
	public String doPauseJob(Model model, @ModelAttribute("JobForm") JobForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			jobService.pauseJob(BaseJobService.jobClassName, BaseJobService.jobGroupName);
			model.addAttribute("message", "暫停成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "[ERROR]"+e.toString());
		}
		
		return retrieveManage(model, form, request, response);
	}
	
	@RequestMapping(value = { "resume" }, method = RequestMethod.POST)
	public String doResumeJob(Model model, @ModelAttribute("JobForm") JobForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			jobService.resumeJob(BaseJobService.jobClassName, BaseJobService.jobGroupName);
			model.addAttribute("message", "重啟成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "[ERROR]"+e.toString());
		}
		
		return retrieveManage(model, form, request, response);
	}
}
