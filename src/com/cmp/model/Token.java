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
@Table(name="token")
public class Token {
	
	@Id
	@Column(name = "token_id", nullable = false)
	private String id;
	

	@Column(name = "type", nullable = false)
	private String type;
	

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "create_date_time", nullable = false)
	private Date createDateTime;
	
	public Token() {
	}
	
    public Token(String id, String type, User user, Date createDateTime) {
		super();
		this.id = id;
		this.type = type;
		this.user = user;
		this.createDateTime = createDateTime;
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Date getCreateDateTime() {
		return createDateTime;
	}


	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}


}
