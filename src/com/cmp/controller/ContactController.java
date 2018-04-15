package com.cmp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmp.AppResponse;
import com.cmp.service.ContactService;

@Controller
@RequestMapping(value="/contact")
public class ContactController extends BaseController {
	private static Log log = LogFactory.getLog(MailController.class);
	@Autowired
	private ContactService contactService;
	
	@RequestMapping(value = { "/contactUs" }, method = RequestMethod.GET, produces="application/json")
    public @ResponseBody AppResponse contactUs(HttpServletRequest request, HttpServletResponse response,
    		@RequestParam(name="subject", required=false) String subject,
			@RequestParam(name="content", required=false) String content) {
		AppResponse appResponse = null;
		try {
			contactService.contactUs(subject, content);
			appResponse = new AppResponse(HttpServletResponse.SC_OK, "");
		} catch (Exception e) {
			appResponse = new AppResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "");
			e.printStackTrace();
		}
		return appResponse;
    }
}
