package com.cmp.controller;

import java.util.ArrayList;
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
import com.cmp.security.SecurityUtil;
import com.cmp.service.CustService;
import com.cmp.service.FileService;
import com.cmp.service.vo.FileServiceVO;

@Controller
@RequestMapping(value="/channel/cust")
public class ChannelCustController extends BaseController {
	private static Log log = LogFactory.getLog(ChannelCustController.class);
	@Autowired
	private CustService custService;
	@Autowired
	private FileService fileService;
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String viewList(Model model) {
		setActiveMenu(model, MenuItem.MY_CUST);
		return "channel/cust";
	}
	
	/**
	 * 取得Customer資料 by userId
	 * @return DatatableResponse
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
	 * 取得Customer資料 by custId
	 * @return AppResponse
	 */
	@RequestMapping(value="getCustFileById", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody DatatableResponse getCustFileById(
			@RequestParam(name="custId", required=true) Integer custId,
			@RequestParam(name="start", required=false, defaultValue="0") Integer start,
			@RequestParam(name="length", required=false, defaultValue="10") Integer length) {
		
		System.out.println(">>> custId: " + custId);
		long total = 0;
		List<FileServiceVO> fileList = null;
		try {
			fileList = fileService.findCustomerFilesByCustId(custId, start, length);
			
			if (fileList != null && !fileList.isEmpty()) {
				 total = fileList.size();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		
		return new DatatableResponse(total, fileList, total);
	}
	
	@RequestMapping(value="create", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public AppResponse createCust(
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
			return new AppResponse(HttpServletResponse.SC_OK, "新增成功");
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
			@RequestParam(name="address", required=false) String address) {
		try {
			custService.updateCust(id, name, gender, validateDate(birthday), phone, email, weChat, city, address, null);
			return new AppResponse(HttpServletResponse.SC_OK, "更新成功");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			return new AppResponse(super.getLineNumber(), e.getMessage());
		}
	}
}
