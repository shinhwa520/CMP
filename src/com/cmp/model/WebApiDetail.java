package com.cmp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(
	name = "WEB_API_DETAIL",
	uniqueConstraints = {@UniqueConstraint(columnNames = {"API_DETAIL_ID"})}
)
public class WebApiDetail {
	
	public WebApiDetail() {
		
	}
	
	public WebApiDetail(String apiDetailId) {
		super();
		this.apiDetailId = apiDetailId;
	}
	
	public WebApiDetail(String apiDetailId, String apiMasterId, Integer seqNo, String parameterValues,
			String description) {
		super();
		this.apiDetailId = apiDetailId;
		this.apiMasterId = apiMasterId;
		this.seqNo = seqNo;
		this.parameterValues = parameterValues;
		this.description = description;
	}

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "API_DETAIL_ID", unique = true)
	private String apiDetailId;
	
	@Column(name = "API_MASTER_ID", nullable = false)
	private String apiMasterId;
	
	@Column(name = "SEQ_NO", nullable = false)
	private Integer seqNo;
	
	@Column(name = "PARAMETER_VALUES", nullable = false)
	private String parameterValues;
	
	@Column(name = "DESCRIPTION", nullable = true)
	private String description;
	
	public String getApiDetailId() {
		return apiDetailId;
	}
	public void setApiDetailId(String apiDetailId) {
		this.apiDetailId = apiDetailId;
	}
	public String getApiMasterId() {
		return apiMasterId;
	}
	public void setApiMasterId(String apiMasterId) {
		this.apiMasterId = apiMasterId;
	}
	public Integer getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}
	public String getParameterValues() {
		return parameterValues;
	}
	public void setParameterValues(String parameterValues) {
		this.parameterValues = parameterValues;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
