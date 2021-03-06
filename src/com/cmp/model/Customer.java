package com.cmp.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

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
public class Customer implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cust_id", nullable = false)
	private int id;
	
    @Column(name = "cust_name", nullable = false)
	private String name;

    @Column(name = "gender", nullable = true)
	private String gender;
    
    @Column(name = "birthday", nullable = true)
	private Date birthday;
    
    @Column(name = "phone", nullable = true)
	private String phone;
    
    @Column(name = "email", nullable = true)
	private String email;
    
    @Column(name = "weChat", nullable = true)
	private String weChat;

    @Column(name = "identity1_id", nullable = true)
	private Integer identity1_id=0;
    
    @Column(name = "identity1_code", nullable = true)
	private String identity1_code;
    
    @Column(name = "identity1_name", nullable = true)
	private String identity1_name;
    
    @Column(name = "identity2_id", nullable = true)
	private Integer identity2_id=0;
    
    @Column(name = "identity2_code", nullable = true)
	private String identity2_code;
    
    @Column(name = "identity2_name", nullable = true)
	private String identity2_name;
    
    @Column(name = "city", nullable = true)
	private String city;
    
    @Column(name = "census", nullable = true)
	private String census;
    
    @Column(name = "address", nullable = true)
	private String address;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", nullable = true)
    private Status status;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @Column(name = "remark", nullable = true)
	private String remark;
	
    @Column(name = "create_time", nullable = true)
	private Timestamp createTime;
    
    @Column(name = "create_by", nullable = true)
	private String createBy;
    
    @Column(name = "update_time", nullable = true)
	private Timestamp updateTime;
    
    @Column(name = "update_by", nullable = true)
	private String updateBy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "data_status_id", nullable = false)
	private Status dataStatus;
    
    
	public Customer() {
	}

	public Customer(int id, String name, String gender, Date birthday, String phone, String email, String weChat,
			Integer identity1_id, String identity1_code, String identity1_name, Integer identity2_id,
			String identity2_code, String identity2_name, String city, String census, String address, Status status,
			User user, String remark, Timestamp createTime, String createBy, Timestamp updateTime, String updateBy, Status dataStatus) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.phone = phone;
		this.email = email;
		this.weChat = weChat;
		this.identity1_id = identity1_id;
		this.identity1_code = identity1_code;
		this.identity1_name = identity1_name;
		this.identity2_id = identity2_id;
		this.identity2_code = identity2_code;
		this.identity2_name = identity2_name;
		this.city = city;
		this.census = census;
		this.address = address;
		this.status = status;
		this.user = user;
		this.remark = remark;
		this.createTime = createTime;
		this.createBy = createBy;
		this.updateTime = updateTime;
		this.updateBy = updateBy;
		this.dataStatus = dataStatus;
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

	public Integer getIdentity1_id() {
		return identity1_id;
	}

	public void setIdentity1_id(Integer identity1_id) {
		this.identity1_id = identity1_id;
	}

	public String getIdentity1_code() {
		return identity1_code;
	}

	public void setIdentity1_code(String identity1_code) {
		this.identity1_code = identity1_code;
	}

	public Integer getIdentity2_id() {
		return identity2_id;
	}

	public void setIdentity2_id(Integer identity2_id) {
		this.identity2_id = identity2_id;
	}

	public String getIdentity2_code() {
		return identity2_code;
	}

	public void setIdentity2_code(String identity2_code) {
		this.identity2_code = identity2_code;
	}

	public String getCensus() {
		return census;
	}

	public void setCensus(String census) {
		this.census = census;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}



	public String getIdentity1_name() {
		return identity1_name;
	}



	public void setIdentity1_name(String identity1_name) {
		this.identity1_name = identity1_name;
	}



	public String getIdentity2_name() {
		return identity2_name;
	}



	public void setIdentity2_name(String identity2_name) {
		this.identity2_name = identity2_name;
	}
	
	public String getBirthdayStr() {
		if(null==getBirthday()){
			return null;
		}else{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // HH是24小時制，hh是12小時制
			return sdf.format(getBirthday());
		}
	}
	
	public String getIdentity1Str() {
		if(null==identity1_id)
			return "未輸入";
		switch (identity1_id){
			case 1:
				return "身分證";
			case 2:
				return "護照";
			case 3:
				return "台胞證";
			default:
				return "未輸入";
		}
	}
	
	public String getIdentity2Str() {
		if(null==identity2_id)
			return "未輸入";
		switch (identity2_id){
			case 1:
				return "身分證";
			case 2:
				return "護照";
			case 3:
				return "台胞證";
			default:
				return "未輸入";
		}
	}

	public Status getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(Status dataStatus) {
		this.dataStatus = dataStatus;
	}
}