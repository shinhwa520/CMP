package com.cmp.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(
	name = "files_setting",
	uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})}
)
public class FilesSetting {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String id;
	
	@Column(name = "order_num", nullable = false)
	private Integer orderNum;
	
	@Column(name = "on_top", nullable = false)
	private String onTop;
	
	@Column(name = "activation_begin", nullable = false)
	private Timestamp activationBegin;
	
	@Column(name = "activation_end", nullable = false)
	private Timestamp activationEnd;
	
	@Column(name = "create_time", nullable = false)
	private Timestamp createTime;
    
    @Column(name = "create_by", nullable = false)
	private String createBy;
    
    @Column(name = "update_time", nullable = false)
	private Timestamp updateTime;
    
    @Column(name = "update_by", nullable = false)
	private String updateBy;
    
    @ManyToOne
    @JoinColumn(name = "config_id")
	private FilesBaseConfig filesBaseConfig;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "filesSetting")
	private List<FilesPermission> filesPermissions;

    @OneToOne(optional = false, mappedBy = "filesSetting")
    private FilesPublic filesPublic;
    
    @OneToOne(optional = false, mappedBy = "filesSetting")
    private FilesCustomer filesCustomer;
    
	public FilesSetting() {
		super();
	}

	public FilesSetting(String id, Integer orderNum, String onTop, Timestamp activationBegin, Timestamp activationEnd,
			Timestamp createTime, String createBy, Timestamp updateTime, String updateBy,
			FilesBaseConfig filesBaseConfig, List<FilesPermission> filesPermissions, FilesPublic filesPublic,
			FilesCustomer filesCustomer) {
		super();
		this.id = id;
		this.orderNum = orderNum;
		this.onTop = onTop;
		this.activationBegin = activationBegin;
		this.activationEnd = activationEnd;
		this.createTime = createTime;
		this.createBy = createBy;
		this.updateTime = updateTime;
		this.updateBy = updateBy;
		this.filesBaseConfig = filesBaseConfig;
		this.filesPermissions = filesPermissions;
		this.filesPublic = filesPublic;
		this.filesCustomer = filesCustomer;
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

	public FilesBaseConfig getFilesBaseConfig() {
		return filesBaseConfig;
	}

	public void setFilesBaseConfig(FilesBaseConfig filesBaseConfig) {
		this.filesBaseConfig = filesBaseConfig;
	}

	public List<FilesPermission> getFilesPermissions() {
		return filesPermissions;
	}

	public void setFilesPermissions(List<FilesPermission> filesPermissions) {
		this.filesPermissions = filesPermissions;
	}

	public FilesPublic getFilesPublic() {
		return filesPublic;
	}

	public void setFilesPublic(FilesPublic filesPublic) {
		this.filesPublic = filesPublic;
	}

	public FilesCustomer getFilesCustomer() {
		return filesCustomer;
	}

	public void setFilesCustomer(FilesCustomer filesCustomer) {
		this.filesCustomer = filesCustomer;
	}
}
