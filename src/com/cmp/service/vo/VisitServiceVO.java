package com.cmp.service.vo;

import java.util.List;

import com.cmp.model.FilesVisit;
import com.cmp.model.VisitInfo;

public class VisitServiceVO {

	private Integer visitId;
	private Integer userId;
	private Integer fileSeqNo;
	private String fileCategory;
	private VisitInfo visitInfo;
	private List<FilesVisit> filesVisit;
	
	public Integer getVisitId() {
		return visitId;
	}
	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getFileSeqNo() {
		return fileSeqNo;
	}
	public void setFileSeqNo(Integer fileSeqNo) {
		this.fileSeqNo = fileSeqNo;
	}
	public String getFileCategory() {
		return fileCategory;
	}
	public void setFileCategory(String fileCategory) {
		this.fileCategory = fileCategory;
	}
	public VisitInfo getVisitInfo() {
		return visitInfo;
	}
	public void setVisitInfo(VisitInfo visitInfo) {
		this.visitInfo = visitInfo;
	}
	public List<FilesVisit> getFilesVisit() {
		return filesVisit;
	}
	public void setFilesVisit(List<FilesVisit> filesVisit) {
		this.filesVisit = filesVisit;
	}
}
