package com.cmp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cmp.form.LoginForm;
import com.cmp.form.MsgForm;
import com.cmp.service.MsgService;
import com.cmp.service.vo.MsgServiceVO;

@Controller
public class MsgController {
	private static Log log = LogFactory.getLog(MsgController.class);
	
	private MsgService msgService;
	
	@RequestMapping(value = { "/retrieve" }, method = RequestMethod.GET)
    public String showMainPage(@ModelAttribute("MsgForm") MsgForm form, HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("oh ya~~~");
    	try {
//    		msgService.findMakaIds();
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
        return "retrieve/retrieve_main";
    }
	
	@RequestMapping(value = { "/retrieve/query" }, method = RequestMethod.POST)
    public String doQuery(@ModelAttribute("MsgForm") MsgForm form, HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("oh ya~~~");
    	try {
    		List<MsgServiceVO> returnList = msgService.findMakaIds();
    		form.setApiList(returnList);
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
        return "retrieve/retrieve_main";
    }
	
	@RequestMapping(value = { "/retrieve/manageJob" }, method = RequestMethod.POST)
    public String retrieveManage(@ModelAttribute("MsgForm") MsgForm form, HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("oh ya~~~");
    	
        return "retrieve/retrieve_manage";
    }

	@Autowired
	public void setMsgService(MsgService msgService) {
		this.msgService = msgService;
	}
}
