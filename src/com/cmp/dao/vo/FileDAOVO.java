package com.cmp.dao.vo;

public class FileDAOVO {

	private Integer seqNo;
	private String originFileName;
	private String upperFileName;

	private Integer custId;
	private Integer productId;
	
	public Integer getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	public String getOriginFileName() {
		return originFileName;
	}

	public void setOriginFileName(String originFileName) {
		this.originFileName = originFileName;
	}

	public String getUpperFileName() {
		return upperFileName;
	}

	public void setUpperFileName(String upperFileName) {
		this.upperFileName = upperFileName;
	}

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}
}
