package com.cmp.service;

import java.util.List;

import com.cmp.service.vo.VisitServiceVO;

public interface VisitService {
	
	public VisitServiceVO findVisit(VisitServiceVO vsVO);
	
	public VisitServiceVO getVisitById(Integer visitId);

	public boolean saveVisit(VisitServiceVO vsVO);
	
	public boolean saveVisitDetail(VisitServiceVO vsVO);
	
	public boolean deleteVisit(Integer visitId);
	
	public VisitServiceVO joinMembers(Integer visitId, String deleteCustIds, String addCustIds);
	
	public List<VisitServiceVO> findVisitDetails(Integer visitId);
	
	public long countVisitDetails(Integer visitId);
	
	public long retriveOpenVisitCount(VisitServiceVO vsVO);
}
