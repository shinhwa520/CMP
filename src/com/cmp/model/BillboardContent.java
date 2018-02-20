package com.cmp.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
	name = "billboard_content",
	uniqueConstraints = {@UniqueConstraint(columnNames = {"seq_no"})}
)
public class BillboardContent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq_no", unique = true)
	private Integer seqNo;
	
	@Column(name = "title", nullable = true)
	private String title;
	
	@Column(name = "content", nullable = true)
	private String content;
	
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
    private BillboardSetting billboardSetting;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "billboardContent")
    private List<BillboardPermission> billboardPermissions;
    
	public BillboardContent() {
		super();
	}

	public BillboardContent(Integer seqNo, String title, String content, Timestamp createTime, String createBy,
			Timestamp updateTime, String updateBy, BillboardSetting billboardSetting,
			List<BillboardPermission> billboardPermissions) {
		super();
		this.seqNo = seqNo;
		this.title = title;
		this.content = content;
		this.createTime = createTime;
		this.createBy = createBy;
		this.updateTime = updateTime;
		this.updateBy = updateBy;
		this.billboardSetting = billboardSetting;
		this.billboardPermissions = billboardPermissions;
	}

	public Integer getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public BillboardSetting getBillboardSetting() {
		return billboardSetting;
	}

	public void setBillboardSetting(BillboardSetting billboardSetting) {
		this.billboardSetting = billboardSetting;
	}

	public List<BillboardPermission> getBillboardPermissions() {
		return billboardPermissions;
	}

	public void setBillboardPermissions(List<BillboardPermission> billboardPermissions) {
		this.billboardPermissions = billboardPermissions;
	}
}
