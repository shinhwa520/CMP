package com.cmp.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.cmp.service.vo.ApiServiceVO;

public class ApiForm implements Serializable {

	private static final long serialVersionUID = 703316900257078792L;

	private String webName;
	private List<ApiServiceVO> apiList = new ArrayList<ApiServiceVO>();
	private String[] chkedApiUrls;
	private String apiMethodType;
	private String[] apiModelIds;
	
	public String getWebName() {
		return webName;
	}
	public void setWebName(String webName) {
		this.webName = webName;
	}
	public List<ApiServiceVO> getApiList() {
		return apiList;
	}
	public void setApiList(List<ApiServiceVO> apiList) {
		this.apiList = apiList;
	}
	public String[] getChkedApiUrls() {
		return chkedApiUrls;
	}
	public void setChkedApiUrls(String[] chkedApiUrls) {
		this.chkedApiUrls = chkedApiUrls;
	}
	public String getApiMethodType() {
		return apiMethodType;
	}
	public void setApiMethodType(String apiMethodType) {
		this.apiMethodType = apiMethodType;
	}
	public String[] getApiModelIds() {
		return apiModelIds;
	}
	public void setApiModelIds(String[] apiModelIds) {
		this.apiModelIds = apiModelIds;
	}
}
