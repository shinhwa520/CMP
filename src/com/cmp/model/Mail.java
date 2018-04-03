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
	name = "mail",
	uniqueConstraints = {@UniqueConstraint(columnNames = {"mail_id"})}
)
public class Mail implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "mail_id", unique = true)
	private Long id;	

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mail_from", nullable = false)
	private User mailFrom;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mail_to", nullable = false)
	private User mailTo;
	
	@Column(name = "subject", nullable = true)
	private String subject;
	
	@Column(name = "content", nullable = true)
	private String content;
	
	@Column(name = "type", nullable = true)
	private int type=0;
	
	@Column(name = "flag", nullable = true)
	private int flag=0;
	
	@Column(name = "beenRead", nullable = true)
	private boolean beenRead=false;
	
	@Column(name = "alive", nullable = true)
	private boolean alive=true;
	
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = true)
	private Date createTime;
    
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "update_by", nullable = false)
	private User updateBy;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "update_time", nullable = true)
	private Date updateTime;


	public Mail() {
	}
    
	//for 新增
	public Mail(User mailFrom, User mailTo, String subject, String content) {
		super();
		
		Date current = new Date();
		this.id = current.getTime();
		this.mailFrom = mailFrom;
		this.mailTo = mailTo;
		this.subject = subject;
		this.content = content;
		this.createTime = current;
		this.updateBy = mailFrom;
		this.updateTime = current;
	}
	
	public Mail(Long id, User mailFrom, User mailTo, String subject, String content, int type, int flag, boolean beenRead,
			boolean alive, Date createTime, User updateBy, Date updateTime) {
		super();
		this.id = id;
		this.mailFrom = mailFrom;
		this.mailTo = mailTo;
		this.subject = subject;
		this.content = content;
		this.type = type;
		this.flag = flag;
		this.beenRead = beenRead;
		this.alive = alive;
		this.createTime = createTime;
		this.updateBy = updateBy;
		this.updateTime = updateTime;
	}

    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getMailFrom() {
		return mailFrom;
	}

	public void setMailFrom(User mailFrom) {
		this.mailFrom = mailFrom;
	}

	public User getMailTo() {
		return mailTo;
	}

	public void setMailTo(User mailTo) {
		this.mailTo = mailTo;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}


	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public User getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(User updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public boolean isBeenRead() {
		return beenRead;
	}

	public void setBeenRead(boolean beenRead) {
		this.beenRead = beenRead;
	}
}
