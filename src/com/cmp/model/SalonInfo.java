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
	name = "salon_info",
	uniqueConstraints = {@UniqueConstraint(columnNames = {"salon_id"})}
)
public class SalonInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "salon_id", unique = true)
	private Integer salonId;
	
	@Column(name = "delete_flag", nullable = false)
	private String deleteFlag = "N";
	
	@Column(name = "salon_name", nullable = false)
	private String salonName;
	
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
	private SalonSetting salonSetting;

	public SalonInfo() {
		super();
	}

	public SalonInfo(Integer salonId, String deleteFlag, String salonName, String description, String mainTitle,
			String mainContent, String subTitle, Timestamp joinBeginTime, Timestamp joinEndTime, Integer maxMemberCount,
			Integer minMemberCount, Status status, String remark, Timestamp createTime, String createBy,
			Timestamp updateTime, String updateBy, SalonSetting salonSetting) {
		super();
		this.salonId = salonId;
		this.deleteFlag = deleteFlag;
		this.salonName = salonName;
		this.description = description;
		this.mainTitle = mainTitle;
		this.mainContent = mainContent;
		this.subTitle = subTitle;
		this.joinBeginTime = joinBeginTime;
		this.joinEndTime = joinEndTime;
		this.maxMemberCount = maxMemberCount;
		this.minMemberCount = minMemberCount;
		this.status = status;
		this.remark = remark;
		this.createTime = createTime;
		this.createBy = createBy;
		this.updateTime = updateTime;
		this.updateBy = updateBy;
		this.salonSetting = salonSetting;
	}

	public Integer getSalonId() {
		return salonId;
	}

	public void setSalonId(Integer salonId) {
		this.salonId = salonId;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getSalonName() {
		return salonName;
	}

	public void setSalonName(String salonName) {
		this.salonName = salonName;
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

	public SalonSetting getSalonSetting() {
		return salonSetting;
	}

	public void setSalonSetting(SalonSetting salonSetting) {
		this.salonSetting = salonSetting;
	}
}
