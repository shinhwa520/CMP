package com.cmp.controller;


import java.security.Principal;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cmp.MenuItem;
import com.cmp.service.BillboardService;

@Controller
@RequestMapping("/")
public class LoginContoller extends BaseController {
	
	@Autowired
	BillboardService billboardService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model, Principal principal, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (null == principal) {
				return "redirect:/login";
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/dashboard");
			rd.forward(request,response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String indexPage(Model model, Principal principal, HttpServletRequest request, HttpServletResponse response) {
    	try {
			if (null == principal) {
				return "redirect:/login";
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/dashboard");
			rd.forward(request,response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
    }
	
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "langType", defaultValue = "en_US") String langType, Locale locale, Principal principal, Model model) {
    	Locale _locale = LocaleContextHolder.getLocale();
//    	changeLang(request, response, model, langType);
    	return "login";
    }
    
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String loginOutPage(HttpServletRequest request, HttpServletResponse response, Model model, Principal principal) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		SecurityContextHolder.getContext().setAuthentication(null);

		return "redirect:/login";
    }

	@RequestMapping(value = "changeLanguage", method = RequestMethod.GET)
	public String changeLanguage(HttpServletRequest request,
                                 HttpServletResponse response,
                                 Model model,
                                 @RequestParam(value = "langType", defaultValue = "en_US") String langType,
                                 @RequestParam(value = "refresh", defaultValue = "index") String refreshPage) {
		
		changeLang(request, response, model, langType);
		String appName = request.getContextPath();
		refreshPage = refreshPage.substring(appName.length());
		
		return "redirect:"+refreshPage;
	}
}
