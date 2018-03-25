package com.cmp.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(
	name = "visit_setting",
	uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})}
)
public class VisitSetting {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "id", unique = true)
	private String id;
	
	@Column(name = "order_num", nullable = false)
	private Integer orderNum;
	
	@Column(name = "activation_begin", nullable = true)
	private Timestamp activationBegin;
	
	@Column(name = "activation_end", nullable = true)
	private Timestamp activationEnd;
	
	@Column(name = "create_time", nullable = true)
	private Timestamp createTime;
    
    @Column(name = "create_by", nullable = true)
	private String createBy;
    
    @Column(name = "update_time", nullable = true)
	private Timestamp updateTime;
    
    @Column(name = "update_by", nullable = true)
	private String updateBy;

    @OneToOne(optional = false, mappedBy = "visitSetting")
    private VisitInfo visitInfo;

	public VisitSetting() {
		super();
	}

	public VisitSetting(String id, Integer orderNum, Timestamp activationBegin, Timestamp activationEnd,
			Timestamp createTime, String createBy, Timestamp updateTime, String updateBy, VisitInfo visitInfo) {
		super();
		this.id = id;
		this.orderNum = orderNum;
		this.activationBegin = activationBegin;
		this.activationEnd = activationEnd;
		this.createTime = createTime;
		this.createBy = createBy;
		this.updateTime = updateTime;
		this.updateBy = updateBy;
		this.visitInfo = visitInfo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Timestamp getActivationBegin() {
		return activationBegin;
	}

	public void setActivationBegin(Timestamp activationBegin) {
		this.activationBegin = activationBegin;
	}

	public Timestamp getActivationEnd() {
		return activationEnd;
	}

	public void setActivationEnd(Timestamp activationEnd) {
		this.activationEnd = activationEnd;
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

	public VisitInfo getVisitInfo() {
		return visitInfo;
	}

	public void setVisitInfo(VisitInfo visitInfo) {
		this.visitInfo = visitInfo;
	}
}
