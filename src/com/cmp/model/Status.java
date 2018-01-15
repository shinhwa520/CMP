package com.cmp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="status")
public class Status {
	public Status() {
		
	}
	public Status(int id, String name, String type, int sort) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.sort = sort;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "status_id", nullable = false)
	private int id;
	
    @Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "type", nullable = false)
	private String type;
	
	@Column(name = "sort", nullable = false)
	private int sort;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

}
