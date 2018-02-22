package com.cmp.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmp.AppResponse;
import com.cmp.DatatableResponse;
import com.cmp.MenuItem;
import com.cmp.model.Customer;
import com.cmp.model.User;
import com.cmp.service.CustService;
import com.cmp.service.UserService;

@Controller
@RequestMapping(value="/admin/user")
public class AdminUserController extends BaseController {
	private static Log log = LogFactory.getLog(AdminUserController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private CustService custService;
	
	/**
	 * 導向渠道商列表
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String viewList(Model model) {
		setActiveMenu(model, MenuItem.ADMIN_USER);
		return "admin/user";
	}
	
	/**
	 * 取得該渠道商列表
	 * 
	 * @param start
	 * @param length
	 * @return DatatableResponse
	 */
	@RequestMapping(value="getUser4Admin.json", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody DatatableResponse getCust4Admin(
			@RequestParam(name="start", required=false, defaultValue="0") Integer start,
			@RequestParam(name="length", required=false, defaultValue="10") Integer length) {
//		SecurityUser securityUser = SecurityUtil.getSecurityUser();
//		String userId = SecurityUtil.getSecurityUser().getUser().getId();
		List<User> datalist = userService.findUserByChannelId(null, sdfYearMonth.format(new Date()), start, length);
		long total = userService.countUserByChannelId(null);
		return new DatatableResponse(total, datalist, total);
	}

	/**
	 * 導向該渠道商之客戶列表
	 */
	@RequestMapping(value = "cust", method = RequestMethod.GET)
	public String viewUserCust(@RequestParam(name="userId", required=true, defaultValue="0") String userId, Model model) {
		setActiveMenu(model, MenuItem.ADMIN_USER);
		model.addAttribute("userId", userId);
		return "admin/user_cust";
	}
	
	/**
	 * 取得該渠道商之客戶列表
	 * 
	 * @param start
	 * @param length
	 * @return DatatableResponse
	 */
	@RequestMapping(value="getCustByUserId.json", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody DatatableResponse getCustByUserId(
			@RequestParam(name="userId", required=true, defaultValue="0") String userId,
			@RequestParam(name="start", required=false, defaultValue="0") Integer start,
			@RequestParam(name="length", required=false, defaultValue="10") Integer length) {
		System.out.println("getCustByUserId [userId]:" + userId);
		List<Customer> datalist = custService.findCustByUserId(userId, start, length);
		long total = custService.countCustByUserId(userId);
		return new DatatableResponse(total, datalist, total);
	}
	
	/**
	 * 取得User資料 by userId
	 * @return AppResponse
	 */
	@RequestMapping(value="getUserById/{userId}", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody AppResponse getUserById(@PathVariable String userId) {
		try {
			User user = userService.findUserAndKpiById(userId, sdfYearMonth.format(new Date()));
			AppResponse appResponse = new AppResponse(HttpServletResponse.SC_OK, "取得渠道商資料成功");
			appResponse.putData("user",  user);
			return appResponse;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			return new AppResponse(super.getLineNumber(), e.getMessage());
		}
	}
	
	@RequestMapping(value="update", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public AppResponse update(
			@RequestParam(name="user_id", required=true) String userId,
			@RequestParam(name="user_name", required=true) String userName,
			@RequestParam(name="phone", required=true) String phone,
			@RequestParam(name="email", required=true) String email,
			@RequestParam(name="status", required=true) String status) {
		try {
			userService.update(userId, userName, phone, email, status);
			return new AppResponse(HttpServletResponse.SC_OK, "更新成功");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			return new AppResponse(super.getLineNumber(), e.getMessage());
		}
	}
}
