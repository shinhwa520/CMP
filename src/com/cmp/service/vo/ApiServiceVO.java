package com.cmp.service.vo;

import java.util.Map;

import org.directwebremoting.annotations.DataTransferObject;
import org.directwebremoting.annotations.RemoteProperty;

@DataTransferObject
public class ApiServiceVO {

	private String webName;
	private String apiUrl;
	private String masterSeqNo;
	private String masterDecription;
	private String apiMethodType;
	private String dataType;
	private String parameterNames;
	private String dataExample;
	
	private String detailSeqNo;
	private String parameterValues;
	private String detailDescription;
	
	private String apiDetailId;
	private String makaId;
	private String makaUrl;
	
	private Map<String, String> custInfoMap;
	private String custKey;
	private String custInfoKey;
	private String custInfoVal;
	
	@RemoteProperty
	private String[] apiUrls;
	private String[] apiModelIds;
	private String userActive;
	
	public String getWebName() {
		return webName;
	}
	public void setWebName(String webName) {
		this.webName = webName;
	}
	public String getApiUrl() {
		return apiUrl;
	}
	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}
	public String getMasterSeqNo() {
		return masterSeqNo;
	}
	public void setMasterSeqNo(String masterSeqNo) {
		this.masterSeqNo = masterSeqNo;
	}
	public String getMasterDecription() {
		return masterDecription;
	}
	public void setMasterDecription(String masterDecription) {
		this.masterDecription = masterDecription;
	}
	public String getApiMethodType() {
		return apiMethodType;
	}
	public void setApiMethodType(String apiMethodType) {
		this.apiMethodType = apiMethodType;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getParameterNames() {
		return parameterNames;
	}
	public void setParameterNames(String parameterNames) {
		this.parameterNames = parameterNames;
	}
	public String getDataExample() {
		return dataExample;
	}
	public void setDataExample(String dataExample) {
		this.dataExample = dataExample;
	}
	public String getDetailSeqNo() {
		return detailSeqNo;
	}
	public void setDetailSeqNo(String detailSeqNo) {
		this.detailSeqNo = detailSeqNo;
	}
	public String getParameterValues() {
		return parameterValues;
	}
	public void setParameterValues(String parameterValues) {
		this.parameterValues = parameterValues;
	}
	public String getDetailDescription() {
		return detailDescription;
	}
	public void setDetailDescription(String detailDescription) {
		this.detailDescription = detailDescription;
	}
	public String getApiDetailId() {
		return apiDetailId;
	}
	public void setApiDetailId(String apiDetailId) {
		this.apiDetailId = apiDetailId;
	}
	public String getMakaId() {
		return makaId;
	}
	public void setMakaId(String makaId) {
		this.makaId = makaId;
	}
	public String getMakaUrl() {
		return makaUrl;
	}
	public void setMakaUrl(String makaUrl) {
		this.makaUrl = makaUrl;
	}
	public String[] getApiUrls() {
		return apiUrls;
	}
	public void setApiUrls(String[] apiUrls) {
		this.apiUrls = apiUrls;
	}
	public String getCustKey() {
		return custKey;
	}
	public void setCustKey(String custKey) {
		this.custKey = custKey;
	}
	public Map<String, String> getCustInfoMap() {
		return custInfoMap;
	}
	public void setCustInfoMap(Map<String, String> custInfoMap) {
		this.custInfoMap = custInfoMap;
	}
	public String getCustInfoKey() {
		return custInfoKey;
	}
	public void setCustInfoKey(String custInfoKey) {
		this.custInfoKey = custInfoKey;
	}
	public String getCustInfoVal() {
		return custInfoVal;
	}
	public void setCustInfoVal(String custInfoVal) {
		this.custInfoVal = custInfoVal;
	}
	public String[] getApiModelIds() {
		return apiModelIds;
	}
	public void setApiModelIds(String[] apiModelIds) {
		this.apiModelIds = apiModelIds;
	}
	public String getUserActive() {
		return userActive;
	}
	public void setUserActive(String userActive) {
		this.userActive = userActive;
	}
}
