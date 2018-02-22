package com.cmp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name="txLog")
public class TxLog implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "tx_id", nullable = false)
	private String id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cust_id", nullable = false)
    private Customer cust;

	@Column(name = "name", nullable = true)
	private String name;
	
	@Column(name = "desc", nullable = true)
	private String desc;

	@Column(name = "price", nullable = false)
	private int price = 0;
	
	@Column(name = "commission", nullable = false)
	private int commission = 0;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "tx_date_time", nullable = false)
	private Date txDateTime;
	
	@Column(name = "third_obj", nullable = true)
	private String thirdObj;
	
	@Column(name = "third_id", nullable = true)
	private String thirdId;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "create_date_time", nullable = false)
	private Date createDateTime;
	
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "update_date_time", nullable = false)
	private Date updateDateTime;

	public TxLog() {
	}
    
	public TxLog(String id, Customer cust, String name, String desc, int price, int commission, Date txDateTime,
			String thirdObj, String thirdId, Date createDateTime, Date updateDateTime) {
		super();
		this.id = id;
		this.cust = cust;
		this.name = name;
		this.desc = desc;
		this.price = price;
		this.commission = commission;
		this.txDateTime = txDateTime;
		this.thirdObj = thirdObj;
		this.thirdId = thirdId;
		this.createDateTime = createDateTime;
		this.updateDateTime = updateDateTime;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Customer getCust() {
		return cust;
	}

	public void setCust(Customer cust) {
		this.cust = cust;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCommission() {
		return commission;
	}

	public void setCommission(int commission) {
		this.commission = commission;
	}

	public Date getTxDateTime() {
		return txDateTime;
	}

	public void setTxDateTime(Date txDateTime) {
		this.txDateTime = txDateTime;
	}

	public String getThirdObj() {
		return thirdObj;
	}

	public void setThirdObj(String thirdObj) {
		this.thirdObj = thirdObj;
	}

	public String getThirdId() {
		return thirdId;
	}

	public void setThirdId(String thirdId) {
		this.thirdId = thirdId;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public Date getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	

}
