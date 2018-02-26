package com.cmp.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	name = "files_customer",
	uniqueConstraints = {@UniqueConstraint(columnNames = {"seq_no"})}
)
public class FilesCustomer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq_no", unique = true)
	private Integer seqNo;
	
	@Column(name = "file_name", nullable = false)
	private String fileName;
	
	@Column(name = "file_extension", nullable = false)
	private String fileExtension;
	
	@Column(name = "file_description", nullable = true)
	private String fileDescription;
	
	@Column(name = "download_times", nullable = true)
	private Integer downloadTimes;
	
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
	private FilesSetting filesSetting;
    
    @ManyToOne
    @JoinColumn(name = "cust_id")
	private Customer customer;

	public FilesCustomer() {
		super();
	}

	public FilesCustomer(Integer seqNo, String fileName, String fileExtension, String fileDescription,
			Integer downloadTimes, Timestamp createTime, String createBy, Timestamp updateTime, String updateBy,
			FilesSetting filesSetting, Customer customer) {
		super();
		this.seqNo = seqNo;
		this.fileName = fileName;
		this.fileExtension = fileExtension;
		this.fileDescription = fileDescription;
		this.downloadTimes = downloadTimes;
		this.createTime = createTime;
		this.createBy = createBy;
		this.updateTime = updateTime;
		this.updateBy = updateBy;
		this.filesSetting = filesSetting;
		this.customer = customer;
	}

	public Integer getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public String getFileDescription() {
		return fileDescription;
	}

	public void setFileDescription(String fileDescription) {
		this.fileDescription = fileDescription;
	}

	public Integer getDownloadTimes() {
		return downloadTimes;
	}

	public void setDownloadTimes(Integer downloadTimes) {
		this.downloadTimes = downloadTimes;
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

	public FilesSetting getFilesSetting() {
		return filesSetting;
	}

	public void setFilesSetting(FilesSetting filesSetting) {
		this.filesSetting = filesSetting;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
