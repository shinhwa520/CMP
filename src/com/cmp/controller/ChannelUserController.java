package com.cmp.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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
import com.cmp.security.SecurityUtil;
import com.cmp.service.CustService;
import com.cmp.service.UserService;
import org.springframework.web.servlet.support.RequestContext;

@Controller
@RequestMapping(value="/channel/user")
public class ChannelUserController extends BaseController {
	private static Log log = LogFactory.getLog(ChannelUserController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private CustService custService;
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String viewList(Model model) {
		setActiveMenu(model, MenuItem.MY_USER);
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
			Principal principal, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name="start", required=false, defaultValue="0") Integer start,
			@RequestParam(name="length", required=false, defaultValue="10") Integer length) {
		User user = SecurityUtil.getSecurityUser().getUser();
		List<User> datalist;
		long total;
		String roleName = "MA";
		String yearMonth = sdfYearMonth.format(new Date());
		if(StringUtils.equals(roleName, user.getRole().getName())){
			datalist = userService.findUser4MA(roleName, yearMonth, start, length);
			total = userService.countUser4MA(roleName);
		}else{
			String userId = user.getId();
			datalist = userService.findUserByChannelId(userId, yearMonth, start, length);
			total = userService.countUserByChannelId(userId);
		}
		return new DatatableResponse(total, datalist, total);
	}
	
	@RequestMapping(value = "cust", method = RequestMethod.GET)
	public String viewUserCust(@RequestParam(name="userId", required=true, defaultValue="0") String userId, Model model) {
		setActiveMenu(model, MenuItem.MY_USER);
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
	
	@RequestMapping(value="updateKpi", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public AppResponse updateKpi(
			@RequestParam(name="remark", required=false) String remark,
			@RequestParam(name="user_id", required=true) String userId,
			@RequestParam(name="agent_user", required=true) int agent_user,
			@RequestParam(name="agent_cust", required=true) int agent_cust,
			@RequestParam(name="volume", required=true) int volume,
			HttpServletRequest request) {
		try {
			RequestContext req = new RequestContext(request);
			Date current = new Date();
			userService.updateKpi(userId, sdfYearMonth.format(new Date()), agent_user, agent_cust, volume, current, remark);
			return new AppResponse(HttpServletResponse.SC_OK, req.getMessage("success.update"));//更新成功
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			return new AppResponse(super.getLineNumber(), e.getMessage());
		}
	}
}
