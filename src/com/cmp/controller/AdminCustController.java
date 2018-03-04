package com.cmp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import com.cmp.security.SecurityUser;
import com.cmp.security.SecurityUtil;
import com.cmp.service.CustService;
import org.springframework.web.servlet.support.RequestContext;

@Controller
@RequestMapping(value="/admin/cust")
public class AdminCustController extends BaseController {
	private static Log log = LogFactory.getLog(AdminCustController.class);
	@Autowired
	private CustService custService;
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String viewList(Model model) {
		setActiveMenu(model, MenuItem.ADMIN_CUST);
		return "admin/cust";
	}
	
	/**
	 * 取得Cust資料
	 * 
	 * @param start
	 * @param length
	 * @return DatatableResponse
	 */
	@RequestMapping(value="getCust4Admin.json", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody DatatableResponse getCust4Admin(
			@RequestParam(name="start", required=false, defaultValue="0") Integer start,
			@RequestParam(name="length", required=false, defaultValue="10") Integer length) {
//		SecurityUser securityUser = SecurityUtil.getSecurityUser();
//		System.out.println(securityUser.getUser().getId());
		List<Customer> datalist = custService.findCustByUserId(null, start, length);
		long total = custService.countCustByUserId(null);
		return new DatatableResponse(total, datalist, total);
	}

	
	/**
	 * 取得Customer資料 by custId
	 * @return AppResponse
	 */
	@RequestMapping(value="getCustById/{custId}", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody AppResponse getCustById(@PathVariable Integer custId) {
		try {
			Customer cust = custService.findCustById(custId);
			AppResponse appResponse = new AppResponse(HttpServletResponse.SC_OK, "取得客戶資料成功");
			appResponse.putData("cust",  cust);
			return appResponse;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			return new AppResponse(super.getLineNumber(), e.getMessage());
		}
	}
	
	@RequestMapping(value="update", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public AppResponse updateCust(
			@RequestParam(name="cust_id", required=true) Integer id,
			@RequestParam(name="cust_name", required=true) String name,
			@RequestParam(name="gender", required=false) String gender,
			@RequestParam(name="birthday", required=false) String birthday,
			@RequestParam(name="phone", required=false) String phone,
			@RequestParam(name="email", required=false) String email,
			@RequestParam(name="weChat", required=false) String weChat,
			@RequestParam(name="city", required=false) String city,
			@RequestParam(name="address", required=false) String address,
			@RequestParam(name="status", required=true) String status,
			HttpServletRequest request) {
		try {
			RequestContext req = new RequestContext(request);
			custService.updateCust(id, name, gender, validateDate(birthday), phone, email, weChat, city, address, status);
			return new AppResponse(HttpServletResponse.SC_OK, req.getMessage("success.update"));//更新成功
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			return new AppResponse(super.getLineNumber(), e.getMessage());
		}
	}
}
