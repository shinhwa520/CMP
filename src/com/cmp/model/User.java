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
import javax.persistence.Transient;

@Entity
@Table(name="user")
public class User implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

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
    
    @Column(name = "weChat", nullable = true)
	private String weChat;
	
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "channel_id", nullable = true)
    private User channel;
    
    @Column(name = "reward", nullable = true)
	private Integer reward;
    
    @Column(name = "create_by", nullable = true)
	private String createBy;
	
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "create_date_time", nullable = false)
	private Date createDateTime;
	
    @Column(name = "update_by", nullable = true)
	private String updateBy;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "update_date_time", nullable = false)
	private Date updateDateTime;
    
	@Transient
	private String[] roles;
	
	@Transient
	private int agent_user=0;	//預計仲介user數
	@Transient
	private int agent_cust=0;	//預計仲介cust數
	@Transient
	private int volume=0;		//預計成交量
	@Transient
	private int _agent_user=0;	//實際仲介user數
	@Transient
	private int _agent_cust=0;	//實際仲介cust數
	@Transient
	private int _volume=0;		//實際成交量
	
	
	public User() {
	}
	
	public User(String email, Role role, Status status) {
		super();
		Date current = new Date();
		this.id = String.valueOf(current.getTime());
		this.email = email;
		this.role = role;
		this.status = status;
		this.createBy = "REG";
		this.createDateTime = current;
		this.updateBy = "REG";
		this.updateDateTime = current;
	}

	public User(String id, String name, String account, String password, Role role, Status status, String phone,
			String email, String weChat, User channel, Integer reward, String createBy, Date createDateTime,
			String updateBy, Date updateDateTime) {
		super();
		this.id = id;
		this.name = name;
		this.account = account;
		this.password = password;
		this.role = role;
		this.status = status;
		this.phone = phone;
		this.email = email;
		this.weChat = weChat;
		this.channel = channel;
		this.reward = reward;
		this.createBy = createBy;
		this.createDateTime = createDateTime;
		this.updateBy = updateBy;
		this.updateDateTime = updateDateTime;
	}

	public User(String id, String name, String account, String password, Role role, Status status, String phone,
			String email, User channel, String createBy, Date createDateTime, String updateBy, Date updateDateTime) {
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
		this.createBy = createBy;
		this.createDateTime = createDateTime;
		this.updateBy = updateBy;
		this.updateDateTime = updateDateTime;
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

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public int getAgent_user() {
		return agent_user;
	}

	public void setAgent_user(int agent_user) {
		this.agent_user = agent_user;
	}

	public int getAgent_cust() {
		return agent_cust;
	}

	public void setAgent_cust(int agent_cust) {
		this.agent_cust = agent_cust;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public int get_agent_user() {
		return _agent_user;
	}

	public void set_agent_user(int _agent_user) {
		this._agent_user = _agent_user;
	}

	public int get_agent_cust() {
		return _agent_cust;
	}

	public void set_agent_cust(int _agent_cust) {
		this._agent_cust = _agent_cust;
	}

	public int get_volume() {
		return _volume;
	}

	public void set_volume(int _volume) {
		this._volume = _volume;
	}


}
