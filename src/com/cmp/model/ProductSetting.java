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
	name = "product_setting",
	uniqueConstraints = {@UniqueConstraint(columnNames = {"product_id"})}
)
public class ProductSetting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id", unique = true)
	private Integer productId;
	
	@Column(name = "order_num", nullable = false)
	private Integer orderNum;
	
	@Column(name = "activation_begin", nullable = true)
	private Timestamp activationBegin;
	
	@Column(name = "activation_end", nullable = true)
	private Timestamp activationEnd;
	
	@Column(name = "create_time", nullable = true)
	private Timestamp createTime;
    
    @Column(name = "create_by", nullable = true)
	private String createBy;
    
    @Column(name = "update_time", nullable = true)
	private Timestamp updateTime;
    
    @Column(name = "update_by", nullable = true)
	private String updateBy;

    @ManyToOne
    @JoinColumn(name = "config_id")
	private ProductBaseConfig productBaseConfig;
    
    @OneToOne(optional = false, mappedBy = "productSetting")
    private ProductInfo productInfo;

	public ProductSetting() {
		super();
	}

	public ProductSetting(Integer productId, Integer orderNum, Timestamp activationBegin, Timestamp activationEnd,
			Timestamp createTime, String createBy, Timestamp updateTime, String updateBy,
			ProductBaseConfig productBaseConfig, ProductInfo productInfo) {
		super();
		this.productId = productId;
		this.orderNum = orderNum;
		this.activationBegin = activationBegin;
		this.activationEnd = activationEnd;
		this.createTime = createTime;
		this.createBy = createBy;
		this.updateTime = updateTime;
		this.updateBy = updateBy;
		this.productBaseConfig = productBaseConfig;
		this.productInfo = productInfo;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
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

	public ProductBaseConfig getProductBaseConfig() {
		return productBaseConfig;
	}

	public void setProductBaseConfig(ProductBaseConfig productBaseConfig) {
		this.productBaseConfig = productBaseConfig;
	}

	public ProductInfo getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}
}
