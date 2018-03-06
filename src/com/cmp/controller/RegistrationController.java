package com.cmp.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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

import com.cmp.Response;
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
    public String email(Model model, @ModelAttribute("UserInfoForm") UserInfoForm form, HttpServletRequest request, HttpServletResponse response) {
		model.addAttribute("message", "");
        return "registration/email";
    }
	
	/**
	 * user 輸入email
	 * return confirm email訊息頁面
	 */
	@RequestMapping(value = { "/emailConfirm" }, method = RequestMethod.POST)
    public String emailConfirm(Model model, @ModelAttribute("UserInfoForm") UserInfoForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
    		String mailAddress = form.getEmail();
			RequestContext req = new RequestContext(request);

    		//驗證email是否重複
    		if(!registrationService.checkEmailAvailable(mailAddress)){
    			model.addAttribute("message", req.getMessage("error.emailExist"));
    			return "registration/email";
    		}
    		
    		StringBuffer sb = request.getRequestURL();
    		String appName = request.getContextPath();
    		String url = sb.substring(0, sb.indexOf(appName)) +appName+ "/registration/user";
    		User user = registrationService.initUser(mailAddress, url);
    		form.setUserId(user.getId());
//    		model.addAttribute("message", req.getMessage("confirmEmail"));
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return "registration/token";
    }
	
	/**
	 * user 認證信確認URL
	 * return user 表單提供輸入
	 */
	@RequestMapping(value = { "/user" }, method = RequestMethod.POST)
    public String user(Model model, @ModelAttribute("UserInfoForm") UserInfoForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
    		User user = registrationService.verifyToken(form.getUserId(), form.getVerificationCode());
			RequestContext req = new RequestContext(request);

    		if(null==user){
    			model.addAttribute("message", req.getMessage("error.verifyFail"));
    			return "registration/token";
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
	@RequestMapping(value = { "/userInfo" }, method = RequestMethod.POST)//POST
    public String userInfo(Model model, @ModelAttribute("UserInfoForm") UserInfoForm form, HttpServletRequest request, HttpServletResponse response) {
		RequestContext req = new RequestContext(request);
    	try {
    		if(!registrationService.findUserByAccount(form.getAccount()).isEmpty()){
    			model.addAttribute("message", req.getMessage("error.accountExist"));
    			return "registration/user";
    		}
			registrationService.saveUserInfo(new RegistrationUserVO(
							form.getUserId()
			    			,new String(form.getName().getBytes("iso-8859-1"), "utf-8")
			    			,form.getAccount()
			    			,form.getPassword()
			    			,form.getPhone()
			    			,form.getWeChat())
					);
			RegistrationUserVO vo = registrationService.initQuestList();
			form.setQuesMap(vo.getQuesMap());
			form.setAns(vo.getAns());
			form.setQuesMapkeySize(vo.getQuesMap().keySet().size());
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "registration/question";
    }
	
	/**
	 * user 提交問題答案
	 * return 上游account輸入頁
	 */
	@RequestMapping(value = { "/upstream" }, method = RequestMethod.GET)
    public String upstream(
    		@RequestParam(name="userId", required=true) String userId, 
    		@ModelAttribute("UserInfoForm") UserInfoForm form) {
    	form.setUserId(userId);
        return "registration/upstream";
    }
	
	/**
	 * user 輸入上游 user.account
	 * return 合同頁
	 */
	@RequestMapping(value = { "/agreement" }, method = RequestMethod.POST)//POST
    public String agreement(Model model, @ModelAttribute("UserInfoForm") UserInfoForm form, HttpServletRequest request) {
    	try {
            RequestContext req = new RequestContext(request);
    		if(StringUtils.isBlank(form.getChannelAccount())){
    			registrationService.upstream(form.getUserId(), form.getChannelAccount());
    			model.addAttribute("message", req.getMessage("error.noFillParentChannel"));
    		}else{
        		if(registrationService.upstream(form.getUserId(), form.getChannelAccount()))
        			model.addAttribute("message", "");
        		else
        			model.addAttribute("message", req.getMessage("error.noDataAutoChannel"));
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "registration/agreement";
    }
	
	/**
	 * user 簽署合同
	 * return jsonResponse
	 */
	@RequestMapping(value = { "/agreeAgreement" }, method = RequestMethod.GET)
    public @ResponseBody String agreeAgreement(@ModelAttribute("UserInfoForm") UserInfoForm form) {
    	try {
    		registrationService.agreement(form.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
        return jsonResponse(Response.REGISTRATION_SUCCESS);
    }
	
    @RequestMapping(value = "/agreementPdf", method = RequestMethod.GET)
    protected String agreementPdf(Model model, @RequestParam(name="userId", required=true) String userId, HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = registrationService.findUserByUserId(userId);
		String rootPath = request.getSession().getServletContext().getRealPath("");
		String srcPath = rootPath + "/template/APS_Partners_Agreement.txt";
		
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
        model.addAttribute("agreement", sb.toString());
        return "registration/partnersAgreement";
    }
}
