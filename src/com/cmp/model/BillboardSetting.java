package com.cmp.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(
	name = "billboard_setting",
	uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})}
)
public class BillboardSetting {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "id", unique = true)
	private String id;
	
	@Column(name = "order_num", nullable = false)
	private Integer orderNum;
	
	@Column(name = "on_top", nullable = true)
	private String onTop;
	
	@Column(name = "is_opened", nullable = true)
	private String isOpened;
	
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
    
    @OneToOne(optional = false, mappedBy = "billboardSetting")
    private BillboardContent billboardContent;

	public BillboardSetting() {
		super();
	}

	public BillboardSetting(String id, Integer orderNum, String onTop, String isOpened, Timestamp activationBegin,
			Timestamp activationEnd, Timestamp createTime, String createBy, Timestamp updateTime, String updateBy,
			BillboardContent billboardContent) {
		super();
		this.id = id;
		this.orderNum = orderNum;
		this.onTop = onTop;
		this.isOpened = isOpened;
		this.activationBegin = activationBegin;
		this.activationEnd = activationEnd;
		this.createTime = createTime;
		this.createBy = createBy;
		this.updateTime = updateTime;
		this.updateBy = updateBy;
		this.billboardContent = billboardContent;
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

	public String getOnTop() {
		return onTop;
	}

	public void setOnTop(String onTop) {
		this.onTop = onTop;
	}

	public String getIsOpened() {
		return isOpened;
	}

	public void setIsOpened(String isOpened) {
		this.isOpened = isOpened;
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

	public BillboardContent getBillboardContent() {
		return billboardContent;
	}

	public void setBillboardContent(BillboardContent billboardContent) {
		this.billboardContent = billboardContent;
	}
}
