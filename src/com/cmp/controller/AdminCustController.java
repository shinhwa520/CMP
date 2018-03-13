package com.cmp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmp.model.Status;
import com.cmp.service.StatusService;
import com.cmp.service.vo.CustServiceVO;
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
import org.springframework.web.servlet.support.RequestContext;

import com.cmp.AppResponse;
import com.cmp.DatatableResponse;
import com.cmp.MenuItem;
import com.cmp.model.Customer;
import com.cmp.service.CustService;

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
		List<Customer> datalist = custService.findCustByUserId(null, start, length);
		long total = custService.countCustByUserId(null);
		return new DatatableResponse(total, datalist, total);
	}

	/**
	 * Search Cust資料
	 *
	 * @param start
	 * @param length
	 * @return DatatableResponse
	 */
	@RequestMapping(value="searchCust4Admin.json", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody DatatableResponse searchCust4Admin(
			@RequestParam(name="keyword", required=false, defaultValue="") String keyword,
			@RequestParam(name="start", required=false, defaultValue="0") Integer start,
			@RequestParam(name="length", required=false, defaultValue="10") Integer length) {
//		SecurityUser securityUser = SecurityUtil.getSecurityUser();
		List<Customer> datalist = custService.findCust4Search(keyword, start, length);
		long total = custService.countCust4Search(keyword);
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

	/**
	 * 更新Customer資料 by custId
	 * @return AppResponse
	 */
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

			@RequestParam(name="identity1_id", required=false) Integer identity1_id,
			@RequestParam(name="identity1_code", required=false) String identity1_code,
			@RequestParam(name="identity1_name", required=false) String identity1_name,
			@RequestParam(name="identity2_id", required=false) Integer identity2_id,
			@RequestParam(name="identity2_code", required=false) String identity2_code,
			@RequestParam(name="identity2_name", required=false) String identity2_name,

			@RequestParam(name="city", required=false) String city,
			@RequestParam(name="census", required=false) String census,
			@RequestParam(name="address", required=false) String address,
			@RequestParam(name="remark", required=false) String remark,
			@RequestParam(name="status", required=true) String status,
			HttpServletRequest request) {
		try {
			custService.updateCust(id, name, gender, validateDate(birthday), phone, email, weChat
					, identity1_id, identity1_code, identity1_name, identity2_id, identity2_code, identity2_name
					, city, census, address, remark, status);
			RequestContext req = new RequestContext(request);
			return new AppResponse(HttpServletResponse.SC_OK, req.getMessage("success.update"));//更新成功
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			return new AppResponse(super.getLineNumber(), e.getMessage());
		}
	}

	/**
	 * 删除Cust
	 *
	 * @param custId
	 * @return DatatableResponse
	 */
	@RequestMapping(value="deleteCustAjax", method = RequestMethod.POST, produces="application/json")
	public @ResponseBody AppResponse deleteCust(
			@RequestParam(name="custId", required=true) Integer custId,
			HttpServletRequest request) {
		try {
			RequestContext req = new RequestContext(request);

			CustServiceVO custServiceVO = new CustServiceVO();
			custServiceVO.setCust_Id(custId);

			custService.deleteCust(custServiceVO);

			return new AppResponse(HttpServletResponse.SC_OK, req.getMessage("success.update"));
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			return new AppResponse(super.getLineNumber(), e.getMessage());
		}
	}
}
