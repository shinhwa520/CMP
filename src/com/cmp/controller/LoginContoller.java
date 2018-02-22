package com.cmp.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cmp.MenuItem;
import com.cmp.form.IndexForm;
import com.cmp.service.BillboardService;
import com.cmp.service.vo.BillboardServiceVO;

@Controller
@RequestMapping("/")
public class LoginContoller extends BaseController {
	
	BillboardService billboardService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model, Principal principal, @ModelAttribute("IndexForm") IndexForm form) {
		List<BillboardServiceVO> billboardList;
		try {
			if (null == principal) {
				return "redirect:/login";
			}
			System.out.println(principal.getName());
			
			billboardList = billboardService.findAllBillboardRecords(false, 0, 500);
			form.setBillboardList(billboardList);
			setActiveMenu(model, MenuItem.INDEX);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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

    @Autowired
	public void setBillboardService(BillboardService billboardService) {
		this.billboardService = billboardService;
	}
}
