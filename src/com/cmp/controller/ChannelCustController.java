package com.cmp.controller;

import java.util.ArrayList;
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
import org.springframework.web.servlet.support.RequestContext;

import com.cmp.AppResponse;
import com.cmp.DatatableResponse;
import com.cmp.MenuItem;
import com.cmp.model.Customer;
import com.cmp.model.User;
import com.cmp.security.SecurityUtil;
import com.cmp.service.CustService;
import com.cmp.service.FileService;
import com.cmp.service.vo.CustServiceVO;
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
		User user = SecurityUtil.getSecurityUser().getUser();
		List<Customer> datalist;
		long total;
		String roleName = "MA";
		if(StringUtils.equals(roleName, user.getRole().getName())){
			datalist = custService.findCust4MA(roleName, start, length);
			total = custService.countCust4MA(roleName);
		}else{
			String userId = SecurityUtil.getSecurityUser().getUser().getId();
			datalist = custService.findCustByUserId(userId, start, length);
			total = custService.countCustByUserId(userId);
		}

		return new DatatableResponse(total, datalist, total);
	}
	
	@RequestMapping(value="getCustAndVisitByUserId.json", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody DatatableResponse getCustAndVisitByUserId(
			@RequestParam(name="start", required=false, defaultValue="0") Integer start,
			@RequestParam(name="length", required=false, defaultValue="25") Integer length,
			@RequestParam(name="visitId", required=true) Integer visitId) {
		
		String userId = SecurityUtil.getSecurityUser().getUser().getId();
		List<CustServiceVO> custList = custService.findCustAndVisitByUserId(visitId, userId, start, length);
		
		long total = custService.countCustByUserId(userId);
		return new DatatableResponse(total, custList, total);
	}
	
	@RequestMapping(value="getCustAndSalonByUserId.json", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody DatatableResponse getCustAndSalonByUserId(
			@RequestParam(name="start", required=false, defaultValue="0") Integer start,
			@RequestParam(name="length", required=false, defaultValue="25") Integer length,
			@RequestParam(name="salonId", required=true) Integer salonId) {
		
		String userId = SecurityUtil.getSecurityUser().getUser().getId();
		List<CustServiceVO> custList = custService.findCustAndSalonByUserId(salonId, userId, start, length);
		
		long total = custService.countCustByUserId(userId);
		return new DatatableResponse(total, custList, total);
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
		
		long count = 0;
		List<FileServiceVO> fileList = new ArrayList<FileServiceVO>();
		
		try {
			count = fileService.countCustomerFilesByCustId(custId);
			
			if (count > 0) {
				fileList = fileService.findCustomerFilesByCustId(custId, start, length);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		
		return new DatatableResponse(count, fileList, count);
	}
	
	@RequestMapping(value="create", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public AppResponse createCust(
			@RequestParam(name="cust_name", required=true) String name,
			@RequestParam(name="gender", required=false) String gender,
			@RequestParam(name="birthday", required=false) String birthday,
			@RequestParam(name="phone", required=false) String phone,
			@RequestParam(name="email", required=false) String email,
			@RequestParam(name="weChat", required=false) String weChat,
			@RequestParam(name="identity1_id", required=true) Integer identity1_id,
			@RequestParam(name="identity1_code", required=false) String identity1_code,
			@RequestParam(name="identity1_name", required=false) String identity1_name,
			@RequestParam(name="identity2_id", required=true) Integer identity2_id,
			@RequestParam(name="identity2_code", required=false) String identity2_code,
			@RequestParam(name="identity2_name", required=false) String identity2_name,
			@RequestParam(name="city", required=false) String city,
			@RequestParam(name="census", required=false) String census,
			@RequestParam(name="address", required=false) String address,
			@RequestParam(name="remark", required=false) String remark) {
		try {
			custService.createCust(name, gender, validateDate(birthday), phone, email, weChat
					, identity1_id, identity1_code, identity1_name, identity2_id, identity2_code, identity2_name
					, city, census, address, remark);
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

			@RequestParam(name="identity1_id", required=true) Integer identity1_id,
			@RequestParam(name="identity1_code", required=false) String identity1_code,
			@RequestParam(name="identity1_name", required=false) String identity1_name,
			@RequestParam(name="identity2_id", required=true) Integer identity2_id,
			@RequestParam(name="identity2_code", required=false) String identity2_code,
			@RequestParam(name="identity2_name", required=false) String identity2_name,

			@RequestParam(name="city", required=false) String city,
			@RequestParam(name="census", required=false) String census,
			@RequestParam(name="address", required=false) String address,
			@RequestParam(name="remark", required=false) String remark,
            HttpServletRequest request) {
		try {
			RequestContext req = new RequestContext(request);
			custService.updateCust(id, name, gender, validateDate(birthday), phone, email, weChat
					, identity1_id, identity1_code, identity1_name, identity2_id, identity2_code, identity2_name
					, city, census, address, remark, null);
            return new AppResponse(HttpServletResponse.SC_OK, req.getMessage("success.update"));//更新成功
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			return new AppResponse(super.getLineNumber(), e.getMessage());
		}
	}
}
