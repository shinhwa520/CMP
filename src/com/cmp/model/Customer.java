package com.cmp.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CUSTOMER")
public class Customer implements java.io.Serializable {

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
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", nullable = true)
    private Status status;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @Column(name = "create_time", nullable = true)
	private Timestamp createTime;
    
    @Column(name = "create_by", nullable = true)
	private String createBy;
    
    @Column(name = "update_time", nullable = true)
	private Timestamp updateTime;
    
    @Column(name = "update_by", nullable = true)
	private String updateBy;
    
    @Column(name = "address", nullable = true)
	private String address;
    
    @Column(name = "birthday", nullable = true)
	private Date birthday;
    
    @Column(name = "gender", nullable = true)
	private String gender;
    
    @Column(name = "email", nullable = true)
	private String email;
    
    @Column(name = "weChat", nullable = true)
	private String weChat;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
    private List<FilesCustomer> filesCustomers;
    
	public Customer() {
	}

	public Customer(int id, String name, String city, String phone, Status status, User user, Timestamp createTime,
			String createBy, Timestamp updateTime, String updateBy, String address, Date birthday, String gender,
			String email, String weChat, List<FilesCustomer> filesCustomers) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.phone = phone;
		this.status = status;
		this.user = user;
		this.createTime = createTime;
		this.createBy = createBy;
		this.updateTime = updateTime;
		this.updateBy = updateBy;
		this.address = address;
		this.birthday = birthday;
		this.gender = gender;
		this.email = email;
		this.weChat = weChat;
		this.filesCustomers = filesCustomers;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWeChat() {
		return weChat;
	}

	public void setWeChat(String weChat) {
		this.weChat = weChat;
	}

	public List<FilesCustomer> getFilesCustomers() {
		return filesCustomers;
	}

	public void setFilesCustomers(List<FilesCustomer> filesCustomers) {
		this.filesCustomers = filesCustomers;
	}
}