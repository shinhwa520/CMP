package com.cmp.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmp.DatatableResponse;
import com.cmp.MenuItem;
import com.cmp.model.Customer;
import com.cmp.model.User;
import com.cmp.security.SecurityUtil;
import com.cmp.service.CustService;
import com.cmp.service.UserService;

@Controller
@RequestMapping(value="/channel_user")
public class ChannelUserController extends BaseController {
	private static Log log = LogFactory.getLog(ChannelUserController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private CustService custService;
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String viewList(Model model) {
		setActiveMenu(model, MenuItem.USER_LIST);
		return "channel/user";
	}
	
	/**
	 * 取得Cust資料
	 * 
	 * @param start
	 * @param length
	 * @return DatatableResponse
	 */
	@RequestMapping(value="getUserByChannelId.json", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody DatatableResponse getCust4Admin(
			@RequestParam(name="start", required=false, defaultValue="0") Integer start,
			@RequestParam(name="length", required=false, defaultValue="10") Integer length) {
//		SecurityUser securityUser = SecurityUtil.getSecurityUser();
		String userId = SecurityUtil.getSecurityUser().getUser().getId();
		List<User> datalist = userService.findUserByChannelId(userId, start, length);
		long total = userService.countUserByChannelId(userId);
		return new DatatableResponse(total, datalist, total);
	}
	
	
	@RequestMapping(value = "cust", method = RequestMethod.GET)
	public String viewUserCust(@RequestParam(name="userId", required=true, defaultValue="0") String userId, Model model) {
		setActiveMenu(model, MenuItem.USER_LIST);
		model.addAttribute("userId", userId);
		return "channel/user_cust";
	}
	
	@RequestMapping(value="getCustByUserId.json", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody DatatableResponse getCustByUserId(
			@RequestParam(name="userId", required=true, defaultValue="0") String userId,
			@RequestParam(name="start", required=false, defaultValue="0") Integer start,
			@RequestParam(name="length", required=false, defaultValue="10") Integer length) {
//		SecurityUser securityUser = SecurityUtil.getSecurityUser();
//		String userId = SecurityUtil.getSecurityUser().getUser().getId();
		System.out.println("getCustByUserId [userId]:" + userId);
		List<Customer> datalist = custService.findCustByUserId(userId, start, length);
		long total = custService.countCustByUserId(userId);
		return new DatatableResponse(total, datalist, total);
	}
}
