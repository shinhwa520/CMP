package com.cmp.service.vo;

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
}
