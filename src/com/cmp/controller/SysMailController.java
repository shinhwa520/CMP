package com.cmp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmp.AppResponse;
import com.cmp.DatatableResponse;
import com.cmp.MenuItem;
import com.cmp.model.SysMail;
import com.cmp.security.SecurityUtil;
import com.cmp.service.SysMailService;

@Controller
@RequestMapping(value="/sysMail")
public class SysMailController extends BaseController {
	private static Log log = LogFactory.getLog(SysMailController.class);
	@Autowired
	private SysMailService sysMailService;
	
	@RequestMapping(value = "sysMailbox", method = RequestMethod.GET)
	public String viewList(Model model, @RequestParam(name="sysMailId", required=false) Long sysMailId) {
		if(null!=sysMailId){
			SysMail sysMail = sysMailService.findSysMailById(sysMailId);
			model.addAttribute("sysMail", sysMail);
		}
		setActiveMenu(model, MenuItem.ADMIN_STATUS);
		return "sysMail/sysMailbox";
	}
	
	/**
	 * Retrieve SysMailByUser
	 * 
	 * @param start
	 * @param length
	 * @return DatatableResponse
	 */
	@RequestMapping(value="listSysMailByUser.json", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody DatatableResponse listSysMailByUser(
			@RequestParam(name="start", required=false, defaultValue="0") Integer start,
			@RequestParam(name="length", required=false, defaultValue="10") Integer length) {
		String userId = SecurityUtil.getSecurityUser().getUser().getId();
		List<SysMail> datalist = sysMailService.listSysMailByUser(null, userId, true, null, start, length);
		long total = sysMailService.countSysMailByUser(null, userId, true, null);
		return new DatatableResponse(total, datalist, total);
	}
	
	@RequestMapping(value = { "/getSysMailInfo" }, method = RequestMethod.GET, produces="application/json")
    public @ResponseBody AppResponse getSysMailInfo(HttpServletRequest request, HttpServletResponse response) {
		AppResponse appResponse = null;
		try {
			appResponse = new AppResponse(HttpServletResponse.SC_OK, "", sysMailService.getSysMailInfo());
		} catch (Exception e) {
			appResponse = new AppResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "");
			e.printStackTrace();
		}
		return appResponse;
    }
	
	@RequestMapping(value = { "/sendSysMail" }, method = RequestMethod.GET, produces="application/json")
    public @ResponseBody AppResponse sendSysMail(HttpServletRequest request, HttpServletResponse response,
    		@RequestParam(name="newSubject", required=false) String newSubject,
			@RequestParam(name="newContent", required=false) String newContent,
			@RequestParam(name="mailTo", required=false) String mailTo) {
		AppResponse appResponse = null;
		try {
			sysMailService.sendSysMail(newSubject, newContent, mailTo);
			appResponse = new AppResponse(HttpServletResponse.SC_OK, "");
		} catch (Exception e) {
			appResponse = new AppResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "");
			e.printStackTrace();
		}
		return appResponse;
    }
	
	@RequestMapping(value = { "/sysMailboxDetail" }, method = RequestMethod.GET, produces="application/json")
    public @ResponseBody AppResponse sysMailboxDetail(HttpServletRequest request, HttpServletResponse response,
    		@RequestParam(name="sysMailId", required=true) Long sysMailId) {
		AppResponse appResponse = null;
		try {
			SysMail sysMail = sysMailService.findSysMailById(sysMailId);
			appResponse = new AppResponse(HttpServletResponse.SC_OK, "");
			appResponse.putData("targetSysMail", sysMail);
		} catch (Exception e) {
			appResponse = new AppResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "");
			e.printStackTrace();
		}
		return appResponse;
    }
	
	@RequestMapping(value = { "/deleteSysMail" }, method = RequestMethod.GET, produces="application/json")
    public @ResponseBody AppResponse deleteSysMail(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name="target", required=false) String target) {
		AppResponse appResponse = null;
		try {
			sysMailService.deleteSysMail(target);
			appResponse = new AppResponse(HttpServletResponse.SC_OK, "");
		} catch (Exception e) {
			appResponse = new AppResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "");
			e.printStackTrace();
		}
		return appResponse;
    }
	
	@RequestMapping(value = { "/trashSysMail" }, method = RequestMethod.GET, produces="application/json")
    public @ResponseBody AppResponse trashSysMail(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name="target", required=false) String target) {
		AppResponse appResponse = null;
		try {
			sysMailService.trashSysMail(target);
			appResponse = new AppResponse(HttpServletResponse.SC_OK, "");
		} catch (Exception e) {
			appResponse = new AppResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "");
			e.printStackTrace();
		}
		return appResponse;
    }
	
	@RequestMapping(value="trash.json", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody DatatableResponse trash(
			@RequestParam(name="start", required=false, defaultValue="0") Integer start,
			@RequestParam(name="length", required=false, defaultValue="10") Integer length) {
		String userId = SecurityUtil.getSecurityUser().getUser().getId();
		List<SysMail> datalist = sysMailService.listSysMailByUser(null, userId, false, null, start, length);
		long total = sysMailService.countSysMailByUser(null, userId, false, null);
		return new DatatableResponse(total, datalist, total);
	}
}
