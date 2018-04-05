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
import com.cmp.dao.UserDAO;
import com.cmp.dao.VisitDAO;
import com.cmp.model.Status;
import com.cmp.model.User;
import com.cmp.model.VisitDetail;
import com.cmp.model.VisitInfo;
import com.cmp.model.VisitSetting;
import com.cmp.security.SecurityUtil;
import com.cmp.service.RoleService;
import com.cmp.service.StatusService;
import com.cmp.service.VisitService;
import com.cmp.service.vo.VisitServiceVO;

@Service("visitService")
@Transactional
public class VisitServiceImpl implements VisitService {
	private static Log log = LogFactory.getLog(VisitServiceImpl.class);
	
	@Autowired
	private VisitDAO visitDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private CustomerDAO customerDAO;
	
	private SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");
	
	@Override
	public VisitServiceVO getVisitById(Integer visitId) {
		VisitServiceVO reVO = null;
		VisitInfo visitInfo = null;
		try {
			visitInfo = visitDAO.findVisitByVisitId(visitId);
			reVO = transModel2VO(visitInfo);
			
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error(e.toString(), e);
			}
			e.printStackTrace();
		}
		
		return reVO;
	}
	
	/**
	 * 查找當前使用者一直到最上層等於主渠道時，過程中的所有渠道商Account
	 * @param accountList
	 * @param userAccount
	 * @return
	 */
	private List<String> findAllUpperUserUntilMA(List<String> accountList, String userAccount) {
		User user = userDAO.findUserByAccount(userAccount).get(0);
		if (user != null && !user.getRole().getName().equals(RoleService.ROLE_NAME_MA)) {
			User upperUser = userDAO.findUserById(user.getChannel().getId());
			
			String upperUserAccount = "";
			if (upperUser != null) {
				upperUserAccount = upperUser.getAccount();
				accountList.add(upperUserAccount);
				
				return findAllUpperUserUntilMA(accountList, upperUserAccount);
				
			} else {
				return accountList;
			}
			
		} else {
			return accountList;
		}
	}
	
	@Override
	public VisitServiceVO findVisit(VisitServiceVO vsVO) {
		VisitServiceVO reVO = new VisitServiceVO();
		List<VisitServiceVO> reList = null;
		List<VisitInfo> viList = null;
		try {
			String role = SecurityUtil.getSecurityUser().getUser().getRole().getName();
			String account = SecurityUtil.getSecurityUser().getUser().getAccount();
			List<String> accountList = null;
			
			/*
			 * 確認查看權限:
			 * (1)Role: SU / MA / ASST => 可看所有的團
			 * (2)一級渠道(含)以下 => 只能看歸屬的主渠道開的團
			 */
			if (StringUtils.equals(role, RoleService.ROLE_NAME_SU)
					|| StringUtils.equals(role, RoleService.ROLE_NAME_ASST)
					|| StringUtils.equals(role, RoleService.ROLE_NAME_MA)) {
				
				vsVO.setCanSeeAll(true);
				
			} else {
				accountList = new ArrayList<String>();
				accountList.add(account);
				accountList = findAllUpperUserUntilMA(accountList, account);
				vsVO.setUserAccounts(accountList);
			}
			
			viList = visitDAO.findVisit(vsVO);
			reList = transModel2VOList(viList);
			
			/*
			 * 判斷當下使用者是否有權限開團: (符合以下其中一種即有權限)
			 * (1)Role: SU / MA / ASST
			 * (2)一級渠道商 (上層為 adminp 或 adminj)
			 */
			boolean canAdd = false;
			if (StringUtils.equals(role, RoleService.ROLE_NAME_SU)
					|| StringUtils.equals(role, RoleService.ROLE_NAME_ASST)
					|| StringUtils.equals(role, RoleService.ROLE_NAME_MA)) {
				
				canAdd = true;
				
			} else {
				User user = userDAO.findUserByAccount(account).get(0);
				if (user != null && user.getChannel() != null) {
					User upperUser = userDAO.findUserById(user.getChannel().getId());
					
					if (StringUtils.equals(upperUser.getRole().getName(), RoleService.ROLE_NAME_MA)) {
						
						canAdd = true;
					}
				}
			}
			
			reVO.setVisitList(reList);
			reVO.setCanAdd(canAdd);
			
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error(e.toString(), e);
			}
			e.printStackTrace();
		}
		
		return reVO;
	}
	
	private List<VisitServiceVO> transModel2VOList(List<VisitInfo> modelList) {
		List<VisitServiceVO> reList = new LinkedList<VisitServiceVO>();
		try {
			for (VisitInfo vi : modelList) {
				reList.add(transModel2VO(vi));
				
				Collections.sort(reList, new Comparator<VisitServiceVO>() {
					@Override
					public int compare(VisitServiceVO o1, VisitServiceVO o2) {
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
	
	private VisitServiceVO transModel2VO(VisitInfo visitInfo) {
		VisitServiceVO visitServiceVO = null;
		try {
			visitServiceVO = new VisitServiceVO();
			
			BeanUtils.copyProperties(visitInfo, visitServiceVO, new String[] {
					"joinBeginTime", "joinEndTime", "departureDate", "departureTime", 
					"arrivalDate", "arrivalTime", "returnDate", "returnFlightTime"
			});
			
			visitServiceVO.setBeginDate(
					visitInfo.getJoinBeginTime() != null ? DATE_FORMAT.format(visitInfo.getJoinBeginTime()) : "");
			visitServiceVO.setEndDate(
					visitInfo.getJoinEndTime() != null ? DATE_FORMAT.format(visitInfo.getJoinEndTime()) : "");
			visitServiceVO.setDepartureDate(
					visitInfo.getDepartureDate() != null ? DATE_FORMAT.format(visitInfo.getDepartureDate()) : "");
			visitServiceVO.setDepartureTime(
					visitInfo.getDepartureTime() != null ? TIME_FORMAT.format(visitInfo.getDepartureTime()) : "");
			visitServiceVO.setArrivalDate(
					visitInfo.getArrivalDate() != null ? DATE_FORMAT.format(visitInfo.getArrivalDate()) : "");
			visitServiceVO.setArrivalTime(
					visitInfo.getArrivalTime() != null ? TIME_FORMAT.format(visitInfo.getArrivalTime()) : "");
			visitServiceVO.setReturnDate(
					visitInfo.getReturnDate() != null ? DATE_FORMAT.format(visitInfo.getReturnDate()) : "");
			visitServiceVO.setReturnTime(
					visitInfo.getReturnFlightTime() != null ? TIME_FORMAT.format(visitInfo.getReturnFlightTime()) : "");
			visitServiceVO.setStatus(visitInfo.getStatus().getId());
			
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
					|| StringUtils.equals(account, visitInfo.getCreateBy())) {
				
				visitServiceVO.setCanModify(true);
			}
			
			visitServiceVO.setMemberCount(visitDAO.countVisitDetail(visitInfo.getVisitId()));
			
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error(e.toString(), e);
			}
			e.printStackTrace();
		}
		
		return visitServiceVO;
	}

	@Override
	public boolean saveVisit(VisitServiceVO vsVO) {
		VisitInfo visitInfo = null;
		VisitSetting visitSetting = null;
		try {
			if (vsVO.getVisitId() != null) {
				visitInfo = visitDAO.findVisitByVisitId(vsVO.getVisitId());
				
				if (visitInfo == null) {
					throw new Exception("[ERROR]查無 VisitInfo 資料 >> id:"+vsVO.getVisitId());
				}
				visitSetting = visitInfo.getVisitSetting();
				
			} else {
				visitInfo = new VisitInfo();
				visitInfo.setCreateBy(SecurityUtil.getSecurityUser().getUser().getAccount());
				visitInfo.setCreateTime(new Timestamp((new java.util.Date()).getTime()));
				
				visitSetting = new VisitSetting();
				visitSetting.setId(UUID.randomUUID().toString().replace("-", ""));
				visitSetting.setOrderNum(0);
				visitSetting.setCreateBy(SecurityUtil.getSecurityUser().getUser().getAccount());
				visitSetting.setCreateTime(new Timestamp((new java.util.Date()).getTime()));
				visitSetting.setUpdateBy(SecurityUtil.getSecurityUser().getUser().getAccount());
				visitSetting.setUpdateTime(new Timestamp((new java.util.Date()).getTime()));
			}
			
			BeanUtils.copyProperties(vsVO, visitInfo, new String[] {
					"joinBeginTime", "joinEndTime", "departureDate", "departureTime", 
					"arrivalDate", "arrivalTime", "returnDate", "returnFlightTime", "createBy"
			});
			
			visitInfo.setJoinBeginTime(new Timestamp(DATE_TIME_FORMAT.parse(vsVO.getBeginDate().concat(" 00:00:00")).getTime()));
			visitInfo.setJoinEndTime(new Timestamp(DATE_TIME_FORMAT.parse(vsVO.getEndDate().concat(" 23:59:59")).getTime()));
			visitInfo.setDepartureDate(
					StringUtils.isNotBlank(vsVO.getDepartureDate()) ? new java.sql.Date(DATE_FORMAT.parse(vsVO.getDepartureDate()).getTime()) : null);
			visitInfo.setDepartureTime(
					StringUtils.isNotBlank(vsVO.getDepartureTime()) ? new java.sql.Time(TIME_FORMAT.parse(vsVO.getDepartureTime()).getTime()) : null);
			visitInfo.setArrivalDate(
					StringUtils.isNotBlank(vsVO.getArrivalDate()) ? new java.sql.Date(DATE_FORMAT.parse(vsVO.getArrivalDate()).getTime()) : null);
			visitInfo.setArrivalTime(
					StringUtils.isNotBlank(vsVO.getArrivalTime()) ? new java.sql.Time(TIME_FORMAT.parse(vsVO.getArrivalTime()).getTime()) : null);
			visitInfo.setReturnDate(
					StringUtils.isNotBlank(vsVO.getReturnDate()) ? new java.sql.Date(DATE_FORMAT.parse(vsVO.getReturnDate()).getTime()) : null);
			visitInfo.setReturnFlightTime(
					StringUtils.isNotBlank(vsVO.getReturnTime()) ? new java.sql.Time(TIME_FORMAT.parse(vsVO.getReturnTime()).getTime()) : null);
			
			Status status = new Status();
			status.setId(vsVO.getStatus());
			visitInfo.setStatus(status);
			
			visitInfo.setUpdateBy(SecurityUtil.getSecurityUser().getUser().getAccount());
			visitInfo.setUpdateTime(new Timestamp((new java.util.Date()).getTime()));
			
			visitDAO.saveVisit(visitInfo, visitSetting);
			
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error(e.toString(), e);
			}
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean deleteVisit(Integer visitId) {
		VisitInfo visitInfo = null;
		try {
			visitInfo = visitDAO.findVisitByVisitId(visitId);
			
			if (visitInfo == null) {
				throw new Exception("[ERROR]查無 VisitInfo 資料 >> id:"+visitId);
			}
			
			visitInfo.setDeleteFlag("Y");
			visitInfo.setUpdateBy(SecurityUtil.getSecurityUser().getUser().getAccount());
			visitInfo.setUpdateTime(new Timestamp((new java.util.Date()).getTime()));
			
			visitDAO.saveVisit(visitInfo, visitInfo.getVisitSetting());
			
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
	public VisitServiceVO joinMembers(Integer visitId, String deleteCustIds, String addCustIds) {
		VisitServiceVO reVO = new VisitServiceVO();
		List<VisitDetail> vdList = null;
		
		List<String> tmpCustList = null;
		List<Integer> deleteCustList = new ArrayList<Integer>();
		try {
			//刪除
			if (StringUtils.isNotBlank(deleteCustIds)) {
				tmpCustList = Arrays.asList(deleteCustIds.split(","));
				for (String delId : tmpCustList) {
					deleteCustList.add(Integer.parseInt(delId));
				}
				List<VisitDetail> deleteModels = visitDAO.findVisitDetailByCustIds(visitId, deleteCustList);
				
				if (deleteModels != null && !deleteModels.isEmpty()) {
					visitDAO.deleteVisitDetail(deleteModels);
				}
			}
			
			//新增
			if (StringUtils.isNotBlank(addCustIds)) {
				tmpCustList = Arrays.asList(addCustIds.split(","));
				VisitDetail vd = null;
				vdList = new ArrayList<VisitDetail>();
				for (String custId : tmpCustList) {
					vd = new VisitDetail();
					vd.setId(UUID.randomUUID().toString().replace("-", ""));
					vd.setVisitInfo(visitDAO.findVisitByVisitId(visitId));
					vd.setCust(customerDAO.findCustById(Integer.parseInt(custId)));
					vd.setCreateBy(SecurityUtil.getSecurityUser().getUser().getAccount());
					vd.setCreateTime(new Timestamp((new java.util.Date()).getTime()));
					vd.setUpdateBy(SecurityUtil.getSecurityUser().getUser().getAccount());
					vd.setUpdateTime(new Timestamp((new java.util.Date()).getTime()));
					vdList.add(vd);
				}
				
				visitDAO.addVisitDetail(vdList);
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
	public List<VisitServiceVO> findVisitDetails(Integer visitId) {
		List<VisitServiceVO> reList = new ArrayList<VisitServiceVO>();
		List<VisitDetail> modelList = null;
		try {
			String userId = SecurityUtil.getSecurityUser().getUser().getId();
			String role = SecurityUtil.getSecurityUser().getUser().getRole().getName();
			
			if (role.equals(RoleService.ROLE_NAME_SU) || role.equals(RoleService.ROLE_NAME_ASST)) {
				//SU & ASST(助理) 能看所有入團客戶清單
				userId = "";
			}
			modelList = visitDAO.findVisitDetailByVistiIdAndUserId(visitId, userId);
			
			VisitServiceVO vsVO = null;
			for (VisitDetail vd : modelList) {
				vsVO = new VisitServiceVO();
				vsVO.setUserName(userDAO.findUserById(vd.getCust().getUser().getId()).getName());
				vsVO.setCustName(vd.getCust().getName());
				vsVO.setCustId(vd.getCust().getId());
				
				/*
				 * 判斷客戶狀態(!=已收團費) or 客戶的基本必填欄位資料不齊全
				 * => custNotReady 設定為 true。用以頁面判斷醒目顯示用
				 */
				if ((vd.getCust().getStatus().getId() < StatusService.CUST_2_RECEIVED_TOUR_FEE)
						|| StringUtils.isBlank(vd.getCust().getName())
						|| StringUtils.isBlank(vd.getCust().getEmail())
						|| (vd.getCust().getIdentity1_id() == 0)
						|| StringUtils.isBlank(vd.getCust().getIdentity1_code())
						|| StringUtils.isBlank(vd.getCust().getIdentity1_name())
						|| StringUtils.isBlank(vd.getCust().getCensus())) {
					vsVO.setCustNotReady(true);
				}
				
				vsVO.setVisaStatus(StringUtils.isNotBlank(vd.getVisaStatus()) ? vd.getVisaStatus() : "");
				vsVO.setAccommodationSituation(StringUtils.isNotBlank(vd.getAccommodationStatus()) ? vd.getAccommodationStatus() : "");
				vsVO.setAmountReceived(Integer.toString(vd.getAmountReceived()));
				vsVO.setRemark(StringUtils.isNotBlank(vd.getRemark()) ? vd.getRemark() : "");
				reList.add(vsVO);
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
	public long countVisitDetails(Integer visitId) {
		long count = 0;
		try {
			String userId = SecurityUtil.getSecurityUser().getUser().getId();
			count = visitDAO.countVisitDetailByVistiIdAndUserId(visitId, userId);
			
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error(e.toString(), e);
			}
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public boolean saveVisitDetail(VisitServiceVO vsVO) {
		VisitDetail visitDetail;
		Integer visitId;
		try {
			visitId = vsVO.getVisitId();
			
			int idx = 0;
			for (Integer custId : vsVO.getCustIdArray()) {
				visitDetail = visitDAO.findVisitDetailByCustIds(visitId, Arrays.asList(new Integer[] {custId})).get(0);
				
				if (visitDetail != null) {
					visitDetail.setAccommodationStatus(
							vsVO.getAccommodationSituationArray().length == 0 ? null : vsVO.getAccommodationSituationArray()[idx]);
					visitDetail.setAmountReceived(
							vsVO.getAmountReceivedArray().length == 0 ? 0 : vsVO.getAmountReceivedArray()[idx] == null ? 0 : vsVO.getAmountReceivedArray()[idx]);
					visitDetail.setRemark(
							vsVO.getRemarkArray().length == 0 ? null : vsVO.getRemarkArray()[idx]);
					visitDetail.setVisaStatus(
							vsVO.getVisaStatusArray().length == 0 ? null : vsVO.getVisaStatusArray()[idx]);
					visitDetail.setUpdateBy(SecurityUtil.getSecurityUser().getUser().getAccount());
					visitDetail.setUpdateTime(new Timestamp((new java.util.Date()).getTime()));
					
					visitDAO.updateVisitDetail(visitDetail);
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
