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
@Table(name="user_kpi")
public class UserKpi implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_kpi_id", nullable = false)
	private String id;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


	@Column(name = "year_month", nullable = false)
	private String year_month;
	
	@Column(name = "agent_user", nullable = false)
	private int agent_user=0;
	
	@Column(name = "agent_cust", nullable = false)
	private int agent_cust=0;
	
	@Column(name = "volume", nullable = false)
	private int volume=0;
	
    @Column(name = "create_by", nullable = true)
	private String createBy;
	
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "create_date_time", nullable = false)
	private Date createDateTime;
	
    @Column(name = "update_by", nullable = true)
	private String updateBy;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "update_date_time", nullable = false)
	private Date updateDateTime;
	
    
    
	public UserKpi() {
	}

	public UserKpi(String id, User user, String year_month, int agent_user, int agent_cust, int volume, String createBy,
			Date createDateTime, String updateBy, Date updateDateTime) {
		super();
		this.id = id;
		this.user = user;
		this.year_month = year_month;
		this.agent_user = agent_user;
		this.agent_cust = agent_cust;
		this.volume = volume;
		this.createBy = createBy;
		this.createDateTime = createDateTime;
		this.updateBy = updateBy;
		this.updateDateTime = updateDateTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getYear_month() {
		return year_month;
	}

	public void setYear_month(String year_month) {
		this.year_month = year_month;
	}

	public int getAgent_user() {
		return agent_user;
	}

	public void setAgent_user(int agent_user) {
		this.agent_user = agent_user;
	}

	public int getAgent_cust() {
		return agent_cust;
	}

	public void setAgent_cust(int agent_cust) {
		this.agent_cust = agent_cust;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	


}
