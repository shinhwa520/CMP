package com.cmp.form;

import java.util.List;

import com.cmp.service.vo.SalonServiceVO;

public class SalonForm {

	private String salonName;
	private String salonDescription;
	private String beginDate;
	private String endDate;
	private Integer minMemberCount;
	private Integer maxMemberCount;
	private String status;
	private String remarks;
	private Integer memberCount;
	private List<SalonServiceVO> salonList;
	
	private Boolean canAdd = false;
	
	public String getSalonName() {
		return salonName;
	}
	public void setSalonName(String salonName) {
		this.salonName = salonName;
	}
	public String getSalonDescription() {
		return salonDescription;
	}
	public void setSalonDescription(String salonDescription) {
		this.salonDescription = salonDescription;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getMemberCount() {
		return memberCount;
	}
	public void setMemberCount(Integer memberCount) {
		this.memberCount = memberCount;
	}
	public List<SalonServiceVO> getSalonList() {
		return salonList;
	}
	public void setSalonList(List<SalonServiceVO> salonList) {
		this.salonList = salonList;
	}
	public Boolean getCanAdd() {
		return canAdd;
	}
	public void setCanAdd(Boolean canAdd) {
		this.canAdd = canAdd;
	}
}
