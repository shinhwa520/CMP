package com.cmp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="CUSTOMER")
public class Customer {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cust_id", nullable = false)
	private int id;
	
    @Column(name = "cust_name", nullable = false)
	private String name;

    @Column(name = "city", nullable = true)
	private String city;
    
    @Column(name = "phone", nullable = true)
	private String phone;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id", nullable = false)
    private User channel;
    
	public Customer() {
	}
	
	public Customer(int id, String name, String city, String phone, User channel) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.phone = phone;
		this.channel = channel;
	}

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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public User getChannel() {
		return channel;
	}

	public void setChannel(User channel) {
		this.channel = channel;
	}

}
