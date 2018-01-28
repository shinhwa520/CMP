package com.cmp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmp.DatatableResponse;
import com.cmp.form.ApiForm;
import com.cmp.service.ApiService;
import com.cmp.service.vo.ApiServiceVO;

@Controller
public class ApiController {
	private static Log log = LogFactory.getLog(ApiController.class);
	
	private ApiService apiService;
	
	@RequestMapping(value = { "/api" }, method = RequestMethod.GET)
    public String showMainPage(@ModelAttribute("ApiForm") ApiForm form, HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("oh ya~~~");
    	try {
//    		apiService.findMakaIds();
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
        return "api/retrieve_main";
    }
	
	@RequestMapping(value = { "/api/list" }, method = RequestMethod.GET)
    public String showApiData(@ModelAttribute("ApiForm") ApiForm form, HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("oh ya~~~");
    	try {
//    		apiService.findMakaIds();
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
        return "api/api_list";
    }
	
	@RequestMapping(value = { "/api/query" }, method = RequestMethod.POST)
    public String doQuery(@ModelAttribute("ApiForm") ApiForm form, HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("oh ya~~~");
    	try {
    		List<ApiServiceVO> returnList = apiService.findData(form.getWebName(), null, null);
    		
    		if (returnList != null && !returnList.isEmpty()) {
    			form.setApiList(returnList);
        		form.setApiMethodType(returnList.get(0).getApiMethodType());
        		
    		} else {
    			request.setAttribute("ErrorMessage", "查無資料");
    		}
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
        return "api/retrieve_main";
    }
	
	@RequestMapping(value = { "/api/retrieve" }, method = RequestMethod.POST)
	public String doRetrieve(@ModelAttribute("ApiForm") ApiForm form, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("doRetrieve");
		try {
			System.out.println(form.getChkedApiUrls().length);
			ApiServiceVO msVO = new ApiServiceVO();
			msVO.setWebName(form.getWebName());
			msVO.setApiUrls(form.getChkedApiUrls());
			msVO.setApiModelIds(form.getApiModelIds());
			msVO.setApiMethodType(form.getApiMethodType());
			msVO = apiService.doRetrieve(msVO);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return doQuery(form, request, response);
	}
	
	@Autowired
	public void setApiService(ApiService apiService) {
		this.apiService = apiService;
	}
	
	/**
	 * 
	 * 取得轉檔原始檔資料
	 * 
	 * @param start
	 * @param length
	 * @return
	 */
	@RequestMapping(value="/api/getApiData.json", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody DatatableResponse getIngestSource(
			@RequestParam(name="webName", required=false, defaultValue="MAKA") String webName,
			@RequestParam(name="start", required=false, defaultValue="0") Integer start,
			@RequestParam(name="length", required=false, defaultValue="10") Integer length) {
		
		List<ApiServiceVO> returnList = null;
		long total = 0;
		
		try {
			returnList = apiService.findData(webName, start, length);
			total = apiService.countData(webName);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new DatatableResponse(total, returnList, total);
	}
}
