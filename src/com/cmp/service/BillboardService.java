package com.cmp.service;

import java.util.List;

import com.cmp.service.vo.BillboardServiceVO;

public interface BillboardService {

	public List<BillboardServiceVO> findAllBillboardRecords(boolean isAdmin, Integer startRow, Integer pageLength);
	
	public void modifyBillboardRecord(BillboardServiceVO bsVO);
	
	public BillboardServiceVO getBillboardBySeqNo(Integer seqNos);
	
	public void deleteBillboardRecords(Integer[] seqNo);
}
