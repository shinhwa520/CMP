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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
	name = "visit_detail",
	uniqueConstraints = {@UniqueConstraint(columnNames = {"visit_detail_id"})}
)
public class VisitDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "visit_detail_id", unique = true)
	private Integer id;
	
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "visit_id", nullable = false)
	private VisitInfo visitInfo;
	
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cust_id", nullable = false)
	private Customer cust;
	
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", nullable = true)
    private Status status;
    
    @Column(name = "remark", nullable = true)
	private String remark;
    
    @Column(name = "visa_status", nullable = true)
	private String visaStatus;
    
    @Column(name = "accommodation_status", nullable = true)
	private String accommodationStatus;
    
    @Column(name = "amount_received", nullable = true)
	private int amountReceived = 0;
	
	@Column(name = "create_time", nullable = true)
	private Timestamp createTime;
    
    @Column(name = "create_by", nullable = true)
	private String createBy;
    
    @Column(name = "update_time", nullable = true)
	private Timestamp updateTime;
    
    @Column(name = "update_by", nullable = true)
	private String updateBy;

	public VisitDetail() {
	}

	public VisitDetail(Integer id, VisitInfo visitInfo, Customer cust, Status status, String remark,
			String visaStatus, String accommodationStatus, int amountReceived, Timestamp createTime, String createBy,
			Timestamp updateTime, String updateBy) {
		super();
		this.id = id;
		this.visitInfo = visitInfo;
		this.cust = cust;
		this.status = status;
		this.remark = remark;
		this.visaStatus = visaStatus;
		this.accommodationStatus = accommodationStatus;
		this.amountReceived = amountReceived;
		this.createTime = createTime;
		this.createBy = createBy;
		this.updateTime = updateTime;
		this.updateBy = updateBy;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public VisitInfo getVisitInfo() {
		return visitInfo;
	}

	public void setVisitInfo(VisitInfo visitInfo) {
		this.visitInfo = visitInfo;
	}

	public Customer getCust() {
		return cust;
	}

	public void setCust(Customer cust) {
		this.cust = cust;
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

	public String getVisaStatus() {
		return visaStatus;
	}

	public void setVisaStatus(String visaStatus) {
		this.visaStatus = visaStatus;
	}

	public String getAccommodationStatus() {
		return accommodationStatus;
	}

	public void setAccommodationStatus(String accommodationStatus) {
		this.accommodationStatus = accommodationStatus;
	}

	public int getAmountReceived() {
		return amountReceived;
	}

	public void setAmountReceived(int amountReceived) {
		this.amountReceived = amountReceived;
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
}
