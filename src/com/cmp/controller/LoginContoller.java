package com.cmp.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cmp.MenuItem;

@Controller
@RequestMapping("/")
public class LoginContoller extends BaseController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model, Principal principal) {
		model.addAttribute("message", "You are logged in as " + principal.getName());
		setActiveMenu(model, MenuItem.CUST_LIST);
		return "index";
	}
	
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model, Principal principal) {
    	return "login";
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String loginOutPage(HttpServletRequest request, HttpServletResponse response, Model model, Principal principal) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		SecurityContextHolder.getContext().setAuthentication(null);

		return "redirect:/login";
    }
}
