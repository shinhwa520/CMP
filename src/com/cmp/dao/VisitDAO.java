package com.cmp.dao;

import java.util.List;

import com.cmp.model.VisitDetail;
import com.cmp.model.VisitInfo;
import com.cmp.model.VisitSetting;
import com.cmp.service.vo.VisitServiceVO;

public interface VisitDAO {

	public VisitInfo findVisitByVisitId(Integer visitId);
	
	public List<VisitInfo> findVisit(VisitServiceVO visitServiceVO);

	public void saveVisit(VisitInfo visitInfo, VisitSetting visitSetting);
	
	public Integer deleteVisit(VisitServiceVO visitServiceVO);
	
	public long countVisitDetail(Integer visitId);
	
	public long checkCustHasJoinTheVisitOrNot(Integer visitId, Integer custId);
	
	public List<VisitDetail> findVisitDetailByVistiIdAndUserId(Integer visitId, String userId);
	
	public long countVisitDetailByVistiIdAndUserId(Integer visitId, String userId);
	
	public Integer deleteVisitDetail(List<VisitDetail> deleteModelList);
	
	public boolean addVisitDetail(List<VisitDetail> addModelList);
	
	public List<VisitDetail> findVisitDetailByCustIds(Integer visitId, List<Integer> custIdList);
}
