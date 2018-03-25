package com.cmp.model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
	name = "visit_info",
	uniqueConstraints = {@UniqueConstraint(columnNames = {"visit_id"})}
)
public class VisitInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "visit_id", unique = true)
	private Integer visitId;
	
	@Column(name = "delete_flag", nullable = false)
	private String deleteFlag = "N";
	
	@Column(name = "visit_name", nullable = false)
	private String visitName;
	
	@Column(name = "description", nullable = true)
	private String description;
	
	@Column(name = "main_title", nullable = true)
	private String mainTitle;
	
	@Column(name = "main_content", nullable = true)
	private String mainContent;
	
	@Column(name = "sub_title", nullable = true)
	private String subTitle;
	
	@Column(name = "join_begin_time", nullable = false)
	private Timestamp joinBeginTime;
	
	@Column(name = "join_end_time", nullable = false)
	private Timestamp joinEndTime;
	
	@Column(name = "max_member_count", nullable = false)
	private Integer maxMemberCount;
	
	@Column(name = "min_member_count", nullable = false)
	private Integer minMemberCount;
	
	@Column(name = "departure_date", nullable = true)
	private Date departureDate;
	
	@Column(name = "departure_time", nullable = true)
	private Time departureTime;
	
	@Column(name = "departure_city", nullable = true)
	private String departureCity;
	
	@Column(name = "arrival_date", nullable = true)
	private Date arrivalDate;
	
	@Column(name = "arrival_time", nullable = true)
	private Time arrivalTime;
	
	@Column(name = "arrival_airport_terminal", nullable = true)
	private String arrivalAirportTerminal;
	
	@Column(name = "return_date", nullable = true)
	private Date returnDate;
	
	@Column(name = "return_flight_time", nullable = true)
	private Time returnFlightTime;
	
	@Column(name = "return_airport_terminal", nullable = true)
	private String returnAirportTerminal;
	
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;
    
    @Column(name = "remark", nullable = true)
	private String remark;
	
	@Column(name = "create_time", nullable = true)
	private Timestamp createTime;
    
    @Column(name = "create_by", nullable = true)
	private String createBy;
    
    @Column(name = "update_time", nullable = true)
	private Timestamp updateTime;
    
    @Column(name = "update_by", nullable = true)
	private String updateBy;

    @OneToOne
    @JoinColumn(name = "setting_id")
	private VisitSetting visitSetting;

	public VisitInfo() {
		super();
	}

	public VisitInfo(Integer visitId, String deleteFlag, String visitName, String description, String mainTitle,
			String mainContent, String subTitle, Timestamp joinBeginTime, Timestamp joinEndTime, Integer maxMemberCount,
			Integer minMemberCount, Date departureDate, Time departureTime, String departureCity, Date arrivalDate,
			Time arrivalTime, String arrivalAirportTerminal, Date returnDate, Time returnFlightTime,
			String returnAirportTerminal, Status status, String remark, Timestamp createTime, String createBy,
			Timestamp updateTime, String updateBy, VisitSetting visitSetting) {
		super();
		this.visitId = visitId;
		this.deleteFlag = deleteFlag;
		this.visitName = visitName;
		this.description = description;
		this.mainTitle = mainTitle;
		this.mainContent = mainContent;
		this.subTitle = subTitle;
		this.joinBeginTime = joinBeginTime;
		this.joinEndTime = joinEndTime;
		this.maxMemberCount = maxMemberCount;
		this.minMemberCount = minMemberCount;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.departureCity = departureCity;
		this.arrivalDate = arrivalDate;
		this.arrivalTime = arrivalTime;
		this.arrivalAirportTerminal = arrivalAirportTerminal;
		this.returnDate = returnDate;
		this.returnFlightTime = returnFlightTime;
		this.returnAirportTerminal = returnAirportTerminal;
		this.status = status;
		this.remark = remark;
		this.createTime = createTime;
		this.createBy = createBy;
		this.updateTime = updateTime;
		this.updateBy = updateBy;
		this.visitSetting = visitSetting;
	}

	public Integer getVisitId() {
		return visitId;
	}

	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
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

	public String getMainTitle() {
		return mainTitle;
	}

	public void setMainTitle(String mainTitle) {
		this.mainTitle = mainTitle;
	}

	public String getMainContent() {
		return mainContent;
	}

	public void setMainContent(String mainContent) {
		this.mainContent = mainContent;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public Timestamp getJoinBeginTime() {
		return joinBeginTime;
	}

	public void setJoinBeginTime(Timestamp joinBeginTime) {
		this.joinBeginTime = joinBeginTime;
	}

	public Timestamp getJoinEndTime() {
		return joinEndTime;
	}

	public void setJoinEndTime(Timestamp joinEndTime) {
		this.joinEndTime = joinEndTime;
	}

	public Integer getMaxMemberCount() {
		return maxMemberCount;
	}

	public void setMaxMemberCount(Integer maxMemberCount) {
		this.maxMemberCount = maxMemberCount;
	}

	public Integer getMinMemberCount() {
		return minMemberCount;
	}

	public void setMinMemberCount(Integer minMemberCount) {
		this.minMemberCount = minMemberCount;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Time getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Time departureTime) {
		this.departureTime = departureTime;
	}

	public String getDepartureCity() {
		return departureCity;
	}

	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Time getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Time arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getArrivalAirportTerminal() {
		return arrivalAirportTerminal;
	}

	public void setArrivalAirportTerminal(String arrivalAirportTerminal) {
		this.arrivalAirportTerminal = arrivalAirportTerminal;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Time getReturnFlightTime() {
		return returnFlightTime;
	}

	public void setReturnFlightTime(Time returnFlightTime) {
		this.returnFlightTime = returnFlightTime;
	}

	public String getReturnAirportTerminal() {
		return returnAirportTerminal;
	}

	public void setReturnAirportTerminal(String returnAirportTerminal) {
		this.returnAirportTerminal = returnAirportTerminal;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public VisitSetting getVisitSetting() {
		return visitSetting;
	}

	public void setVisitSetting(VisitSetting visitSetting) {
		this.visitSetting = visitSetting;
	}
}
