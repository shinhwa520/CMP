package com.cmp.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
			String subContent, Timestamp createTime, String createBy, Timestamp updateTime, String updateBy,
			VisitSetting visitSetting) {
		super();
		this.visitId = visitId;
		this.visitName = visitName;
		this.mainTitle = mainTitle;
		this.mainContent = mainContent;
		this.subTitle = subTitle;
		this.subContent = subContent;
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
