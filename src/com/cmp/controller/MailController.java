package com.cmp.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;

import com.cmp.AppResponse;
import com.cmp.DatatableResponse;
import com.cmp.MenuItem;
import com.cmp.model.Mail;
import com.cmp.security.SecurityUtil;
import com.cmp.service.MailService;

@Controller
@RequestMapping(value="/mail")
public class MailController extends BaseController {
	private static Log log = LogFactory.getLog(MailController.class);
	@Autowired
	private MailService mailService;
	
	@RequestMapping(value = "mailbox", method = RequestMethod.GET)
	public String viewList(Model model, @RequestParam(name="mailId", required=false) Long mailId) {
		setActiveMenu(model, MenuItem.ADMIN_STATUS);
		return "mail/mailbox";
	}
	
	@RequestMapping(value = { "mailbox/{mailId}" }, method = RequestMethod.GET)
    public String mailboxDtl(HttpServletRequest request, HttpServletResponse response,
    		Model model, @PathVariable Long mailId) {
		try {
			Mail mail = mailService.findMailById(mailId);
			model.addAttribute("targetMail_subject", mail.getSubject());
			model.addAttribute("targetMail_mailFrom", mail.getMailFrom().getName());
			model.addAttribute("targetMail_content", mail.getContent());
			model.addAttribute("targetMail_status", mail.getStatus());
			
			model.addAttribute("targetMail_mailFromId", mail.getMailFrom().getId());
			model.addAttribute("targetMail_mailTo", mail.getMailTo().getName());
			model.addAttribute("targetMail_createTime", mail.getCreateTime());
		} catch (Exception e) {
			e.printStackTrace();
			Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
			pathVariables.remove("mailId");
		}
		setActiveMenu(model, MenuItem.ADMIN_STATUS);
		return "mail/mailbox";
    }
	
	/**
	 * Retrieve MailByUser
	 * 
	 * @param start
	 * @param length
	 * @return DatatableResponse
	 */
	@RequestMapping(value="listMailByUser.json", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody DatatableResponse listMailByUser(
			@RequestParam(name="start", required=false, defaultValue="0") Integer start,
			@RequestParam(name="length", required=false, defaultValue="10") Integer length) {
		String userId = SecurityUtil.getSecurityUser().getUser().getId();
		List<Mail> datalist = mailService.listMailByUser(null, userId, 1, null, start, length);
		long total = mailService.countMailByUser(null, userId, 1, null);
		return new DatatableResponse(total, datalist, total);
	}
	
	@RequestMapping(value = { "/getMailInfo" }, method = RequestMethod.GET, produces="application/json")
    public @ResponseBody AppResponse getMailInfo(HttpServletRequest request, HttpServletResponse response) {
		AppResponse appResponse = null;
		try {
			appResponse = new AppResponse(HttpServletResponse.SC_OK, "", mailService.getMailInfo());
		} catch (Exception e) {
			appResponse = new AppResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "");
			e.printStackTrace();
		}
		return appResponse;
    }
	
	@RequestMapping(value = { "/sendMail" }, method = RequestMethod.GET, produces="application/json")
    public @ResponseBody AppResponse sendMail(HttpServletRequest request, HttpServletResponse response,
    		@RequestParam(name="newSubject", required=false) String newSubject,
			@RequestParam(name="newContent", required=false) String newContent,
			@RequestParam(name="mailTo", required=false) String mailTo) {
		AppResponse appResponse = null;
		try {
			mailService.sendMail(newSubject, newContent, mailTo);
			appResponse = new AppResponse(HttpServletResponse.SC_OK, "");
		} catch (Exception e) {
			appResponse = new AppResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "");
			e.printStackTrace();
		}
		return appResponse;
    }
	
	@RequestMapping(value = { "/mailboxDetail" }, method = RequestMethod.GET, produces="application/json")
    public @ResponseBody AppResponse mailboxDetail(HttpServletRequest request, HttpServletResponse response,
    		@RequestParam(name="mailId", required=true) Long mailId) {
		AppResponse appResponse = null;
		try {
			Mail mail = mailService.findMailById(mailId);
			appResponse = new AppResponse(HttpServletResponse.SC_OK, "");
			appResponse.putData("targetMail", mail);
		} catch (Exception e) {
			appResponse = new AppResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "");
			e.printStackTrace();
		}
		return appResponse;
    }
	
	@RequestMapping(value = { "/deleteMail" }, method = RequestMethod.GET, produces="application/json")
    public @ResponseBody AppResponse deleteMail(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name="target", required=false) String target) {
		AppResponse appResponse = null;
		try {
			mailService.deleteMail(target);
			appResponse = new AppResponse(HttpServletResponse.SC_OK, "");
		} catch (Exception e) {
			appResponse = new AppResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "");
			e.printStackTrace();
		}
		return appResponse;
    }
	
	@RequestMapping(value = { "/trashMail" }, method = RequestMethod.GET, produces="application/json")
    public @ResponseBody AppResponse trashMail(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name="target", required=false) String target) {
		AppResponse appResponse = null;
		try {
			mailService.trashMail(target);
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
		List<Mail> datalist = mailService.listMailByUser(null, userId, 0, null, start, length);
		long total = mailService.countMailByUser(null, userId, 0, null);
		return new DatatableResponse(total, datalist, total);
	}
	
	@RequestMapping(value = { "/saveMail" }, method = RequestMethod.GET, produces="application/json")
    public @ResponseBody AppResponse saveMail(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name="target", required=false) String target) {
		AppResponse appResponse = null;
		try {
			mailService.saveMail(target);
			appResponse = new AppResponse(HttpServletResponse.SC_OK, "");
		} catch (Exception e) {
			appResponse = new AppResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "");
			e.printStackTrace();
		}
		return appResponse;
    }
	
	@RequestMapping(value="saved.json", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody DatatableResponse saved(
			@RequestParam(name="start", required=false, defaultValue="0") Integer start,
			@RequestParam(name="length", required=false, defaultValue="10") Integer length) {
		String userId = SecurityUtil.getSecurityUser().getUser().getId();
		List<Mail> datalist = mailService.listMailByUser(null, userId, 2, null, start, length);
		long total = mailService.countMailByUser(null, userId, 2, null);
		return new DatatableResponse(total, datalist, total);
	}
	
	@RequestMapping(value = { "/inboxMail" }, method = RequestMethod.GET, produces="application/json")
    public @ResponseBody AppResponse inboxMail(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name="target", required=false) String target) {
		AppResponse appResponse = null;
		try {
			mailService.inboxMail(target);
			appResponse = new AppResponse(HttpServletResponse.SC_OK, "");
		} catch (Exception e) {
			appResponse = new AppResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "");
			e.printStackTrace();
		}
		return appResponse;
    }
}
