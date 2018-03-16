package com.cmp.model;

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
	
	@Column(name = "visit_name", nullable = true)
	private String visitName;
	
	@Column(name = "main_title", nullable = true)
	private String mainTitle;
	
	@Column(name = "main_content", nullable = true)
	private String mainContent;
	
	@Column(name = "sub_title", nullable = true)
	private String subTitle;
	
	@Column(name = "sub_content", nullable = true)
	private String subContent;
	
	@Column(name = "join_begin_time", nullable = true)
	private Timestamp joinBeginTime;
	
	@Column(name = "join_end_time", nullable = true)
	private Timestamp joinEndTime;
	
	@Column(name = "departure_city", nullable = true)
	private String departureCity;
	
	@Column(name = "departure_time", nullable = true)
	private Timestamp departureTime;
	
	@Column(name = "departure_terminal", nullable = true)
	private String departureTerminal;
	
	@Column(name = "arriving_time", nullable = true)
	private Timestamp arrivingTime;
	
	@Column(name = "arriving_terminal", nullable = true)
	private String arrivingTerminal;
	
	@Column(name = "return_departure_time", nullable = true)
	private Timestamp returnDepartureTime;
	
	@Column(name = "return_departure_terminal", nullable = true)
	private String returnDepartureTerminal;
	
	@Column(name = "return_arriving_time", nullable = true)
	private Timestamp returnArrivingTime;
	
	@Column(name = "return_arriving_terminal", nullable = true)
	private String returnArrivingTerminal;
	
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", nullable = true)
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
    @JoinColumn(name = "visit_id")
	private VisitSetting visitSetting;

	public VisitInfo() {
		super();
	}

	public VisitInfo(Integer visitId, String visitName, String mainTitle, String mainContent, String subTitle,
			String subContent, Timestamp joinBeginTime, Timestamp joinEndTime, String departureCity,
			Timestamp departureTime, String departureTerminal, Timestamp arrivingTime, String arrivingTerminal,
			Timestamp returnDepartureTime, String returnDepartureTerminal, Timestamp returnArrivingTime,
			String returnArrivingTerminal, Status status, String remark, Timestamp createTime, String createBy,
			Timestamp updateTime, String updateBy, VisitSetting visitSetting) {
		super();
		this.visitId = visitId;
		this.visitName = visitName;
		this.mainTitle = mainTitle;
		this.mainContent = mainContent;
		this.subTitle = subTitle;
		this.subContent = subContent;
		this.joinBeginTime = joinBeginTime;
		this.joinEndTime = joinEndTime;
		this.departureCity = departureCity;
		this.departureTime = departureTime;
		this.departureTerminal = departureTerminal;
		this.arrivingTime = arrivingTime;
		this.arrivingTerminal = arrivingTerminal;
		this.returnDepartureTime = returnDepartureTime;
		this.returnDepartureTerminal = returnDepartureTerminal;
		this.returnArrivingTime = returnArrivingTime;
		this.returnArrivingTerminal = returnArrivingTerminal;
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

	public String getVisitName() {
		return visitName;
	}

	public void setVisitName(String visitName) {
		this.visitName = visitName;
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

	public String getSubContent() {
		return subContent;
	}

	public void setSubContent(String subContent) {
		this.subContent = subContent;
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

	public String getDepartureCity() {
		return departureCity;
	}

	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}

	public Timestamp getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Timestamp departureTime) {
		this.departureTime = departureTime;
	}

	public String getDepartureTerminal() {
		return departureTerminal;
	}

	public void setDepartureTerminal(String departureTerminal) {
		this.departureTerminal = departureTerminal;
	}

	public Timestamp getArrivingTime() {
		return arrivingTime;
	}

	public void setArrivingTime(Timestamp arrivingTime) {
		this.arrivingTime = arrivingTime;
	}

	public String getArrivingTerminal() {
		return arrivingTerminal;
	}

	public void setArrivingTerminal(String arrivingTerminal) {
		this.arrivingTerminal = arrivingTerminal;
	}

	public Timestamp getReturnDepartureTime() {
		return returnDepartureTime;
	}

	public void setReturnDepartureTime(Timestamp returnDepartureTime) {
		this.returnDepartureTime = returnDepartureTime;
	}

	public String getReturnDepartureTerminal() {
		return returnDepartureTerminal;
	}

	public void setReturnDepartureTerminal(String returnDepartureTerminal) {
		this.returnDepartureTerminal = returnDepartureTerminal;
	}

	public Timestamp getReturnArrivingTime() {
		return returnArrivingTime;
	}

	public void setReturnArrivingTime(Timestamp returnArrivingTime) {
		this.returnArrivingTime = returnArrivingTime;
	}

	public String getReturnArrivingTerminal() {
		return returnArrivingTerminal;
	}

	public void setReturnArrivingTerminal(String returnArrivingTerminal) {
		this.returnArrivingTerminal = returnArrivingTerminal;
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
