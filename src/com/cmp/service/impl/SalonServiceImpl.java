package com.cmp.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.CustomerDAO;
import com.cmp.dao.SalonDAO;
import com.cmp.dao.StatusDAO;
import com.cmp.dao.UserDAO;
import com.cmp.model.Customer;
import com.cmp.model.SalonDetail;
import com.cmp.model.SalonInfo;
import com.cmp.model.SalonSetting;
import com.cmp.model.Status;
import com.cmp.security.SecurityUtil;
import com.cmp.service.RoleService;
import com.cmp.service.SalonService;
import com.cmp.service.vo.SalonServiceVO;

@Service("salonService")
@Transactional
public class SalonServiceImpl implements SalonService {

private static Log log = LogFactory.getLog(SalonServiceImpl.class);
	
	@Autowired
	private SalonDAO salonDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private CustomerDAO customerDAO;
	
	@Autowired
	private StatusDAO statusDAO;
	
	private SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");
	
	@Override
	public SalonServiceVO findSalon(SalonServiceVO ssVO) {
		SalonServiceVO reVO = new SalonServiceVO();
		List<SalonServiceVO> reList = null;
		List<SalonInfo> viList = null;
		try {
			viList = salonDAO.findSalon(ssVO);
			reList = transModel2VOList(viList);
			
			reVO.setSalonList(reList);
			
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error(e.toString(), e);
			}
			e.printStackTrace();
		}
		
		return reVO;
	}
	
	private List<SalonServiceVO> transModel2VOList(List<SalonInfo> modelList) {
		List<SalonServiceVO> reList = new LinkedList<SalonServiceVO>();
		try {
			for (SalonInfo vi : modelList) {
				reList.add(transModel2VO(vi));
				
				Collections.sort(reList, new Comparator<SalonServiceVO>() {
					@Override
					public int compare(SalonServiceVO o1, SalonServiceVO o2) {
						return (o1.getMemberCount() > o2.getMemberCount()) ? -1 : (o1.getMemberCount() == o2.getMemberCount()) ? 0 : 1;
					}
				 });
			}
			
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error(e.toString(), e);
			}
			e.printStackTrace();
		}
		
		return reList;
	}
	
	private SalonServiceVO transModel2VO(SalonInfo salonInfo) {
		SalonServiceVO salonServiceVO = null;
		try {
			salonServiceVO = new SalonServiceVO();
			
			BeanUtils.copyProperties(salonInfo, salonServiceVO, new String[] {
					"joinBeginTime", "joinEndTime"
			});
			
			salonServiceVO.setBeginDate(
					salonInfo.getJoinBeginTime() != null ? DATE_FORMAT.format(salonInfo.getJoinBeginTime()) : "");
			salonServiceVO.setEndDate(
					salonInfo.getJoinEndTime() != null ? DATE_FORMAT.format(salonInfo.getJoinEndTime()) : "");
			salonServiceVO.setStatus(salonInfo.getStatus().getId());
			
			/*
			 * 判斷當下使用者是否有權限Modify此團: (符合以下其中一種即有權限)
			 * (1)Role: SU / MA / ASST
			 * (2)自己開的團
			 */
			String role = SecurityUtil.getSecurityUser().getUser().getRole().getName();
			String account = SecurityUtil.getSecurityUser().getUser().getAccount();
			if (StringUtils.equals(role, RoleService.ROLE_NAME_SU)
					|| StringUtils.equals(role, RoleService.ROLE_NAME_ASST)
					|| StringUtils.equals(role, RoleService.ROLE_NAME_MA)
					|| StringUtils.equals(account, salonInfo.getCreateBy())) {
				
				salonServiceVO.setCanModify(true);
			}
			
			salonServiceVO.setMemberCount(salonDAO.countSalonDetail(salonInfo.getSalonId()));
			
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error(e.toString(), e);
			}
			e.printStackTrace();
		}
		
		return salonServiceVO;
	}

	@Override
	public SalonServiceVO getSalonById(Integer salonId) {
		SalonServiceVO reVO = null;
		SalonInfo salonInfo = null;
		try {
			salonInfo = salonDAO.findSalonBySalonId(salonId);
			reVO = transModel2VO(salonInfo);
			
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error(e.toString(), e);
			}
			e.printStackTrace();
		}
		
		return reVO;
	}

	@Override
	public boolean saveSalon(SalonServiceVO ssVO) {
		SalonInfo salonInfo = null;
		SalonSetting salonSetting = null;
		try {
			if (ssVO.getSalonId() != null) {
				salonInfo = salonDAO.findSalonBySalonId(ssVO.getSalonId());
				
				if (salonInfo == null) {
					throw new Exception("[ERROR]查無 SalonInfo 資料 >> id:"+ssVO.getSalonId());
				}
				salonSetting = salonInfo.getSalonSetting();
				
			} else {
				salonInfo = new SalonInfo();
				salonInfo.setCreateBy(SecurityUtil.getSecurityUser().getUser().getAccount());
				salonInfo.setCreateTime(new Timestamp((new java.util.Date()).getTime()));
				
				salonSetting = new SalonSetting();
				salonSetting.setId(UUID.randomUUID().toString().replace("-", ""));
				salonSetting.setOrderNum(0);
				salonSetting.setCreateBy(SecurityUtil.getSecurityUser().getUser().getAccount());
				salonSetting.setCreateTime(new Timestamp((new java.util.Date()).getTime()));
				salonSetting.setUpdateBy(SecurityUtil.getSecurityUser().getUser().getAccount());
				salonSetting.setUpdateTime(new Timestamp((new java.util.Date()).getTime()));
			}
			
			BeanUtils.copyProperties(ssVO, salonInfo, new String[] {
					"joinBeginTime", "joinEndTime", "createBy"
			});
			
			salonInfo.setJoinBeginTime(new Timestamp(DATE_TIME_FORMAT.parse(ssVO.getBeginDate().concat(" 00:00:00")).getTime()));
			salonInfo.setJoinEndTime(new Timestamp(DATE_TIME_FORMAT.parse(ssVO.getEndDate().concat(" 23:59:59")).getTime()));
			
			Status status = new Status();
			status.setId(ssVO.getStatus());
			salonInfo.setStatus(status);
			
			salonInfo.setUpdateBy(SecurityUtil.getSecurityUser().getUser().getAccount());
			salonInfo.setUpdateTime(new Timestamp((new java.util.Date()).getTime()));
			
			salonDAO.saveSalon(salonInfo, salonSetting);
			
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error(e.toString(), e);
			}
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean deleteSalon(Integer salonId) {
		SalonInfo salonInfo = null;
		try {
			salonInfo = salonDAO.findSalonBySalonId(salonId);
			
			if (salonInfo == null) {
				throw new Exception("[ERROR]查無 SalonInfo 資料 >> id:"+salonId);
			}
			
			salonInfo.setDeleteFlag("Y");
			salonInfo.setUpdateBy(SecurityUtil.getSecurityUser().getUser().getAccount());
			salonInfo.setUpdateTime(new Timestamp((new java.util.Date()).getTime()));
			
			salonDAO.saveSalon(salonInfo, salonInfo.getSalonSetting());
			
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error(e.toString(), e);
			}
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}

	@Override
	public SalonServiceVO joinMembers(Integer salonId, String deleteCustIds, String addCustIds) {
		SalonServiceVO reVO = new SalonServiceVO();
		List<SalonDetail> sdList = null;
		
		List<String> tmpCustList = null;
		List<Integer> deleteCustList = new ArrayList<Integer>();
		try {
			//刪除
			if (StringUtils.isNotBlank(deleteCustIds)) {
				tmpCustList = Arrays.asList(deleteCustIds.split(","));
				for (String delId : tmpCustList) {
					deleteCustList.add(Integer.parseInt(delId));
				}
				List<SalonDetail> deleteModels = salonDAO.findSalonDetailByCustIds(salonId, deleteCustList);
				
				if (deleteModels != null && !deleteModels.isEmpty()) {
					salonDAO.deleteSalonDetail(deleteModels);
				}
			}
			
			//新增
			if (StringUtils.isNotBlank(addCustIds)) {
				tmpCustList = Arrays.asList(addCustIds.split(","));
				SalonDetail sd = null;
				sdList = new ArrayList<SalonDetail>();
				for (String custId : tmpCustList) {
					sd = new SalonDetail();
					sd.setId(UUID.randomUUID().toString().replace("-", ""));
					sd.setSalonInfo(salonDAO.findSalonBySalonId(salonId));
					sd.setCust(customerDAO.findCustById(Integer.parseInt(custId)));
					sd.setCreateBy(SecurityUtil.getSecurityUser().getUser().getAccount());
					sd.setCreateTime(new Timestamp((new java.util.Date()).getTime()));
					sd.setUpdateBy(SecurityUtil.getSecurityUser().getUser().getAccount());
					sd.setUpdateTime(new Timestamp((new java.util.Date()).getTime()));
					sdList.add(sd);
				}
				
				salonDAO.addSalonDetail(sdList);
			}
			
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error(e.toString(), e);
			}
			e.printStackTrace();
		}
		
		return reVO;
	}

	@Override
	public List<SalonServiceVO> findSalonDetails(Integer salonId) {
		List<SalonServiceVO> reList = new ArrayList<SalonServiceVO>();
		List<SalonDetail> modelList = null;
		try {
			String userId = SecurityUtil.getSecurityUser().getUser().getId();
			String role = SecurityUtil.getSecurityUser().getUser().getRole().getName();
			
			if (role.equals(RoleService.ROLE_NAME_SU) || role.equals(RoleService.ROLE_NAME_ASST)) {
				//SU & ASST(助理) 能看所有入團客戶清單
				userId = "";
			}
			modelList = salonDAO.findSalonDetailBySalonIdAndUserId(salonId, userId);
			
			SalonServiceVO ssVO = null;
			for (SalonDetail sd : modelList) {
				ssVO = new SalonServiceVO();
				ssVO.setUserName(userDAO.findUserById(sd.getCust().getUser().getId()).getName());
				ssVO.setCustName(sd.getCust().getName());
				ssVO.setCustId(sd.getCust().getId());
				ssVO.setStatusId(sd.getCust().getStatus().getId());
				ssVO.setSort(sd.getCust().getStatus().getSort());
				ssVO.setRemark(StringUtils.isNotBlank(sd.getRemark()) ? sd.getRemark() : "");
				reList.add(ssVO);
			}
			
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error(e.toString(), e);
			}
			e.printStackTrace();
		}
		
		return reList;
	}

	@Override
	public long countSalonDetails(Integer salonId) {
		long count = 0;
		try {
			String userId = SecurityUtil.getSecurityUser().getUser().getId();
			count = salonDAO.countSalonDetailBySalonIdAndUserId(salonId, userId);
			
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error(e.toString(), e);
			}
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public boolean saveSalonDetail(SalonServiceVO ssVO) {
		SalonDetail salonDetail;
		Customer customer;
		Integer salonId;
		try {
			salonId = ssVO.getSalonId();
			
			int idx = 0;
			for (Integer custId : ssVO.getCustIdArray()) {
				salonDetail = salonDAO.findSalonDetailByCustIds(salonId, Arrays.asList(new Integer[] {custId})).get(0);
				
				if (salonDetail != null) {
					salonDetail.setRemark(
							ssVO.getRemarkArray().length == 0 ? null : ssVO.getRemarkArray()[idx]);
					salonDetail.setUpdateBy(SecurityUtil.getSecurityUser().getUser().getAccount());
					salonDetail.setUpdateTime(new Timestamp((new java.util.Date()).getTime()));
					
					salonDAO.updateSalonDetail(salonDetail);
					
					customer = customerDAO.findCustById(custId);
					
					if (customer != null) {
						customer.setStatus(statusDAO.findStatus("CUST", ssVO.getStatusSortArray()[idx]));
						customer.setUpdateTime(new Timestamp(System.currentTimeMillis()));
						customer.setUpdateBy(SecurityUtil.getSecurityUser().getUser().getAccount());
						customerDAO.saveCust(customer);
					}
				}
				
				idx += 1;
			}
			
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error(e.toString(), e);
			}
			e.printStackTrace();
			
			return false;
		}
		return true;
	}

}
