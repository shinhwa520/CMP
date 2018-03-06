package com.cmp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cmp.model.User;
import com.cmp.service.UserService;

@Controller
@RequestMapping("/outsite")
public class OutsiteController extends BaseController {
	private static Log log = LogFactory.getLog(OutsiteController.class);
	@Autowired
	private UserService userService;

	@RequestMapping(value = "viewCustInfo", method = RequestMethod.GET)
	public String viewCustPublicInfo(Model model, HttpServletRequest request, HttpServletResponse response) {
		try {
			String referrer = request.getHeader("referer");
			
			if (StringUtils.isNotBlank(referrer)) {
				String makaId = (referrer.lastIndexOf("/") != -1) 
									? referrer.substring(referrer.lastIndexOf("/")+1, (referrer.lastIndexOf("?") != -1 ? referrer.lastIndexOf("?") : referrer.length())) 
									: null;
									
				if (StringUtils.isNotBlank(makaId)) {
					User user = userService.findUserByApiId(makaId);
					
					if (user != null) {
						model.addAttribute("userName", user.getName());
						model.addAttribute("userEmail", user.getEmail());
						model.addAttribute("userWeChat", user.getWeChat());
						model.addAttribute("userPhone", user.getPhone());
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "outsite/contact_info";
	}
}
