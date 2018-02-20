package com.cmp.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.cmp.service.vo.BillboardServiceVO;

public class BillboardForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4456208061256878175L;
	
	private List<BillboardServiceVO> billboardList = new ArrayList<BillboardServiceVO>();
	private String title;
	private String onTopChkbox;
	private String isOpenedChkbox;
	private String beginDateStr;
	private String beginTimeStr;
	private String endDateStr;
	private String endTimeStr;
	private String content;
	private Integer[] delChkbox;
	private boolean adminRole;

	public List<BillboardServiceVO> getBillboardList() {
		return billboardList;
	}

	public void setBillboardList(List<BillboardServiceVO> billboardList) {
		this.billboardList = billboardList;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOnTopChkbox() {
		return onTopChkbox;
	}

	public void setOnTopChkbox(String onTopChkbox) {
		this.onTopChkbox = onTopChkbox;
	}

	public String getIsOpenedChkbox() {
		return isOpenedChkbox;
	}

	public void setIsOpenedChkbox(String isOpenedChkbox) {
		this.isOpenedChkbox = isOpenedChkbox;
	}

	public String getBeginDateStr() {
		return beginDateStr;
	}

	public void setBeginDateStr(String beginDateStr) {
		this.beginDateStr = beginDateStr;
	}

	public String getBeginTimeStr() {
		return beginTimeStr;
	}

	public void setBeginTimeStr(String beginTimeStr) {
		this.beginTimeStr = beginTimeStr;
	}

	public String getEndDateStr() {
		return endDateStr;
	}

	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}

	public String getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer[] getDelChkbox() {
		return delChkbox;
	}

	public void setDelChkbox(Integer[] delChkbox) {
		this.delChkbox = delChkbox;
	}

	public boolean isAdminRole() {
		return adminRole;
	}

	public void setAdminRole(boolean adminRole) {
		this.adminRole = adminRole;
	}
}
