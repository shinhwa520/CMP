package com.cmp.controller;

import java.security.Principal;

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
    

}
