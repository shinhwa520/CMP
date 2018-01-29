package com.cmp.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmp.MenuItem;
import com.cmp.Response;
import com.cmp.form.registration.EmailConfirmForm;
import com.cmp.form.registration.UserInfoForm;
import com.cmp.model.User;
import com.cmp.service.RegistrationService;
import com.cmp.service.vo.RegistrationUserVO;

@Controller
@RequestMapping(value="/registration")
public class RegistrationController extends BaseController {
	private static Log log = LogFactory.getLog(RegistrationController.class);
	@Autowired
	private RegistrationService registrationService;
	
	/**
	 * login頁面按下[註冊]
	 * return email 表單提供輸入
	 */
	@RequestMapping(value = { "/email" }, method = RequestMethod.GET)
    public String email(Model model, @ModelAttribute("EmailConfirmForm") EmailConfirmForm form, HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("registration/email");
    	model.addAttribute("message", "");
        return "registration/email";
    }
	
	/**
	 * user 輸入email
	 * return confirm email訊息頁面
	 */
	@RequestMapping(value = { "/emailConfirm" }, method = RequestMethod.POST)
    public String emailConfirm(Model model, @ModelAttribute("EmailConfirmForm") EmailConfirmForm form, HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("registration/emailConfirm");
    	try {
    		String mailAddress = form.getMailAddress();
    		//驗證email是否重複
    		if(!registrationService.checkEmailAvailable(mailAddress)){
    			model.addAttribute("message", "Email已被使用，請重新輸入");
    			return "registration/email";
    		}
    		
    		StringBuffer sb = request.getRequestURL();
    		String appName = request.getContextPath();
    		String url = sb.substring(0, sb.indexOf(appName)) +appName+ "/registration/user";
    		System.out.println("Confirm URL:"+url);
    		registrationService.initUser(mailAddress, url);
    		
    		model.addAttribute("message", "please confirm email!");
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return "registration/msg";
    }
	
	/**
	 * user 認證信確認URL
	 * return user 表單提供輸入
	 */
	@RequestMapping(value = { "/user" }, params = "tokenId", method = RequestMethod.GET)
    public String user(Model model, @RequestParam("tokenId") String tokenId, @ModelAttribute("UserInfoForm") UserInfoForm form, HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("registration/user");
    	try {
    		User user = registrationService.verifyToken(tokenId);
    		if(null==user){
    			model.addAttribute("message", "verifyToken error");
    			return "registration/error";
    		}
    		model.addAttribute("message", "");
    		form.setUserId(user.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "registration/user";
    }
	
	/**
	 * user 輸入userInfo
	 * return 問題頁面
	 */
	@RequestMapping(value = { "/userInfo" }, method = RequestMethod.POST)
    public String userInfo(Model model, @ModelAttribute("UserInfoForm") UserInfoForm form, HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("registration/userInfo");
    	try {
    		if(!registrationService.findUserByAccount(form.getAccount()).isEmpty()){
    			model.addAttribute("message", "Account已被使用，請重新輸入");
    			return "registration/user";
    		}
    		
    		
			registrationService.saveUserInfo(new RegistrationUserVO(
							form.getUserId()
			    			,form.getName()
			    			,form.getAccount()
			    			,form.getPassword()
			    			,form.getPhone()
			    			,form.getChannelUrl())
					);
			RegistrationUserVO vo = registrationService.initQuestList();
			form.setQuesMap(vo.getQuesMap());
			form.setQuesMapkeySize(vo.getQuesMap().keySet().size());
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "registration/question";
    }
	
	
	/**
	 * user 輸入提問答案
	 * return login頁面
	 */
	@RequestMapping(value = { "/ans" }, method = RequestMethod.GET)
    public @ResponseBody String ans(Model model, @RequestParam("id") String id, @RequestParam("results") String results
    		, @ModelAttribute("UserInfoForm") UserInfoForm form, HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("registration/ans");
    	try {
    		registrationService.saveUserQues(id, results);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return jsonResponse(Response.REGISTRATION_SUCCESS);
    }
}
