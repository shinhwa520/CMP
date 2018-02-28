package com.cmp.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;
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
import com.cmp.dao.StatusDAO;
import com.cmp.dao.UserDAO;
import com.cmp.dao.WebApiMasterDAO;
import com.cmp.dao.WebApiSettingDAO;
import com.cmp.dao.vo.WebApiDAOVO;
import com.cmp.model.Customer;
import com.cmp.model.Status;
import com.cmp.model.User;
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
	
	@Autowired
	private WebApiMasterDAO webApiMasterDAO;
	@Autowired
	private WebApiSettingDAO webApiSettingDAO;
	@Autowired
	private CustomerDAO customerDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private StatusDAO statusDAO;
	
	private Map<String, String> fieldMap = new HashMap<String, String>();
	
	@Override
	public long countData(String webName) throws Exception {
		long rowCount = 0;
		
		try {
			WebApiDAOVO waDAOVO = new WebApiDAOVO();
			waDAOVO.setWebName(webName);
			rowCount = webApiMasterDAO.countWebApiMasterAndDetailByWebApiDAOVO(waDAOVO);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rowCount;
	}
	
	@Override
	public List<ApiServiceVO> findData(String webName, Integer start, Integer length) throws Exception {
		List<ApiServiceVO> returnList = null;
		List<Object[]> objList = null;
		
		try {
			returnList = new ArrayList<ApiServiceVO>();
			WebApiDAOVO waDAOVO = new WebApiDAOVO();
			waDAOVO.setWebName(webName);
			waDAOVO.setStartRow(start);
			waDAOVO.setPageLength(length);
			objList = webApiMasterDAO.findWebApiMasterAndDetailByWebApiDAOVO(waDAOVO);
			
			WebApiMaster wamModel;
			WebApiDetail wadModel;
			User userModel;
			ApiServiceVO msVO;
			
			if (objList != null && !objList.isEmpty()) {
				for (Object[] objs : objList) {
					wamModel = (WebApiMaster)objs[0];
					wadModel = (WebApiDetail)objs[1];
					userModel = wadModel.getUser();
					
					boolean userActive = false;
					if (StringUtils.isNotBlank(userModel.getId())) {
						User user = userDAO.findUserById(userModel.getId());
						
						if (user != null) {
							userActive = true;
						}
					}
					
					msVO = new ApiServiceVO();
					
					if (wamModel.getSeqNo() == 1) {
						continue;
					}
					
					msVO.setWebName(wamModel.getWebName());
					
					if (StringUtils.equals(wamModel.getApiMethodType(), "GET")) {
						String url = wamModel.getApiUrl();
						String[] paraName = StringUtils.split(wamModel.getParameterNames(), SEPERATOR);
						String[] paraVal = StringUtils.split(wadModel.getParameterValues(), SEPERATOR);
						
						for (int i=0; i<paraName.length; i++) {
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
					msVO.setUserActive(userActive ? "Y" : "N");
					
					returnList.add(msVO);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return returnList;
	}
	
	@Override
	public ApiServiceVO doRetrieveFromJob(String webName) throws Exception {
		List<ApiServiceVO> apiList = null;
		List<String> apiUrls = null;
		List<String> apiModelIds = null;
		ApiServiceVO msVO = null;
				
		try {
			apiList = findData(webName, null, null);
			
			if (apiList != null && !apiList.isEmpty()) {
				msVO = new ApiServiceVO();
				msVO.setWebName(webName);
				msVO.setApiMethodType(apiList.get(0).getApiMethodType());
				
				apiUrls = new ArrayList<String>();
				apiModelIds = new ArrayList<String>();
				
				for (ApiServiceVO apiVO : apiList) {
					apiUrls.add(apiVO.getApiUrl());
					apiModelIds.add(apiVO.getMakaId());
				}
				
				msVO.setApiUrls(apiUrls.toArray(new String[apiUrls.size()]));
				msVO.setApiModelIds(apiModelIds.toArray(new String[apiModelIds.size()]));
			}
			
			doRetrieve(msVO);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RemoteMethod
	public ApiServiceVO doRetrieve(ApiServiceVO apiServiceVO) throws Exception {
		System.out.println("doRetrieve");
		
		String userId = null;
		Map<String, Map<String, String>> custInfoFromJsonMap;
		Map<String, Boolean> custInfoFromDBMap;
		try {
			custInfoFromJsonMap = new HashMap<String, Map<String, String>>();
			custInfoFromDBMap = new HashMap<String, Boolean>();
			
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
			
			//Step 3.初始化JSON data、DB與JAVA VO欄位名稱對照表MAP
			initFieldMap(apiServiceVO.getWebName());
			
			//Step 4.發送取得表單資料API,取得表單資料JSON data回傳
			int index = 0;
			for (String formApiUrl : apiServiceVO.getApiUrls()) {
				custInfoFromJsonMap = retrieveJsonAndTrans2CustInfoFromJsonMap(null, formApiUrl, apiServiceVO.getApiMethodType(), cookie);
				
				if (custInfoFromJsonMap == null || (custInfoFromJsonMap != null && custInfoFromJsonMap.isEmpty())) {
					continue;
				}
				System.out.println("apiUrl: "+formApiUrl+", records: "+custInfoFromJsonMap.size());
				
				//Step 5.取得此MAKA H5模板ID歸屬的渠道商(USER)現有客戶(CUSTOMER)資料
				List<Customer> custList = customerDAO.findCustByUserThroughApiModelId(apiServiceVO.getApiModelIds()[index]);
				if (custList != null && !custList.isEmpty()) {
					userId = custList.get(0).getUser().getId();
					custInfoFromDBMap = transCustomer2CustVO(custList);
					
				} else {
					List<User> userList = userDAO.findUserByApiModelId(apiServiceVO.getApiModelIds()[index]);
					
					if (userList != null && !userList.isEmpty()) {
						userId = userList.get(0).getId();
						
					} else {
						throw new Exception("[ApiServiceImpl] Can't find USER data! >> apiModelId: " + apiServiceVO.getApiModelIds()[index]);
					}
				}
				
				//Step 6.比對JSON回傳與DB既存客戶,寫入新增客戶資料
				for (Entry<String, Map<String, String>> custInfoEntry : custInfoFromJsonMap.entrySet()) {
					if (!custInfoFromDBMap.isEmpty() && custInfoFromDBMap.containsKey(custInfoEntry.getKey())) {
						continue;
						
					} else {
						//Step 6-1.查出Cust第一次抓回資料時的Status.status_id
						Status status = statusDAO.findStatus("CUST", 1);
						status = (status == null) ? new Status(1, null, null, 1) : status;
						
						Map<String, String> cusMap = custInfoEntry.getValue();
						
						Customer cust = new Customer();
						for (Entry<String, String> cusEntry : cusMap.entrySet()) {
							BeanUtils.setProperty(cust, cusEntry.getKey(), cusEntry.getValue());
						}
						User user = new User();
						user.setId(userId);
						cust.setUser(user);
						cust.setCreateTime(new Timestamp(System.currentTimeMillis()));
						cust.setCreateBy("SYS");
						cust.setUpdateTime(new Timestamp(System.currentTimeMillis()));
						cust.setUpdateBy("SYS");
						cust.setStatus(status);
						
						customerDAO.insertCustByModel(cust);
					}
				}
				index++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void initFieldMap(String webName) throws Exception {
		List<WebApiSetting> wasList = webApiSettingDAO.findWebApiSettingByWebNameAndModuleId(webName, null);
		
		if (wasList != null && !wasList.isEmpty()) {
			for (WebApiSetting was : wasList) {
				fieldMap.put(was.getModuleId(), was.getSeqNo().toString().concat(SEPERATOR).concat(was.getTableField()).concat(SEPERATOR).concat(was.getVoField()));
			}
		} else {
			throw new Exception("[ApiServiceImpl] init fieldMap failed!! >> no setting found.");
		}
	}
	
	private Map<String, Map<String, String>> retrieveJsonAndTrans2CustInfoFromJsonMap(Map<String, Map<String, String>> custMap, String apiUrl, String apiMethodType, String cookie) {
		Map<String, Map<String, String>> retMap = custMap;
		String returnJsonData = null;
		
		try {
			if (custMap == null) {
				retMap = new HashMap<String, Map<String, String>>();
			}
			
			returnJsonData = doRetrieve(apiMethodType, apiUrl, false, null, cookie);
			
			Gson gson = new Gson();
			MakaFormDataVO mfdVO = gson.fromJson(returnJsonData, MakaFormDataVO.class);

			
			if (mfdVO.getCode() != HttpStatus.SC_OK) {
				return null;
				
			} else {
				BigDecimal totalNum = new BigDecimal(mfdVO.getData().getTotalNum());
				BigDecimal perPageNum = new BigDecimal(mfdVO.getData().getPerPage());
				int pageCount = totalNum.divide(perPageNum, RoundingMode.CEILING).intValue();
				
				ApiServiceVO custVO;
				for (DataList d : mfdVO.getData().getDataList()) {
					custVO = transJsonContent2CustVO(d.getContent());
					retMap.put(custVO.getCustKey(), custVO.getCustInfoMap());
				}
				
				if (pageCount > 1) {
					for (int i=mfdVO.getData().getPageNumber()+1; i<pageCount; i++) {
						String[] tmp = apiUrl.split("pageNumber");
						apiUrl = tmp[0].concat("pageNumber=").concat(Integer.toString(i));
						
						retrieveJsonAndTrans2CustInfoFromJsonMap(retMap, apiUrl, apiMethodType, cookie);
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retMap;
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
			
			String[] custKey = new String[fieldMap.size()];
			String moduleId = "";
			String answer = "";
			String[] cArray = content.split(",");
			for (String c : cArray) {
				if (c.startsWith("module_id:")) {
					moduleId = c.replace("module_id:", "");
					continue;
					
				} else if (c.startsWith("answer:")) {
					answer = c.replace("answer:", "");
					
					if (!fieldMap.containsKey(moduleId)) {
						throw new Exception("[Web_Api_Setting] >> 查無指定Module_Id設定: " + moduleId);
						
					} else {
						String seqNo = fieldMap.get(moduleId).split(SEPERATOR)[0];
						String voField = fieldMap.get(moduleId).split(SEPERATOR)[2];
						
						if (StringUtils.isBlank(seqNo)) {
							throw new Exception("[Web_Api_Setting] >> Seq_No設定錯誤 - tableField:" + voField);
						}
						
						custKey[Integer.parseInt(seqNo)-1] = answer;
						custInfoMap.put(voField, answer);
					}
				}
			}
			
			String key = "";
			for (String ck : custKey) {
				key = key.concat(ck);
			}
			
			retVO.setCustKey(key);
			retVO.setCustInfoMap(custInfoMap);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return retVO;
	}
	
	private Map<String, Boolean> transCustomer2CustVO(List<Customer> custList) throws Exception {
		Map<String, Boolean> retMap;
		String[] keys;
		String key;
		
		try {
			retMap = new HashMap<String, Boolean>();
			
			String seqNo;
			String voField;
			
			for (Customer cust : custList) {
				keys = new String[fieldMap.size()];
				key = "";
				
				for (String value : fieldMap.values()) {
					seqNo = value.split(SEPERATOR)[0];
					voField = value.split(SEPERATOR)[2];
					
					keys[Integer.parseInt(seqNo)-1] = BeanUtils.getProperty(cust, voField);
				}
				
				for (String k : keys) {
					key = key.concat(k);
				}
				
				retMap.put(key, true);
			}
			
		} catch (Exception e) {
			throw new Exception("[ApiServiceImpl] transCustomer2CustVO failed!!");
			
		}
		
		return retMap;
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
}
