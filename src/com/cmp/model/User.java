package com.cmp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="user")
public class User {
	
	public User() {
	}
	
	public User(String email) {
		super();
		this.id = String.valueOf(new Date().getTime());
		this.email = email;
	}

	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", nullable = false)
	private String id;

	@Column(name = "user_name", nullable = true)
	private String name;
	
	@Column(name = "account", nullable = true)
	private String account;
	
	@Column(name = "password", nullable = true)
	private String password;
	
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = true)
    private Role role;
	
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", nullable = true)
    private Status status;
	
	@Column(name = "phone", nullable = true)
	private String phone;
    
	@Column(name = "email", nullable = true)
	private String email;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "channel_id", nullable = true)
    private User channel;
    
	@Transient
	private String[] roles;
	
	public User(String id, String name, String account, String password, Role role, Status status, String phone,
			String email, User channel) {
		super();
		this.id = id;
		this.name = name;
		this.account = account;
		this.password = password;
		this.role = role;
		this.status = status;
		this.phone = phone;
		this.email = email;
		this.channel = channel;
	}

	public User(String name, String password, String... roles) {
		this.name = name;
		this.password = password;
		this.roles = roles;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User getChannel() {
		return channel;
	}

	public void setChannel(User channel) {
		this.channel = channel;
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}


}
