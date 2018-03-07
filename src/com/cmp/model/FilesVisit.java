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
	name = "files_visit",
	uniqueConstraints = {@UniqueConstraint(columnNames = {"seq_no"})}
)
public class FilesVisit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq_no", unique = true)
	private Integer seqNo;
	
	@Column(name = "upper_file_name", nullable = false)
	private String upperFileName;
	
	@Column(name = "origin_file_name", nullable = false)
	private String originFileName;
	
	@Column(name = "file_name", nullable = false)
	private String fileName;
	
	@Column(name = "file_extension", nullable = false)
	private String fileExtension;
	
	@Column(name = "file_description", nullable = true)
	private String fileDescription;
	
	@Column(name = "file_size", nullable = true)
	private Integer fileSize;
	
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
    
    @Column(name = "visit_id", nullable = false)
    private Integer visitId;

	public FilesVisit() {
		super();
	}

	public FilesVisit(Integer seqNo, String upperFileName, String originFileName, String fileName, String fileExtension,
			String fileDescription, Integer fileSize, Integer downloadTimes, Timestamp createTime, String createBy,
			Timestamp updateTime, String updateBy, FilesSetting filesSetting, Integer visitId) {
		super();
		this.seqNo = seqNo;
		this.upperFileName = upperFileName;
		this.originFileName = originFileName;
		this.fileName = fileName;
		this.fileExtension = fileExtension;
		this.fileDescription = fileDescription;
		this.fileSize = fileSize;
		this.downloadTimes = downloadTimes;
		this.createTime = createTime;
		this.createBy = createBy;
		this.updateTime = updateTime;
		this.updateBy = updateBy;
		this.filesSetting = filesSetting;
		this.visitId = visitId;
	}

	public Integer getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	public String getUpperFileName() {
		return upperFileName;
	}

	public void setUpperFileName(String upperFileName) {
		this.upperFileName = upperFileName;
	}

	public String getOriginFileName() {
		return originFileName;
	}

	public void setOriginFileName(String originFileName) {
		this.originFileName = originFileName;
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

	public Integer getFileSize() {
		return fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
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

	public Integer getVisitId() {
		return visitId;
	}

	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}
}