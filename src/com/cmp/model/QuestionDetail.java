package com.cmp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="question_detail")
public class QuestionDetail {
	
	@Id
	@Column(name = "ques_detail_id", nullable = false)
	private String id;

	@Column(name = "content", nullable = false)
	private String content;
	
	@Column(name = "sort", nullable = false)
	private int sort;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ques_id", nullable = false)
    private Question question;

	public QuestionDetail() {
	}
	
	public QuestionDetail(String id, String content, int sort, Question question) {
		super();
		this.id = id;
		this.content = content;
		this.sort = sort;
		this.question = question;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}
