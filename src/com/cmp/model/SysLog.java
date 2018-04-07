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
import javax.persistence.UniqueConstraint;

@Entity
@Table(
	name = "sys_log",
	uniqueConstraints = {@UniqueConstraint(columnNames = {"sys_log_id"})}
)
public class SysLog implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "sys_log_id", unique = true)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@Column(name = "action", nullable = true)
	private String action;
	
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = true)
	private Date createTime;
	
	public SysLog() {
	}
	
	public SysLog(User user, String action) {
		super();
		Date current = new Date();
		this.id = current.getTime();
		this.user = user;
		this.action = action;
		this.createTime = current;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
}
