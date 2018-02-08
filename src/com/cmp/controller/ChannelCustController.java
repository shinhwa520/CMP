package com.cmp.controller;

import java.util.List;
import java.util.Map;

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
import com.cmp.Response;
import com.cmp.model.Customer;
import com.cmp.security.SecurityUtil;
import com.cmp.service.CustService;

@Controller
@RequestMapping(value="/channel/cust")
public class ChannelCustController extends BaseController {
	private static Log log = LogFactory.getLog(ChannelCustController.class);
	@Autowired
	private CustService custService;
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String viewList(Model model) {
		setActiveMenu(model, MenuItem.MY_CUST);
		return "channel/cust";
	}
	
	/**
	 * 
	 * 取得轉檔原始檔資料
	 * 
	 * @param start
	 * @param length
	 * @return
	 */
	@RequestMapping(value="getCustByUserId.json", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody DatatableResponse getCustByUserId(
			@RequestParam(name="start", required=false, defaultValue="0") Integer start,
			@RequestParam(name="length", required=false, defaultValue="10") Integer length) {
//		SecurityUser securityUser = SecurityUtil.getSecurityUser();
		String userId = SecurityUtil.getSecurityUser().getUser().getId();
		System.out.println("getCustByUserId [userId]:" + userId);
		List<Customer> datalist = custService.findCustByUserId(userId, start, length);
		long total = custService.countCustByUserId(userId);
		return new DatatableResponse(total, datalist, total);
	}
	
	@RequestMapping(value="create", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public Map<Object, Object> createCust(
			@RequestParam(name="cust_name", required=true) String name,
			@RequestParam(name="gender", required=true) String gender,
			@RequestParam(name="birthday", required=false) String birthday,
			@RequestParam(name="phone", required=false) String phone,
			@RequestParam(name="email", required=false) String email,
			@RequestParam(name="weChat", required=false) String weChat,
			@RequestParam(name="city", required=false) String city,
			@RequestParam(name="address", required=false) String address) {
		try {
			custService.createCust(name, gender, birthday, phone, email, weChat, city, address);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			return super.apiError(super.getLineNumber(), e.getMessage());
		}
		//accountLogService.insertAccountLog(AccountLogService.LOG_NODE_INSERT, "");
		return super.apiSuccess(200, "新增成功");
	}
	
//	@RequestMapping(value="update", method = RequestMethod.POST, produces="application/json")
//	@ResponseBody
//	public String updateCust(
//			@RequestParam(name="para_id", required=true) Integer id,
//			@RequestParam(name="para_name", required=true) String name,
//			@RequestParam(name="para_description", required=true) String description,
//			@RequestParam(name="para_class", required=true) String parameterClass,
//			@RequestParam(name="para_type", required=true) String type,
//			@RequestParam(name="para_min", required=true) String min,
//			@RequestParam(name="para_max", required=true) String max,
//			@RequestParam(name="para_sort", required=true) String sort) {
//		Map<Object, Object> result = custService.updateCust(id, name, description, parameterClass, type, min, max, sort);
//		//accountLogService.insertAccountLog(AccountLogService.LOG_NODE_UPDATE, "");
//		return result;
//	}
}
