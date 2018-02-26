package com.cmp.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.cmp.service.vo.FileServiceVO;

public class FileForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1474030491538363541L;

	private List<FileServiceVO> fileList = new ArrayList<FileServiceVO>();
	private boolean adminRole;
	private String fileType;
	private Integer[] delChkbox;
	
	public List<FileServiceVO> getFileList() {
		return fileList;
	}
	public void setFileList(List<FileServiceVO> fileList) {
		this.fileList = fileList;
	}
	public boolean isAdminRole() {
		return adminRole;
	}
	public void setAdminRole(boolean adminRole) {
		this.adminRole = adminRole;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public Integer[] getDelChkbox() {
		return delChkbox;
	}
	public void setDelChkbox(Integer[] delChkbox) {
		this.delChkbox = delChkbox;
	}
}
