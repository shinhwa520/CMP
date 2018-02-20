package com.cmp.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmp.AppResponse;
import com.cmp.MenuItem;
import com.cmp.dao.StatusDAO;
import com.cmp.dao.WebApiDetailDAO;
import com.cmp.form.registration.UserInfoForm;
import com.cmp.model.User;
import com.cmp.model.WebApiDetail;
import com.cmp.security.SecurityUtil;
import com.cmp.service.UserService;

@Controller
@RequestMapping(value="/channel/personalInfo")
public class ChannelPersonalInfoController extends BaseController {
	private static Log log = LogFactory.getLog(ChannelPersonalInfoController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private WebApiDetailDAO webApiDetailDAO;
	@Autowired
	private StatusDAO statusDAO;
	/**
	 * 取得 Personal Info.
	 * 
	 */
	@RequestMapping(value="/info", method = RequestMethod.GET, produces="application/json")
	public String getUserPersonalInfo(@ModelAttribute("UserInfoForm")UserInfoForm form, Model model) {
//		SecurityUser securityUser = SecurityUtil.getSecurityUser();
		String userId = SecurityUtil.getSecurityUser().getUser().getId();
		User user = userService.findUserById(userId);
		setUserInfoForm(user, form);
		setActiveMenu(model, MenuItem.PERSONAL_INFO);
		return "channel/personal_info";
	}
	
	@RequestMapping(value="update", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public AppResponse updateUserPersonalInfo(@ModelAttribute("UserInfoForm")UserInfoForm form, Model model) {
		try {
			String userId = SecurityUtil.getSecurityUser().getUser().getId();
			User user = userService.findUserById(userId);
			BeanUtils.copyProperties(form, user, "id", "status");
			userService.saveUserInfo(user);
			return new AppResponse(HttpServletResponse.SC_OK, "更新成功");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			return new AppResponse(super.getLineNumber(), e.getMessage());
		}
	}
	
	private void setUserInfoForm(User user, UserInfoForm form){
		form.setName(user.getName());
		form.setAccount(user.getAccount());
		form.setPassword(user.getPassword());
		form.setPhone(user.getPhone());
		WebApiDetail apiDetail = webApiDetailDAO.findWebApiDetailByUserId(user.getId());
		if(null == apiDetail)
			form.setChannelUrl("系統生成中...請稍候");
		else
			form.setChannelUrl(apiDetail.getParameterValues());
		form.setStatusName(user.getStatus().getName());
	}
}
