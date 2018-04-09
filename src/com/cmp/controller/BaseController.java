package com.cmp.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import com.cmp.Constants;
import com.cmp.MenuItem;
import com.cmp.Response;
import com.cmp.security.SecurityUser;
import com.cmp.security.SecurityUtil;
import com.cmp.service.MailService;
import com.cmp.service.SysMailService;


@Controller
public class BaseController {
	@Autowired
	private MailService mailService;
	@Autowired
	private SysMailService sysMailService;
	
	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);
	SimpleDateFormat sdfYearMonth = new SimpleDateFormat("yyyyMM");
    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	public BaseController() {
		super();
		sdfYearMonth.setTimeZone(TimeZone.getTimeZone("GMT"));
		sdfDate.setTimeZone(TimeZone.getTimeZone("GMT"));
		sdfDateTime.setTimeZone(TimeZone.getTimeZone("GMT"));
	}
	
	/**
	 * 
	 * 從 spring security 機制取得登入使用者帳號
	 * 
	 * @return
	 */
	public String getLoginUsername() {
		String username = "";
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (null != auth) {
			username = auth.getName();	
		}
		logger.debug("username: " + username);
		
		return username;
	}
	
	
	/**
	 * 
	 * 控制前端網頁 menu 選項開關
	 * 
	 * @param model
	 * @param menuItem
	 */
	public void setActiveMenu(Model model, MenuItem menuItem) {
		model.addAttribute("active", menuItem.toString());
		
//		if (menuItem.equals(MenuItem.SOURCE_LIST) || menuItem.equals(MenuItem.SESSION_LIST) || menuItem.equals(MenuItem.SESSION_CONTENT) || menuItem.equals(MenuItem.SESSION_DETAIL)) {
//			model.addAttribute("menuopen", "SOURCE");
//		}
		
		boolean isAdmin = false;
		boolean isAvailable = false;
		SecurityUser securityUser = SecurityUtil.getSecurityUser();
		
		String userName = "";
		String userRole = "";
		String email = "";
		if (securityUser != null) {
			userName = securityUser.getUser().getName();
			userRole = securityUser.getUser().getRole().getName();
			email = securityUser.getUser().getEmail();
		}
		
		model.addAttribute("versionCode", Constants.VERSION_CODE);
//		model.addAttribute("accountId", account.getId());
		model.addAttribute("username", userName);
		model.addAttribute("email", email);
		model.addAttribute("isAdmin", isAdmin);
		model.addAttribute("isAvailable", isAvailable);
		model.addAttribute("userRole", userRole);
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		String currentPrincipalName = authentication.getName();
//		logger.debug("currentPrincipalName: " + currentPrincipalName);
//		model.addAttribute("username", currentPrincipalName);
		
		
		HashMap<String, Object> mailMap = mailService.getMailInfo();
		model.addAttribute("mailMap", mailMap);
		
		HashMap<String, Object> sysMailMap = sysMailService.getSysMailInfo();
		model.addAttribute("sysMailMap", sysMailMap);
	}
	
	/**
	 * 
	 * 預設 API 執行成功時回傳 json 內容 
	 * 
	 * @return
	 */
	public Map<Object, Object> apiSuccess(int code, String message) {
		Map<Object, Object> result = new HashMap<Object, Object>();
		
		result.put("code", code);
		result.put("message", message);
		
		return result;
	}

	/**
	 * 
	 * 預設 API 執行失敗時回傳 json 內容 
	 * 
	 * @param message
	 * @return
	 */
	public Map<Object, Object> apiError(int code, String message) {
		Map<Object, Object> result = new HashMap<Object, Object>();
		
		result.put("code", code);
		result.put("message", "錯誤:" + message);
		
		return result;
	}
	
	
	public int diffDate(long updateTime, long nowTime) {
		long diff = (nowTime - updateTime) / (24 * 60 * 60 * 1000);
		return (int) diff;
	}
	
    protected String jsonResponse(Response response) {
        return jsonResponse(response, null, null, null);
    }

    protected String jsonResponse(Response response, JSONObject object) {
        return jsonResponse(response, object, null, null);
    }

    protected String jsonResponse(Response response, JSONArray array) {
        return jsonResponse(response, null, array, null);
    }

    protected String jsonResponse(Response response, JSONArray array, int count) {
        return jsonResponse(response, null, array, count);
    }

    protected String jsonResponse(Response response, JSONObject object, JSONArray array, Integer count) {
        JSONObject result = new JSONObject();

        result.put("status", response.getStatus());
        result.put("message", response.getMessage());
        if (null != object) {
            result.put(response.getKey(), object);
        }
        if (null != array) {
            result.put(response.getKey(), array);
        }
        if (null != count) {
            result.put("count", count);
        }

        return result.toString();
    }
    
    protected Date validateDate(String date) {
        Date d = null;
        if (null == date) {
            return d;
        }
        try {
            d = sdfDate.parse(date);
        } catch (ParseException e) {
            return d;
        }
        return d;
    }

    protected Date validateDateTime(String date) {
        Date d = null;
        if (null == date) {
            return d;
        }
        try {
            d = sdfDateTime.parse(date);
        } catch (ParseException e) {
            return d;
        }
        return d;
    }
    
    public static int getLineNumber() {
        return Thread.currentThread().getStackTrace()[2].getLineNumber();
    }

	//基于Cookie的国际化处理
	protected void changeLang(HttpServletRequest request, HttpServletResponse response, Model model, String langType) {
		if (!model.containsAttribute("contentModel")) {
			if (langType.contains("zh")) {
				Locale locale = new Locale("zh", "CN");
				(new CookieLocaleResolver()).setLocale(request, response, locale);
			} else if (langType.equals("en_US")) {
				Locale locale = new Locale("en", "US");
				(new CookieLocaleResolver()).setLocale(request, response, locale);
			} else
				(new CookieLocaleResolver()).setLocale(request, response, LocaleContextHolder.getLocale());
		}
	}
}