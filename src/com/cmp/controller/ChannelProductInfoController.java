package com.cmp.controller;

import java.util.Date;

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

import com.cmp.MenuItem;
import com.cmp.form.ProductForm;
import com.cmp.model.User;
import com.cmp.security.SecurityUtil;
import com.cmp.service.UserService;

@Controller
@RequestMapping("/channel/productInfo")
public class ChannelProductInfoController extends BaseController {
	private static Log log = LogFactory.getLog(ChannelProductInfoController.class);
	@Autowired
	private UserService userService;

	@RequestMapping(value = { "list" }, method = RequestMethod.GET)
    public String fileMain(Model model, HttpServletRequest request, HttpServletResponse response) {
		setActiveMenu(model, MenuItem.PRODUCT_INFO);
		return "product/list";
    }
	
	/*
	@RequestMapping(value="getProductsData.json", method = RequestMethod.POST, produces="application/json")
	public @ResponseBody String getProductsData() {
		JSONObject jsonObj = new JSONObject();
		JSONArray array = new JSONArray();
		
		try {
			System.out.println("getProductsData.json");
			System.out.println("Do something...");
			
			List<String> test = new ArrayList<String>(
									Arrays.asList("<div class='slide_inner'><a class='photo_link' href='#'><img class='photo' src='http://localhost:8080/CMP/resources/product_2018_0001.jpg' alt='魅力大馬'></a>魅力大馬</div>"
										   ,"<div class='slide_inner'><a class='photo_link' href='#'><img class='photo' src='http://localhost:8080/CMP/resources/product_2018_0001.jpg' alt='魅力大馬'></a>魅力大馬</div>"
										   ,"<div class='slide_inner'><a class='photo_link' href='#'><img class='photo' src='http://localhost:8080/CMP/resources/product_2018_0001.jpg' alt='魅力大馬'></a>魅力大馬</div>"
									)
								);
			String str = "<div class='slide_inner'><a class='photo_link' href='#'><img class='photo' src='http://localhost:8080/CMP/resources/product_2018_0001.jpg' alt='魅力大馬'></a>魅力大馬</div>";
			
			jsonObj = new JSONObject();
			jsonObj.put("content", new String(str.getBytes("iso-8859-1"), "utf-8"));
			array.add(jsonObj);
			
			jsonObj = new JSONObject();
			jsonObj.put("content", new String(str.getBytes("iso-8859-1"), "utf-8"));
			array.add(jsonObj);
			
			jsonObj = new JSONObject();
			jsonObj.put("content", new String(str.getBytes("iso-8859-1"), "utf-8"));
			array.add(jsonObj);
			
			System.out.println(array.toJSONString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return array.toJSONString();
	}
	*/
	
	@RequestMapping(value="viewKPI/{userId}", method = RequestMethod.GET)
	public String viewKPI(Model model, @ModelAttribute("ProductForm") ProductForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String userId = SecurityUtil.getSecurityUser().getUser().getId();
			User user = userService.findUserAndKpiById(userId, sdfYearMonth.format(new Date()));
			
			if (user != null) {
				model.addAttribute("expectedAgentUser", user.getAgent_user());
				model.addAttribute("actualAgentUser", user.get_agent_user());
				model.addAttribute("expectedAgentCust", user.getAgent_cust());
				model.addAttribute("actualAgentCust", user.get_agent_cust());
				model.addAttribute("expectedAgentVolume", user.getVolume());
				model.addAttribute("actualAgentVolume", user.get_volume());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		
		return "product/personal_kpi";
	}
}
