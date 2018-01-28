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
@Table(name="user_ques")
public class UserQues implements java.io.Serializable {
	
	@Id
	@Column(name = "user_ques_id", nullable = false)
	private String id;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ques_detail_id", nullable = false)
    private QuestionDetail questionDetail;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
	
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "create_date_time", nullable = false)
	private Date createDateTime;
    
	public UserQues() {
	}
    
	public UserQues(String id, QuestionDetail questionDetail, User user, Date createDateTime) {
		super();
		this.id = id;
		this.questionDetail = questionDetail;
		this.user = user;
		this.createDateTime = createDateTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public QuestionDetail getQuestionDetail() {
		return questionDetail;
	}

	public void setQuestionDetail(QuestionDetail questionDetail) {
		this.questionDetail = questionDetail;
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
