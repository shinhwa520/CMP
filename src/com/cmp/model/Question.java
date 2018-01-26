package com.cmp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="question")
public class Question implements Comparable<Question>{
	
	@Id
	@Column(name = "ques_id", nullable = false)
	private String id;
	

	@Column(name = "content", nullable = false)
	private String content;
	
	@Column(name = "sort", nullable = false)
	private int sort;

	
	public Question() {
	}
	public Question(String id, String content, int sort) {
		super();
		this.id = id;
		this.content = content;
		this.sort = sort;
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
	
	@Override
	public int compareTo(Question o) {
	    if(this.sort > o.getSort()){return 1;}
	    if(this.sort < o.getSort()){return -1;}
	    else{return 0;}
	}

}
