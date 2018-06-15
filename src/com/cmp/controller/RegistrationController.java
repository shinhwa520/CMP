package com.cmp.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;

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
import org.springframework.web.servlet.support.RequestContext;

import com.cmp.AppResponse;
import com.cmp.form.registration.UserInfoForm;
import com.cmp.model.User;
import com.cmp.service.RegistrationService;
import com.cmp.service.vo.RegistrationUserVO;

@Controller
@RequestMapping(value="/registration")
public class RegistrationController extends BaseController {
	@SuppressWarnings("unused")
	private static Log log = LogFactory.getLog(RegistrationController.class);
	@Autowired
	private RegistrationService registrationService;
    
	/**
	 * login頁面按下[註冊]
	 * return process 頁面
	 */
	@RequestMapping(value = { "/process" }, method = RequestMethod.GET)
    public String process(@ModelAttribute("UserInfoForm") UserInfoForm form, HttpServletRequest request, HttpServletResponse response) {
        return "registration/process";
    }
	
	/**
	 * user提交[邮箱]
	 * 寄發 驗證碼
	 */
	@RequestMapping(value = { "/emailConfirm" }, method = RequestMethod.GET)
    public @ResponseBody AppResponse emailConfirm(HttpServletRequest request, HttpServletResponse response
    		, @RequestParam(name="emailAddress", required=true) String emailAddress) {
		AppResponse appResponse = null;
		RequestContext req = new RequestContext(request);
		try {
			User checkUser = registrationService.checkEmailAvailable(emailAddress);
    		//驗證email是否重複	//null =>可使用
    		if(null!=checkUser && 6<=checkUser.getStatus().getSort()){
    			appResponse = new AppResponse(HttpServletResponse.SC_BAD_REQUEST, req.getMessage("error.emailExist"));
    			return appResponse;
    		}
    		
    		StringBuffer sb = request.getRequestURL();
    		String appName = request.getContextPath();
    		String url = sb.substring(0, sb.indexOf(appName)) +appName+ "/registration/user";
    		User user = registrationService.initUser(emailAddress, url, checkUser);
			appResponse = new AppResponse(HttpServletResponse.SC_OK, "");
			appResponse.putData("userId",  user.getId());
			
		} catch (Exception e) {
			appResponse = new AppResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "");
			e.printStackTrace();
		}
		return appResponse;
    }
	
	/**
	 * [重新获取验证码]
	 */
	@RequestMapping(value = { "/reGenToken" }, method = RequestMethod.GET)
    public @ResponseBody AppResponse reGenToken(@RequestParam(name="userId", required=true) String userId, HttpServletRequest request, HttpServletResponse response) {
		AppResponse appResponse = null;
		try {
			registrationService.reGenToken(userId);
			appResponse = new AppResponse(HttpServletResponse.SC_OK, "");
		} catch (Exception e) {
			appResponse = new AppResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "");
			e.printStackTrace();
		}
        return appResponse;
    }
	
	/**
	 * user提交[验证码]
	 * return userInfo 表單
	 */
	@RequestMapping(value = { "/tokenConfirm" }, method = RequestMethod.GET)
    public @ResponseBody AppResponse tokenConfirm(HttpServletRequest request, HttpServletResponse response
    		, @RequestParam(name="userId", required=true) String userId
    		, @RequestParam(name="verificationCode", required=true) String verificationCode) {
		AppResponse appResponse = null;
		RequestContext req = new RequestContext(request);
		try {
    		User user = registrationService.verifyToken(userId, verificationCode);

    		if(null==user){
    			appResponse = new AppResponse(HttpServletResponse.SC_BAD_REQUEST, req.getMessage("error.verifyFail"));
    			return appResponse;
    		}
			appResponse = new AppResponse(HttpServletResponse.SC_OK, "");
			appResponse.putData("userId",  user.getId());
		} catch (Exception e) {
			appResponse = new AppResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "");
			e.printStackTrace();
		}
		return appResponse;
    }
	
	/**
	 * user提交[userInfo 表單]
	 * return 問題頁面
	 */
	@RequestMapping(value = { "/userInfoConfirm" }, method = RequestMethod.GET)
    public @ResponseBody AppResponse userInfoConfirm(HttpServletRequest request, HttpServletResponse response
    		, @RequestParam(name="userId", required=true) String userId
    		, @RequestParam(name="account", required=true) String account
    		, @RequestParam(name="password", required=true) String password
    		, @RequestParam(name="userName", required=true) String userName
    		, @RequestParam(name="phone", required=true) String phone
    		, @RequestParam(name="weChat", required=true) String weChat
    		, @RequestParam(name="channelAccount", required=true) String channelAccount) {
		AppResponse appResponse = null;
		RequestContext req = new RequestContext(request);
		try {
    		if(!registrationService.findUserByAccount(account).isEmpty()){
    			return new AppResponse(HttpServletResponse.SC_BAD_REQUEST, req.getMessage("error.accountExist"));
    		}
    		if(!registrationService.upstream(userId, channelAccount)){
    			return new AppResponse(HttpServletResponse.SC_BAD_REQUEST, req.getMessage("error.noDataAutoChannel"));
			}
			registrationService.saveUserInfo(new RegistrationUserVO(
							userId
			    			,userName
			    			,account
			    			,password
			    			,phone
			    			,weChat
			    			,registrationService.findUserByAccount(channelAccount).get(0))
					);
			
			RegistrationUserVO vo = registrationService.initQuestList();
			appResponse = new AppResponse(HttpServletResponse.SC_OK, "");
			appResponse.putData("quesMap",  vo.getQuesMap());
			appResponse.putData("ans",  vo.getAns());
			appResponse.putData("quesMapKeySize",  vo.getQuesMap().keySet().size());
		} catch (Exception e) {
			appResponse = new AppResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "");
			e.printStackTrace();
		}
		return appResponse;
    }
	
	/**
	 * user 正確回答問題
	 * return 合同頁面
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = { "/getAgreement" }, method = RequestMethod.GET)
    public @ResponseBody AppResponse getAgreement(HttpServletRequest request, HttpServletResponse response
    		, @RequestParam(name="userId", required=true) String userId) {
		AppResponse appResponse = null;
		RequestContext req = new RequestContext(request);
		try {
    		String rootPath = request.getSession().getServletContext().getRealPath("");
    		String srcPath = rootPath + "/template/APS_Partners_Agreement.txt";
    		appResponse = new AppResponse(HttpServletResponse.SC_OK, "");
    		appResponse.putData("agreement",  agreementTxt(userId, srcPath));
		} catch (Exception e) {
			appResponse = new AppResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "");
			e.printStackTrace();
		}
		return appResponse;
    }
	
    @SuppressWarnings("resource")
	private String agreementTxt(String userId, String srcPath) throws Exception {
    	User user = registrationService.findUserByUserId(userId);
		String[] arr = (sdfDate.format(new Date())).split("-");
		String agreementDate = arr[0]+"年"+arr[1]+"月"+arr[2]+"日";
		StringBuffer sb = new StringBuffer();
		BufferedReader br = new BufferedReader(new FileReader(srcPath));
        String line;
        while ((line = br.readLine()) != null) {
        	if(line.contains("$UpStream$"))
        		line = line.replace("$UpStream$", user.getChannel().getName());
        	if(line.contains("$Date$"))
        		line = line.replace("$Date$", agreementDate);
        	if(line.contains("$UserName$"))
        		line = line.replace("$UserName$", user.getName());
        	if(line.contains("$Company$"))
        		line = line.replace("$Company$", "CMP信息服务网联");
        	
        	sb.append(line);
        }
        return sb.toString();
    }
    
	
	/**
	 * user[簽署合同]
	 * 寄發 註冊成功mail
	 */
	@RequestMapping(value = { "/agreeAgreement" }, method = RequestMethod.GET)
    public @ResponseBody AppResponse agreeAgreement(HttpServletRequest request, HttpServletResponse response
    		, @RequestParam(name="userId", required=true) String userId) {
		AppResponse appResponse = null;
		RequestContext req = new RequestContext(request);
		try {
			String rootPath = request.getSession().getServletContext().getRealPath("");
    		registrationService.agreement(userId, rootPath);
    		appResponse = new AppResponse(HttpServletResponse.SC_OK, req.getMessage("gotMail"));
		} catch (Exception e) {
			appResponse = new AppResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "");
			e.printStackTrace();
		}
		return appResponse;
    }
	
	/**
	 * [recoverPassword]
	 * 寄發 重設密碼連結
	 */
	@RequestMapping(value = { "/recoverPassword" }, method = RequestMethod.GET)
    public @ResponseBody AppResponse recoverPassword(HttpServletRequest request, HttpServletResponse response
    		, @RequestParam(name="emailAddress", required=true) String emailAddress) {
		AppResponse appResponse = null;
		RequestContext req = new RequestContext(request);
		try {
			User checkUser = registrationService.checkEmailAvailable(emailAddress);
    		//驗證email是否存在
    		if(null!=checkUser && 6<=checkUser.getStatus().getSort()){
    			
    		}else{
    			appResponse = new AppResponse(HttpServletResponse.SC_BAD_REQUEST, req.getMessage("verifyEmailAgain"));
    			return appResponse;
    		}
    		
    		StringBuffer sb = request.getRequestURL();
    		String appName = request.getContextPath();
    		String url = sb.substring(0, sb.indexOf(appName)) +appName+ "/registration/resetPassword";
    		User user = registrationService.recoverPassword(emailAddress, url, checkUser);
			appResponse = new AppResponse(HttpServletResponse.SC_OK, req.getMessage("resetPasswordNotification"));
			appResponse.putData("userId",  user.getId());
			
		} catch (Exception e) {
			appResponse = new AppResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, req.getMessage("verifyEmailAgain"));
			e.printStackTrace();
		}
		return appResponse;
    }
	
	/**
	 * [resetPassword]
	 * user 點擊重設密碼連結
	 * return 重設密碼頁面
	 */
	@RequestMapping(value = { "/resetPassword" }, params = "tokenId", method = RequestMethod.GET)
    public String resetPassword(HttpServletRequest request, HttpServletResponse response, Model model
    		, @ModelAttribute("UserInfoForm") UserInfoForm form
    		, @RequestParam(name="tokenId", required=true) String tokenId) {
		RequestContext req = new RequestContext(request);
		try {
			User user = registrationService.verifyToken(tokenId);

    		if(null==user){
    			model.addAttribute("message", req.getMessage("error.verifyFail"));
    			return "login";
    		}
    		form.setUserId(user.getId());
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", req.getMessage("error.verifyFail"));
			return "login";
		}
        return "registration/resetPassword";
    }
	
	/**
	 * [passwordConfirm]
	 */
	@RequestMapping(value = { "/passwordConfirm" }, method = RequestMethod.GET)
    public @ResponseBody AppResponse passwordConfirm(HttpServletRequest request, HttpServletResponse response
    		, @RequestParam(name="userId", required=true) String userId
    		, @RequestParam(name="password", required=true) String password) {
		AppResponse appResponse = null;
		RequestContext req = new RequestContext(request);
		try {
			registrationService.resetUserPassword(userId, password);
			appResponse = new AppResponse(HttpServletResponse.SC_OK, req.getMessage("resetPasswordSuccess"));
		} catch (Exception e) {
			appResponse = new AppResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "");
			e.printStackTrace();
		}
		return appResponse;
    }
}
