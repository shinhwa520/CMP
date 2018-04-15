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
	name = "contact",
	uniqueConstraints = {@UniqueConstraint(columnNames = {"contact_id"})}
)
public class Contact implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "contact_id", unique = true)
	private Long id;	
	
	@Column(name = "subject", nullable = true)
	private String subject;
	
	@Column(name = "content", nullable = true)
	private String content;
    
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "create_by", nullable = false)
	private User createBy;
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = true)
	private Date createTime;

	public Contact() {
	}

	//for 新增
	public Contact(String subject, String content, User createBy) {
		super();
		
		Date current = new Date();
		this.id = current.getTime();
		this.subject = subject;
		this.content = content;
		this.createBy = createBy;
		this.createTime = current;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getCreateBy() {
		return createBy;
	}

	public void setCreateBy(User createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


}
