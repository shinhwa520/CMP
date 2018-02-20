package com.cmp.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import com.cmp.Constants;
import com.cmp.MenuItem;
import com.cmp.Response;
import com.cmp.security.SecurityUser;
import com.cmp.security.SecurityUtil;





public class BaseController {
	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);
    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	public BaseController() {
		super();
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
		if (securityUser != null) {
			userName = securityUser.getUsername();
			userRole = securityUser.getUser().getRole().getName();
		}
		
		model.addAttribute("versionCode", Constants.VERSION_CODE);
//		model.addAttribute("accountId", account.getId());
		model.addAttribute("username", userName);
		model.addAttribute("isAdmin", isAdmin);
		model.addAttribute("isAvailable", isAvailable);
		model.addAttribute("userRole", userRole);
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		String currentPrincipalName = authentication.getName();
//		logger.debug("currentPrincipalName: " + currentPrincipalName);
//		model.addAttribute("username", currentPrincipalName);
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
}
