package com.cmp.dao.vo;

import java.util.List;

public class FileDAOVO {

	private Integer seqNo;
	private String originFileName;
	private String upperFileName;
	private List<String> fileExtension;
	private String fileCategory;
	
	private Integer custId;
	private Integer productId;
	private Integer visitId;
	private Integer referenceId;
	
	private boolean isAdmin;
	
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

	public Integer getVisitId() {
		return visitId;
	}

	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}

	public List<String> getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(List<String> fileExtension) {
		this.fileExtension = fileExtension;
	}

	public String getFileCategory() {
		return fileCategory;
	}

	public void setFileCategory(String fileCategory) {
		this.fileCategory = fileCategory;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Integer getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(Integer referenceId) {
		this.referenceId = referenceId;
	}
}
