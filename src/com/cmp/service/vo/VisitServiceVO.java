package com.cmp.service.vo;

import java.util.ArrayList;
import java.util.List;

import com.cmp.model.FilesVisit;
import com.cmp.model.VisitInfo;

public class VisitServiceVO {

	private Integer visitId;
	private Integer userId;
	private Integer fileSeqNo;
	private String fileCategory;
	private VisitInfo visitInfo;
	private List<FilesVisit> filesVisit;
	
	public String[] visaStatusArray;
	public String[] accommodationSituationArray;
	public Integer[] amountReceivedArray;
	public String[] remarkArray;
	public Integer[] custIdArray;
	
	private String visitName;
	private String description;
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
	private Integer status;
	private String remark;
	private long memberCount;
	private List<VisitServiceVO> visitList = new ArrayList<VisitServiceVO>();
	
	private String userName;	//渠道商
	private String custName;	//客戶名稱
	private String visaStatus;	//簽證情況
	private String accommodationSituation;	//住宿情況
	private String amountReceived;	//已收金額
	private Integer custId;
	private boolean custNotReady = false;	//客戶狀態 or資料尚不完整
	
	private String updateDate;
	private String updateBy;
	private String createBy;
	private List<String> userAccounts = new ArrayList<String>();
	private Boolean canAdd = false;
	private Boolean canModify = false;
	private Boolean canSeeAll = false;
	
	public Integer getVisitId() {
		return visitId;
	}
	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getFileSeqNo() {
		return fileSeqNo;
	}
	public void setFileSeqNo(Integer fileSeqNo) {
		this.fileSeqNo = fileSeqNo;
	}
	public String getFileCategory() {
		return fileCategory;
	}
	public void setFileCategory(String fileCategory) {
		this.fileCategory = fileCategory;
	}
	public VisitInfo getVisitInfo() {
		return visitInfo;
	}
	public void setVisitInfo(VisitInfo visitInfo) {
		this.visitInfo = visitInfo;
	}
	public List<FilesVisit> getFilesVisit() {
		return filesVisit;
	}
	public void setFilesVisit(List<FilesVisit> filesVisit) {
		this.filesVisit = filesVisit;
	}
	public String getVisitName() {
		return visitName;
	}
	public void setVisitName(String visitName) {
		this.visitName = visitName;
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
	public long getMemberCount() {
		return memberCount;
	}
	public void setMemberCount(long memberCount) {
		this.memberCount = memberCount;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Boolean getCanModify() {
		return canModify;
	}
	public void setCanModify(Boolean canModify) {
		this.canModify = canModify;
	}
	public Boolean getCanAdd() {
		return canAdd;
	}
	public void setCanAdd(Boolean canAdd) {
		this.canAdd = canAdd;
	}
	public Boolean getCanSeeAll() {
		return canSeeAll;
	}
	public void setCanSeeAll(Boolean canSeeAll) {
		this.canSeeAll = canSeeAll;
	}
	public List<String> getUserAccounts() {
		return userAccounts;
	}
	public void setUserAccounts(List<String> userAccounts) {
		this.userAccounts = userAccounts;
	}
	public List<VisitServiceVO> getVisitList() {
		return visitList;
	}
	public void setVisitList(List<VisitServiceVO> visitList) {
		this.visitList = visitList;
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
	public String getVisaStatus() {
		return visaStatus;
	}
	public void setVisaStatus(String visaStatus) {
		this.visaStatus = visaStatus;
	}
	public String getAccommodationSituation() {
		return accommodationSituation;
	}
	public void setAccommodationSituation(String accommodationSituation) {
		this.accommodationSituation = accommodationSituation;
	}
	public String getAmountReceived() {
		return amountReceived;
	}
	public void setAmountReceived(String amountReceived) {
		this.amountReceived = amountReceived;
	}
	public Integer getCustId() {
		return custId;
	}
	public void setCustId(Integer custId) {
		this.custId = custId;
	}
	public boolean isCustNotReady() {
		return custNotReady;
	}
	public void setCustNotReady(boolean custNotReady) {
		this.custNotReady = custNotReady;
	}
	public String[] getVisaStatusArray() {
		return visaStatusArray;
	}
	public void setVisaStatusArray(String[] visaStatusArray) {
		this.visaStatusArray = visaStatusArray;
	}
	public String[] getAccommodationSituationArray() {
		return accommodationSituationArray;
	}
	public void setAccommodationSituationArray(String[] accommodationSituationArray) {
		this.accommodationSituationArray = accommodationSituationArray;
	}
	public Integer[] getAmountReceivedArray() {
		return amountReceivedArray;
	}
	public void setAmountReceivedArray(Integer[] amountReceivedArray) {
		this.amountReceivedArray = amountReceivedArray;
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
}
