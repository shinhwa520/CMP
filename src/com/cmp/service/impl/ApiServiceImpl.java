package com.cmp.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.lang3.StringUtils;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.CustomerDAO;
import com.cmp.dao.WebApiMasterDAO;
import com.cmp.dao.WebApiSettingDAO;
import com.cmp.dao.vo.WebApiDAOVO;
import com.cmp.model.Customer;
import com.cmp.model.WebApiDetail;
import com.cmp.model.WebApiMaster;
import com.cmp.model.WebApiSetting;
import com.cmp.service.ApiService;
import com.cmp.service.vo.ApiServiceVO;
import com.cmp.service.vo.MakaFormDataVO;
import com.cmp.service.vo.MakaFormDataVO.DataList;
import com.google.gson.Gson;

@Service("apiService")
@Transactional
@RemoteProxy(name = "ApiDwr")  
public class ApiServiceImpl implements ApiService {

	private WebApiMasterDAO webApiMasterDAO;
	private WebApiSettingDAO webApiSettingDAO;
	private CustomerDAO customerDAO;
	
	@Override
	public List<ApiServiceVO> findData(String webName) {
		List<ApiServiceVO> returnList = null;
		List<Object[]> objList = null;
		
		try {
			returnList = new ArrayList<ApiServiceVO>();
			WebApiDAOVO waDAOVO = new WebApiDAOVO();
			waDAOVO.setWebName(webName);
			objList = webApiMasterDAO.findWebApiMasterAndDetailByWebApiDAOVO(waDAOVO);
			
			WebApiMaster wamModel;
			WebApiDetail wadModel;
			ApiServiceVO msVO;
			
			if (objList != null && !objList.isEmpty()) {
				for (Object[] objs : objList) {
					wamModel = (WebApiMaster)objs[0];
					wadModel = (WebApiDetail)objs[1];
					
					msVO = new ApiServiceVO();
					
					if (wamModel.getSeqNo() == 1) {
						continue;
					}
					
					msVO.setWebName(wamModel.getWebName());
					
					if (StringUtils.equals(wamModel.getApiMethodType(), "GET")) {
						String url = wamModel.getApiUrl();
						String[] paraName = StringUtils.split(wamModel.getParameterNames(), "@~");
						String[] paraVal = StringUtils.split(wadModel.getParameterValues(), "@~");
						
						for (int i=0; i<paraName.length; i++) {
							System.out.println("url: "+url+", paraName: "+paraName[i]+", paraVal: "+paraVal[i]);
							url = StringUtils.replace(url, paraName[i], paraVal[i]);
							
							if (StringUtils.indexOf(paraName[i], "pid") != -1) {
								msVO.setMakaId(paraVal[i]);
							}
						}
						
						msVO.setApiUrl(url);
						
					} else {
						msVO.setApiUrl(wamModel.getApiUrl());
					}
					
					msVO.setDetailSeqNo(wadModel.getSeqNo().toString());
					msVO.setDetailDescription(wadModel.getDescription());
					msVO.setParameterValues(wadModel.getParameterValues());
					msVO.setApiMethodType(wamModel.getApiMethodType());
					
					returnList.add(msVO);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return returnList;
	}
	
	@RemoteMethod
	public ApiServiceVO doRetrieve(ApiServiceVO apiServiceVO) {
		System.out.println("yo~man!!!");
		try {
			//Step 1.查找登入API
			WebApiDAOVO waDAOVO = new WebApiDAOVO();
			waDAOVO.setMasterSeqNo(ApiService.LOGIN_API_SEQNO);
			List<Object[]> loginObj = webApiMasterDAO.findWebApiMasterAndDetailByWebApiDAOVO(waDAOVO);
			
			String apiUrl = null;
			String jsonData = null;
			String methodType = null;
			boolean useJson = false; 
			WebApiMaster webApiMaster = null;
			WebApiDetail webApiDetail = null;
			if (loginObj != null && !loginObj.isEmpty()) {
				webApiMaster = (WebApiMaster)loginObj.get(0)[0];
				webApiDetail = (WebApiDetail)loginObj.get(0)[1];
				
				apiUrl = webApiMaster.getApiUrl();
				jsonData = transJsonData(webApiDetail.getParameterValues());
				methodType = webApiMaster.getApiMethodType();
				useJson = StringUtils.equals(webApiMaster.getDataType(), "JSON") ? true : false;
				
			} else {
				throw new Exception("[設定異常]未設定Loign API");
			}
			
			//Step 2.發送登入API,取得cookie
			String cookie = doRetrieve(methodType, apiUrl, useJson, jsonData, null);
			System.out.println("cookie: "+cookie);
			
			if (cookie == null) {
				throw new Exception("[連線異常]取得cookie異常");
			}
			
			//Step 3.發送取得表單資料API,取得表單資料JSON data回傳
			String returnJsonData = null;
			Map<String, Boolean> userMap;
			int index = 0;
			for (String formApiUrl : apiServiceVO.getApiUrls()) {
				returnJsonData = doRetrieve(apiServiceVO.getApiMethodType(), formApiUrl, false, null, cookie);
				
				Gson gson = new Gson();
				MakaFormDataVO mfdVO = gson.fromJson(returnJsonData, MakaFormDataVO.class);

				if (mfdVO.getCode() != HttpStatus.SC_OK) {
					continue;
					
				} else {
					userMap = new HashMap<String, Boolean>();
					
					ApiServiceVO custVO;
					Map<String, Map<String, String>> custInfoMap = new HashMap<String, Map<String, String>>();
					for (DataList d : mfdVO.getData().getDataList()) {
						System.out.println(d.getContent());
						
						custVO = transJsonContent2CustVO(d.getContent());
						custInfoMap.put(custVO.getCustKey(), custVO.getCustInfoMap());
					}
//					Type listType = new TypeToken<ArrayList<MakaFormDataVO>>() {}.getType();
//					ArrayList<MakaFormDataVO> jsonArr = gson.fromJson(returnJsonData, listType);
					
					System.out.println(custInfoMap.size());
					
				}
				
				//Step 4.取得此MAKA H5模板ID歸屬的渠道商(USER)現有客戶(CUSTOMER)資料
				List<Customer> customers = customerDAO.findCustByUserThroughApiModelId(apiServiceVO.getApiModelIds()[index]);
				
				for (Customer c : customers) {
					
				}
				
				
				index++;
			}
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private ApiServiceVO transJsonContent2CustVO(String content) {
		ApiServiceVO retVO;
		Map<String, String> custInfoMap;
		try {
			retVO = new ApiServiceVO();
			custInfoMap = new HashMap<String, String>();
			
			content = content.replace("[", "")
							 .replace("{", "")
							 .replace("\\", "")
							 .replace("\"", "")
							 .replace("}", "")
							 .replace("]", "");
			
			String custKey = "";
			String moduleId = "";
			String answer = "";
			String[] cArray = content.split(",");
			for (String c : cArray) {
				if (c.startsWith("module_id:")) {
					moduleId = c.replace("module_id:", "");
					continue;
					
				} else if (c.startsWith("answer:")) {
					answer = c.replace("answer:", "");
					
					WebApiSetting was = webApiSettingDAO.findWebApiSettingByModuleId(moduleId);
					if (was == null) {
						throw new Exception("[Web_Api_Setting] >> 查無指定Module_Id設定: " + moduleId);
						
					} else {
						custKey = custKey.concat(answer);
						custInfoMap.put(was.getTableField(), answer);
					}
				}
			}
			
			retVO.setCustKey(custKey);
			retVO.setCustInfoMap(custInfoMap);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return retVO;
	}
	
	private String transJsonData(String dbStr) {
		String[] paraArray = dbStr.split("@~");
		
		JSONObject jsonObj = new JSONObject();
		String[] keyValue = null;
		for (String para : paraArray) {
			keyValue = StringUtils.split(para, ":");
			jsonObj.put(keyValue[0], keyValue[1]);
		}
        
		return jsonObj.toString();
	}
	
	private String doRetrieve(String methodType, String apiUrl, boolean useJson, String jsonData, String cookie) {
		String returnStr = null;
		
		try {
			if (StringUtils.equals(methodType, "POST")) {
				returnStr = callApiByPost(apiUrl, jsonData);
				
			} else {
				returnStr = callApiByGet(apiUrl, cookie);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return returnStr;
	}
	
	private String callApiByPost(String apiUrl, String jsonData) {
		PostMethod post = null;
		HttpClient client = null;
		String returnStr = "";

		try {
	        // 使用POST方式傳出
 			post = new PostMethod(apiUrl);
 			
			// 設定Content-Type
			post.addRequestHeader("Content-Type", "application/json; charset=utf-8");

			// 填入xml資料
			RequestEntity requestEntity = new ByteArrayRequestEntity(jsonData.getBytes("UTF-8"), "application/json"); 
	        post.setRequestEntity(requestEntity);
			
			// 建立HttpClient
			client = new HttpClient();
			
			// 設定timeout
			if (0 != 5000){
				post.getParams().setSoTimeout(5000);
			}

			// Execute the method.
			client.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
			int statusCode = client.executeMethod(post);
			if (statusCode != HttpStatus.SC_OK) {
				return null;
			} 
			
			 //获得登陆后的 Cookie
            Cookie[] cookies = client.getState().getCookies();
            
            for(Cookie c:cookies){
            	returnStr += c.toString()+";";
            }
            
		} catch (HttpException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (post != null) {
				// release the connection back to the connection pool
				post.releaseConnection();
			} 
		}
		return returnStr;
	}
	
	private String callApiByGet(String apiUrl, String cookie) {
        GetMethod get = null;
		HttpClient client = null;
		int statusCode = 0;
		String resultStr = Integer.toString(statusCode);

		try {
            client = new HttpClient();
            
        	get = new GetMethod(apiUrl);
			get.addRequestHeader("Content-Type", "text/html; charset=UTF-8");
			get.setRequestHeader("cookie", cookie);
			
			get.getParams().setSoTimeout(5000);
			
			statusCode = client.executeMethod(get);
			if (statusCode != HttpStatus.SC_OK) {
				resultStr = Integer.toString(statusCode);

			} else{
				// 取得回傳內容
				resultStr = get.getResponseBodyAsString();	
			}
			
		} catch (HttpException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (get != null) {
				// release the connection back to the connection pool
				get.releaseConnection();
			} 
		}
		
		return resultStr;
	}

	@Autowired
	public void setWebApiMasterDAO(WebApiMasterDAO webApiMasterDAO) {
		this.webApiMasterDAO = webApiMasterDAO;
	}

	@Autowired
	public void setWebApiSettingDAO(WebApiSettingDAO webApiSettingDAO) {
		this.webApiSettingDAO = webApiSettingDAO;
	}

	@Autowired
	public void setCustomerDAO(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}
}
