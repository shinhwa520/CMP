package com.cmp.dao;

import java.util.List;

import com.cmp.model.SalonDetail;
import com.cmp.model.SalonInfo;
import com.cmp.model.SalonSetting;
import com.cmp.service.vo.SalonServiceVO;

public interface SalonDAO {

	public SalonInfo findSalonBySalonId(Integer salonId);
	
	public List<SalonInfo> findSalon(SalonServiceVO salonServiceVO);

	public void saveSalon(SalonInfo salonInfo, SalonSetting salonSetting);
	
	public Integer deleteSalon(SalonServiceVO salonServiceVO);
	
	public long countSalonDetail(Integer salonId);
	
	public long checkCustHasJoinTheSalonOrNot(Integer salonId, Integer custId);
	
	public List<SalonDetail> findSalonDetailBySalonIdAndUserId(Integer salonId, String userId);
	
	public long countSalonDetailBySalonIdAndUserId(Integer salonId, String userId);
	
	public Integer deleteSalonDetail(List<SalonDetail> deleteModelList);
	
	public boolean addSalonDetail(List<SalonDetail> addModelList);
	
	public List<SalonDetail> findSalonDetailByCustIds(Integer salonId, List<Integer> custIdList);
	
	public void updateSalonDetail(SalonDetail salonDetail);
	
}
