package com.cmp.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(
	name = "salon_detail",
	uniqueConstraints = {@UniqueConstraint(columnNames = {"salon_detail_id"})}
)
public class SalonDetail {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "salon_detail_id", unique = true)
	private String id;
	
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "salon_id", nullable = false)
	private SalonInfo salonInfo;
	
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cust_id", nullable = false)
	private Customer cust;
	
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

	public SalonDetail() {
	}

	public SalonDetail(String id, SalonInfo salonInfo, Customer cust, String remark, Timestamp createTime,
			String createBy, Timestamp updateTime, String updateBy) {
		super();
		this.id = id;
		this.salonInfo = salonInfo;
		this.cust = cust;
		this.remark = remark;
		this.createTime = createTime;
		this.createBy = createBy;
		this.updateTime = updateTime;
		this.updateBy = updateBy;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SalonInfo getSalonInfo() {
		return salonInfo;
	}

	public void setSalonInfo(SalonInfo salonInfo) {
		this.salonInfo = salonInfo;
	}

	public Customer getCust() {
		return cust;
	}

	public void setCust(Customer cust) {
		this.cust = cust;
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
}
