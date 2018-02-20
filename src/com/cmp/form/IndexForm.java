package com.cmp.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.cmp.service.vo.BillboardServiceVO;

public class IndexForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8961388957429621233L;
	
	private List<BillboardServiceVO> billboardList = new ArrayList<BillboardServiceVO>();

	public List<BillboardServiceVO> getBillboardList() {
		return billboardList;
	}

	public void setBillboardList(List<BillboardServiceVO> billboardList) {
		this.billboardList = billboardList;
	}
}
