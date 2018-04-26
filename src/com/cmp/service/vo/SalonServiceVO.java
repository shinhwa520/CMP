package com.cmp.service.vo;

import java.util.ArrayList;
import java.util.List;

public class SalonServiceVO {

	private Integer salonId;
	private Integer userId;
	private String salonName;
	private String description;
	private String beginDate;
	private String endDate;
	private Integer minMemberCount;
	private Integer maxMemberCount;
	private Integer status;
	private String remark;
	private long memberCount;
	
	private boolean canModify;
	
	private String userName;	//渠道商
	private String custName;	//客戶名稱
	private Integer custId;
	private Integer statusId;
	private Integer sort;
	
	public String[] remarkArray;
	public Integer[] custIdArray;
	public Integer[] statusSortArray;
	
	private List<SalonServiceVO> salonList = new ArrayList<SalonServiceVO>();

	public Integer getSalonId() {
		return salonId;
	}

	public void setSalonId(Integer salonId) {
		this.salonId = salonId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getSalonName() {
		return salonName;
	}

	public void setSalonName(String salonName) {
		this.salonName = salonName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getMinMemberCount() {
		return minMemberCount;
	}

	public void setMinMemberCount(Integer minMemberCount) {
		this.minMemberCount = minMemberCount;
	}

	public Integer getMaxMemberCount() {
		return maxMemberCount;
	}

	public void setMaxMemberCount(Integer maxMemberCount) {
		this.maxMemberCount = maxMemberCount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<SalonServiceVO> getSalonList() {
		return salonList;
	}

	public void setSalonList(List<SalonServiceVO> salonList) {
		this.salonList = salonList;
	}

	public boolean isCanModify() {
		return canModify;
	}

	public void setCanModify(boolean canModify) {
		this.canModify = canModify;
	}

	public long getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(long memberCount) {
		this.memberCount = memberCount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String[] getRemarkArray() {
		return remarkArray;
	}

	public void setRemarkArray(String[] remarkArray) {
		this.remarkArray = remarkArray;
	}

	public Integer[] getCustIdArray() {
		return custIdArray;
	}

	public void setCustIdArray(Integer[] custIdArray) {
		this.custIdArray = custIdArray;
	}

	public Integer[] getStatusSortArray() {
		return statusSortArray;
	}

	public void setStatusSortArray(Integer[] statusSortArray) {
		this.statusSortArray = statusSortArray;
	}
	
}
