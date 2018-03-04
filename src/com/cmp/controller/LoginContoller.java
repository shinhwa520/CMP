package com.cmp.controller;


import java.security.Principal;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import com.cmp.MenuItem;
import com.cmp.form.IndexForm;
import com.cmp.service.BillboardService;
import com.cmp.service.vo.BillboardServiceVO;

@Controller
@RequestMapping("/")
public class LoginContoller extends BaseController {
	
	@Autowired
	BillboardService billboardService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model, Principal principal, @ModelAttribute("IndexForm") IndexForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (null == principal) {
				return "redirect:/login";
			}
			List<BillboardServiceVO> billboardList = billboardService.findAllBillboardRecords(false, 0, 500);
			form.setBillboardList(billboardList);
			setActiveMenu(model, MenuItem.INDEX);
//			changeLanguage(request, response, form.getLanguage(), cookieLanguage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "index";
	}
	
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String indexPage(Model model, Principal principal, @ModelAttribute("IndexForm") IndexForm form, HttpServletRequest request, HttpServletResponse response) {
		List<BillboardServiceVO> billboardList = billboardService.findAllBillboardRecords(false, 0, 500);
		form.setBillboardList(billboardList);
		setActiveMenu(model, MenuItem.INDEX);
		return "index";
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
