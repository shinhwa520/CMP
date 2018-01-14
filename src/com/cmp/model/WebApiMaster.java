package com.cmp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(
	name = "WEB_API_MASTER",
	uniqueConstraints = {@UniqueConstraint(columnNames = {"API_MASTER_ID"})}
)
public class WebApiMaster {

	public WebApiMaster() {
		
	}
	
	public WebApiMaster(String apiMasterId) {
		super();
		this.apiMasterId = apiMasterId;
	}
	
	public WebApiMaster(String apiMasterId, String webName, Integer seqNo, String description, String apiUrl,
			String apiMethodType, String dataType, String parameterNames, String dataExample) {
		super();
		this.apiMasterId = apiMasterId;
		this.webName = webName;
		this.seqNo = seqNo;
		this.description = description;
		this.apiUrl = apiUrl;
		this.apiMethodType = apiMethodType;
		this.dataType = dataType;
		this.parameterNames = parameterNames;
		this.dataExample = dataExample;
	}

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "API_MASTER_ID", unique = true)
	private String apiMasterId;
	
    @Column(name = "WEB_NAME", nullable = false)
	private String webName;
	
    @Column(name = "SEQ_NO", nullable = false)
	private Integer seqNo;
	
    @Column(name = "DESCRIPTION", nullable = true)
	private String description;
	
    @Column(name = "API_URL", nullable = false)
	private String apiUrl;
	
    @Column(name = "API_METHOD_TYPE", nullable = false)
	private String apiMethodType;
	
    @Column(name = "DATA_TYPE", nullable = true)
	private String dataType;
	
    @Column(name = "PARAMETER_NAMES", nullable = true)
	private String parameterNames;
	
    @Column(name = "DATA_EXAMPLE", nullable = true)
	private String dataExample;
	
	public String getApiMasterId() {
		return apiMasterId;
	}
	public void setApiMasterId(String apiMasterId) {
		this.apiMasterId = apiMasterId;
	}
	public String getWebName() {
		return webName;
	}
	public void setWebName(String webName) {
		this.webName = webName;
	}
	public Integer getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getApiUrl() {
		return apiUrl;
	}
	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
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
}