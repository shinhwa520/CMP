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
	name = "product_base_config",
	uniqueConstraints = {@UniqueConstraint(columnNames = {"config_id"})}
)
public class ProductBaseConfig {

	@Id
	@Column(name = "config_id", unique = true)
	private Integer configId;
	
	@Column(name = "url_kpi", nullable = true)
	private String productName;
	
	@Column(name = "url_intro_page", nullable = true)
	private String mainTitle;
	
	@Column(name = "url_download", nullable = true)
	private String mainContent;
	
	@Column(name = "create_time", nullable = true)
	private Timestamp createTime;
    
    @Column(name = "create_by", nullable = true)
	private String createBy;
    
    @Column(name = "update_time", nullable = true)
	private Timestamp updateTime;
    
    @Column(name = "update_by", nullable = true)
	private String updateBy;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "productBaseConfig")
    private List<ProductSetting> productSetting;

	public ProductBaseConfig() {
		super();
	}

	public ProductBaseConfig(Integer configId, String productName, String mainTitle, String mainContent,
			Timestamp createTime, String createBy, Timestamp updateTime, String updateBy,
			List<ProductSetting> productSetting) {
		super();
		this.configId = configId;
		this.productName = productName;
		this.mainTitle = mainTitle;
		this.mainContent = mainContent;
		this.createTime = createTime;
		this.createBy = createBy;
		this.updateTime = updateTime;
		this.updateBy = updateBy;
		this.productSetting = productSetting;
	}

	public Integer getConfigId() {
		return configId;
	}

	public void setConfigId(Integer configId) {
		this.configId = configId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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

	public List<ProductSetting> getProductSetting() {
		return productSetting;
	}

	public void setProductSetting(List<ProductSetting> productSetting) {
		this.productSetting = productSetting;
	}
}
