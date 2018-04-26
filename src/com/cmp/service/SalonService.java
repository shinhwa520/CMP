package com.cmp.service;

import java.util.List;

import com.cmp.service.vo.SalonServiceVO;

public interface SalonService {

	public SalonServiceVO findSalon(SalonServiceVO ssVO);
	
	public SalonServiceVO getSalonById(Integer salonId);

	public boolean saveSalon(SalonServiceVO ssVO);
	
	public boolean saveSalonDetail(SalonServiceVO ssVO);
	
	public boolean deleteSalon(Integer salonId);
	
	public SalonServiceVO joinMembers(Integer salonId, String deleteCustIds, String addCustIds);
	
	public List<SalonServiceVO> findSalonDetails(Integer salonId);
	
	public long countSalonDetails(Integer salonId);
}
