package com.cmp.service.vo;

import java.util.Date;

import javax.persistence.Column;

import com.cmp.model.Status;

public class CustServiceVO {
	
	private Integer productId;

	private String productName;
	private String mainTitle;
	private String mainContent;
	private String subTitle;
	private String subContent;
	
	private String urlKpi;
	private String urlIntroPage;
	private String urlDownload;

	private Integer cust_Id;
	
	private int id;
	private String name;
	private String gender;
	private Date birthday;
	private String phone;
	private String email;
	private String weChat;
	private Status status;
	private boolean isJoined;

	public Integer getCust_Id() { return cust_Id; }
	public void setCust_Id(Integer cust_Id) { this.cust_Id = cust_Id; }
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getMainTitle() {
		return mainTitle;
	}
	public void setMainTitle(String mainTitle) {
		this.mainTitle = mainTitle;
	}
	public String getMainContent() {
		return mainContent;
	}
	public void setMainContent(String mainContent) {
		this.mainContent = mainContent;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public String getSubContent() {
		return subContent;
	}
	public void setSubContent(String subContent) {
		this.subContent = subContent;
	}
	public String getUrlKpi() {
		return urlKpi;
	}
	public void setUrlKpi(String urlKpi) {
		this.urlKpi = urlKpi;
	}
	public String getUrlIntroPage() {
		return urlIntroPage;
	}
	public void setUrlIntroPage(String urlIntroPage) {
		this.urlIntroPage = urlIntroPage;
	}
	public String getUrlDownload() {
		return urlDownload;
	}
	public void setUrlDownload(String urlDownload) {
		this.urlDownload = urlDownload;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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
	public String getWeChat() {
		return weChat;
	}
	public void setWeChat(String weChat) {
		this.weChat = weChat;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public boolean isJoined() {
		return isJoined;
	}
	public void setJoined(boolean isJoined) {
		this.isJoined = isJoined;
	}
}
