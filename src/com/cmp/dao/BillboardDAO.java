package com.cmp.dao;

import java.util.List;

import com.cmp.dao.vo.BillboardDAOVO;
import com.cmp.model.BillboardContent;
import com.cmp.model.BillboardPermission;
import com.cmp.model.BillboardSetting;

public interface BillboardDAO {

	public List<BillboardContent> findAllBillboardRecords(boolean isAdmin, Integer startRow, Integer pageLength);
	
	public List<BillboardContent> findBillboardRecordsByDAOVO(BillboardDAOVO billboardDAOVO);
	
	public void modifyBillboardRecord(boolean isAdd, BillboardContent billContent, BillboardSetting billSetting, List<BillboardPermission> oldBillPermissions, List<BillboardPermission> newBillPermissions);
	
	/**
	 * 
	 * @param keys
	 * key[0] = BillboardContent
	 * key[1] = BillboardSetting
	 * key[2] = BillboardPermission
	 */
	public void deleteBillboardRecords(List<Object> modelList);
}
