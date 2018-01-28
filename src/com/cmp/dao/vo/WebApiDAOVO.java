package com.cmp.dao.vo;

public class WebApiDAOVO {

	private String webName;
	private String masterSeqNo;
	private Integer startRow;
	private Integer pageLength;
	
	public String getWebName() {
		return webName;
	}
	public void setWebName(String webName) {
		this.webName = webName;
	}
	public String getMasterSeqNo() {
		return masterSeqNo;
	}
	public void setMasterSeqNo(String masterSeqNo) {
		this.masterSeqNo = masterSeqNo;
	}
	public Integer getStartRow() {
		return startRow;
	}
	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}
	public Integer getPageLength() {
		return pageLength;
	}
	public void setPageLength(Integer pageLength) {
		this.pageLength = pageLength;
	}
}
