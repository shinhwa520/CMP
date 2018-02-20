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
	name = "billboard_permission",
	uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})}
)
public class BillboardPermission {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "id", unique = true)
	private String id;
	
	@Column(name = "setting_type", nullable = false)
	private String settingType;

	@Column(name = "setting_level", nullable = false)
	private String settingLevel;
	
	@Column(name = "setting_value", nullable = false)
	private String settingValue;
	
	@Column(name = "create_time", nullable = true)
	private Timestamp createTime;
    
    @Column(name = "create_by", nullable = true)
	private String createBy;
    
    @Column(name = "update_time", nullable = true)
	private Timestamp updateTime;
    
    @Column(name = "update_by", nullable = true)
	private String updateBy;
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "seq_no", nullable = false)
    private BillboardContent billboardContent;

	public BillboardPermission() {
		super();
	}

	public BillboardPermission(String id, String settingType, String settingLevel, String settingValue,
			Timestamp createTime, String createBy, Timestamp updateTime, String updateBy,
			BillboardContent billboardContent) {
		super();
		this.id = id;
		this.settingType = settingType;
		this.settingLevel = settingLevel;
		this.settingValue = settingValue;
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

	public String getSettingType() {
		return settingType;
	}

	public void setSettingType(String settingType) {
		this.settingType = settingType;
	}

	public String getSettingLevel() {
		return settingLevel;
	}

	public void setSettingLevel(String settingLevel) {
		this.settingLevel = settingLevel;
	}

	public String getSettingValue() {
		return settingValue;
	}

	public void setSettingValue(String settingValue) {
		this.settingValue = settingValue;
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