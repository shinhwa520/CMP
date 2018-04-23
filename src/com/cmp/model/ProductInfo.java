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
	name = "product_info",
	uniqueConstraints = {@UniqueConstraint(columnNames = {"product_id"})}
)
public class ProductInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id", unique = true)
	private Integer productId;
	
	@Column(name = "product_name", nullable = true)
	private String productName;
	
	@Column(name = "eng_name", nullable = true)
	private String engName;
	
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
    @JoinColumn(name = "product_id")
	private ProductSetting productSetting;

	public ProductInfo() {
		super();
	}

	public ProductInfo(Integer productId, String productName, String engName, String mainTitle, String mainContent,
			String subTitle, String subContent, Timestamp createTime, String createBy, Timestamp updateTime,
			String updateBy, ProductSetting productSetting) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.engName = engName;
		this.mainTitle = mainTitle;
		this.mainContent = mainContent;
		this.subTitle = subTitle;
		this.subContent = subContent;
		this.createTime = createTime;
		this.createBy = createBy;
		this.updateTime = updateTime;
		this.updateBy = updateBy;
		this.productSetting = productSetting;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getEngName() {
		return engName;
	}

	public void setEngName(String engName) {
		this.engName = engName;
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

	public ProductSetting getProductSetting() {
		return productSetting;
	}

	public void setProductSetting(ProductSetting productSetting) {
		this.productSetting = productSetting;
	}
}
