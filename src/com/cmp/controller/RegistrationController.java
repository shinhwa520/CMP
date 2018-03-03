package com.cmp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmp.Response;
import com.cmp.form.registration.EmailConfirmForm;
import com.cmp.form.registration.UserInfoForm;
import com.cmp.model.User;
import com.cmp.pdf.AgreementPdfTemplate;
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
    	model.addAttribute("message", "");
        return "registration/email";
    }
	
	/**
	 * user 輸入email
	 * return confirm email訊息頁面
	 */
	@RequestMapping(value = { "/emailConfirm" }, method = RequestMethod.POST)
    public String emailConfirm(Model model, @ModelAttribute("EmailConfirmForm") EmailConfirmForm form, HttpServletRequest request, HttpServletResponse response) {
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
	@RequestMapping(value = { "/userInfo" }, method = RequestMethod.GET)//POST
    public String userInfo(Model model, @ModelAttribute("UserInfoForm") UserInfoForm form, HttpServletRequest request, HttpServletResponse response) {
    	try {
    		if(!registrationService.findUserByAccount(form.getAccount()).isEmpty()){
    			model.addAttribute("message", "Account已被使用，請重新輸入");
    			return "registration/user";
    		}
    		
//    		String channelUrl = form.getChannelUrl();
//    		int index = channelUrl.lastIndexOf("/");
//    		
//    		if(-1==index){
//    			//user 輸入key
//    		}else{
//    			//user 輸入url
//    			channelUrl = channelUrl.substring(index+1);
//    		}
			registrationService.saveUserInfo(new RegistrationUserVO(
							form.getUserId()
			    			,form.getName()
			    			,form.getAccount()
			    			,form.getPassword()
			    			,form.getPhone()
			    			,null)
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
	@RequestMapping(value = { "/agreement" }, method = RequestMethod.POST)
    public String agreement(Model model, @ModelAttribute("UserInfoForm") UserInfoForm form) {
    	try {
    		if(StringUtils.isBlank(form.getChannelAccount())){
    			model.addAttribute("message", "您未輸入上游帳號，系統預設主渠道商為您的上游");
    		}else{
        		if(registrationService.upstream(form.getUserId(), form.getChannelAccount()))
        			model.addAttribute("message", "");
        		else
        			model.addAttribute("message", "您輸入的帳號查無資料，系統預設主渠道商為您的上游");
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "registration/agreement";
    }
	
	/**
	 * return agreementPdfView
	 */
	@GetMapping("/agreementPdf")
    public String agreementPdf(Model model, HttpServletRequest request, HttpServletResponse response) {
		String rootPath = request.getSession().getServletContext().getRealPath("");
		String templatePath = rootPath + "/template/APS_Partners_Agreement.docx";
		String tmpPathName  = rootPath + "/template/tmp/" + sdfDateTime.format(new Date()) + "_Agreement.txt";
//		File file = new File(templatePath);
		
		String result = "";
		try {
			FileInputStream fis = new FileInputStream(templatePath);
			XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
			XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);
			result = extractor.getText();
			
//			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
//			//BufferedReader br = new BufferedReader(new FileReader(file));// 构造一个BufferedReader类来读取文件 
//			String s = null;
//			while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行 
//				result = result + "\n" + s;
//			}
//			br.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		doWriteTextFile(tmpPathName, result);
		model.addAttribute("agreementPdf", getAgreementPdf(result));
		return "agreementPdfView";
//		return null;
    }
	
	/**
	 * user 簽署合同
	 * return jsonResponse
	 */
	@RequestMapping(value = { "/agreeAgreement" }, method = RequestMethod.GET)
    public @ResponseBody String agreeAgreement(@ModelAttribute("UserInfoForm") UserInfoForm form) {
    	try {
//    		registrationService.agreement(form.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
        return jsonResponse(Response.REGISTRATION_SUCCESS);
    }
	
    public AgreementPdfTemplate getAgreementPdf(String content) {
    	AgreementPdfTemplate pdf = new AgreementPdfTemplate();
    	pdf.setName("My Report");
    	pdf.setContent(content);
    	pdf.setDate(LocalDateTime.now());
        return pdf;
    }
    
    
    public void doWriteTextFile(String pathname, String content) {
    	File file = new File(pathname);
    	try (FileOutputStream fos = new FileOutputStream(file)) {
    		// if file doesn't exists, then create it
    		if (!file.exists()) {
    			file.createNewFile();
    		}
    		// get the content in bytes
    		byte[] contentInBytes = content.getBytes();
    		fos.write(contentInBytes);
    		fos.flush();
    		fos.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	} finally {
    		
		}
    }
}
