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
}
