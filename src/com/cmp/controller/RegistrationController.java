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
import org.springframework.web.bind.annotation.RequestParam;

import com.cmp.form.registration.EmailConfirmForm;
import com.cmp.form.registration.UserInfoForm;
import com.cmp.model.User;
import com.cmp.service.RegistrationService;
import com.cmp.service.vo.RegistrationUserVO;

@Controller
@RequestMapping(value="/registration")
public class RegistrationController {
	private static Log log = LogFactory.getLog(RegistrationController.class);
	@Autowired
	private RegistrationService registrationService;
	
	
	@RequestMapping(value = { "/email" }, method = RequestMethod.GET)
    public String email(@ModelAttribute("EmailConfirmForm") EmailConfirmForm form, HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("registration/email");
        return "registration/email";
    }
	
	@RequestMapping(value = { "/emailConfirm" }, method = RequestMethod.POST)
    public String emailConfirm(@ModelAttribute("EmailConfirmForm") EmailConfirmForm form, HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("registration/emailConfirm");
    	try {
    		String mailAddress = form.getMailAddress();
    		StringBuffer sb = request.getRequestURL();
    		String appName = request.getContextPath();
    		String url = sb.substring(0, sb.indexOf(appName)) +appName+ "/registration/user";
    		System.out.println("Confirm URL:"+url);
    		registrationService.initUser(mailAddress, url);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return "registration/email";
    }
	
	@RequestMapping(value = { "/user" }, params = "tokenId", method = RequestMethod.GET)
    public String user(@RequestParam("tokenId") String tokenId, Model model, @ModelAttribute("UserInfoForm") UserInfoForm form, HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("registration/user");
    	try {
    		User user = registrationService.verifyToken(tokenId);
    		if(null==user){
    			model.addAttribute("message", "verifyToken error");
    			return "registration/error";
    		}
    		form.setUserId(user.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "registration/user";
    }
	
	@RequestMapping(value = { "/userInfo" }, method = RequestMethod.GET)
    public String userInfo(@ModelAttribute("UserInfoForm") UserInfoForm form, HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("registration/userInfo");
    	try {
			registrationService.saveUserInfo(new RegistrationUserVO(
							form.getUserId()
			    			,form.getName()
			    			,form.getAccount()
			    			,form.getPassword()
			    			,form.getPhone()
			    			,form.getChannelUrl())
					);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "registration/question";
    }
}
