package com.cmp.form;

import java.util.List;

import com.cmp.service.vo.VisitServiceVO;

public class VisitForm {

	private String visitName;
	private String visitDescription;
	private String beginDate;
	private String endDate;
	private Integer minMemberCount;
	private Integer maxMemberCount;
	private String departureDate;
	private String departureTime;
	private String departureCity;
	private String arrivalDate;
	private String arrivalTime;
	private String arrivalAirportTerminal;
	private String returnDate;
	private String returnTime;
	private String returnAirportTerminal;
	private String status;
	private String remarks;
	private Integer memberCount;
	private List<VisitServiceVO> visitList;
	
	private Boolean canAdd = false;
	
	public String getVisitName() {
		return visitName;
	}
	public void setVisitName(String visitName) {
		this.visitName = visitName;
	}
	public String getVisitDescription() {
		return visitDescription;
	}
	public void setVisitDescription(String visitDescription) {
		this.visitDescription = visitDescription;
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
	public String getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public String getDepartureCity() {
		return departureCity;
	}
	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}
	public String getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getArrivalAirportTerminal() {
		return arrivalAirportTerminal;
	}
	public void setArrivalAirportTerminal(String arrivalAirportTerminal) {
		this.arrivalAirportTerminal = arrivalAirportTerminal;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public String getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}
	public String getReturnAirportTerminal() {
		return returnAirportTerminal;
	}
	public void setReturnAirportTerminal(String returnAirportTerminal) {
		this.returnAirportTerminal = returnAirportTerminal;
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
	public List<VisitServiceVO> getVisitList() {
		return visitList;
	}
	public void setVisitList(List<VisitServiceVO> visitList) {
		this.visitList = visitList;
	}
	public Boolean getCanAdd() {
		return canAdd;
	}
	public void setCanAdd(Boolean canAdd) {
		this.canAdd = canAdd;
	}
}
