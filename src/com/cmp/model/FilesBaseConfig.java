package com.cmp.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
	name = "files_base_config",
	uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "config_name"})}
)
public class FilesBaseConfig {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	private Integer id;
	
	@Column(name = "config_name", nullable = true)
	private String configName;
	
	@Column(name = "end_point", nullable = true)
	private String endPoint;
	
	@Column(name = "access_key_id", nullable = true)
	private String accessKeyId;
	
	@Column(name = "access_key_secret", nullable = true)
	private String accessKeySecret;
	
	@Column(name = "bucket_name", nullable = true)
	private String bucketName;
	
	@Column(name = "create_time", nullable = true)
	private Timestamp createTime;
    
    @Column(name = "create_by", nullable = true)
	private String createBy;
    
    @Column(name = "update_time", nullable = true)
	private Timestamp updateTime;
    
    @Column(name = "update_by", nullable = true)
	private String updateBy;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "filesBaseConfig")
    private List<FilesSetting> filesSettings;
    
	public FilesBaseConfig() {
		super();
	}

	public FilesBaseConfig(Integer id, String configName, String endPoint, String accessKeyId, String accessKeySecret,
			String bucketName, Timestamp createTime, String createBy, Timestamp updateTime, String updateBy,
			List<FilesSetting> filesSettings) {
		super();
		this.id = id;
		this.configName = configName;
		this.endPoint = endPoint;
		this.accessKeyId = accessKeyId;
		this.accessKeySecret = accessKeySecret;
		this.bucketName = bucketName;
		this.createTime = createTime;
		this.createBy = createBy;
		this.updateTime = updateTime;
		this.updateBy = updateBy;
		this.filesSettings = filesSettings;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	public String getAccessKeyId() {
		return accessKeyId;
	}

	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}

	public String getAccessKeySecret() {
		return accessKeySecret;
	}

	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
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

	public List<FilesSetting> getFilesSettings() {
		return filesSettings;
	}

	public void setFilesSettings(List<FilesSetting> filesSettings) {
		this.filesSettings = filesSettings;
	}
}
