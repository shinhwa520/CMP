package com.cmp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.StatusDAO;
import com.cmp.dao.UserDAO;
import com.cmp.dao.UserKpiDAO;
import com.cmp.model.User;
import com.cmp.model.UserKpi;
import com.cmp.security.SecurityUtil;
import com.cmp.service.UserService;
import com.cmp.service.vo.UserServiceVO;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private UserKpiDAO userKpiDAO;
	@Autowired
	private StatusDAO statusDAO;	
	
	private String[] monthWord = new String[] {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	
	@Override
	public User findUserAndKpiById(String id, String yearMonth){
//		SecurityUser securityUser = SecurityUtil.getSecurityUser();
		Object[] objArray = userDAO.findUserAndKpiById(id, yearMonth);
		User user = (User) objArray[0];
		user.set_agent_user(((Long) objArray[1]).intValue());
		user.set_agent_cust(((Long) objArray[2]).intValue());
		user.set_volume(((Long) objArray[3]).intValue());
		UserKpi kpi = userKpiDAO.findKpiByUserAndYearMonth(id, yearMonth);//查詢KPI設定
		if( null!=kpi){
			user.setAgent_user(kpi.getAgent_user());
			user.setAgent_cust(kpi.getAgent_cust());
			user.setVolume(kpi.getVolume());
		}
		
		return user;
	}
	
	@Override
	public UserServiceVO findUserRecentlySixMonthKpiById(String id, String currentYearMonth) {
		UserServiceVO retVO = new UserServiceVO();
		String editorId = SecurityUtil.getSecurityUser().getUser().getId();
		User user = findUserById(id);
		/*
		 * Step 1. 先檢核使用者當月KPI目標資料是否已寫入
		 */
		UserKpi kpi = userKpiDAO.findKpiByUserAndYearMonth(id, currentYearMonth);//查詢KPI設定
		if( null == kpi) {
			/*
			 * Step 2. 若當月資料尚未寫入，複製前一月資料寫入；若仍查無資料則新增一筆目標全為0的資料
			 */
			kpi = userKpiDAO.findTheMostRecentlyKpiByUser(id);
			
			if ( null != kpi ) {
				kpi.setId(String.valueOf(new Date().getTime()));
				kpi.setYearMonth(currentYearMonth);
				kpi.setCreateBy(editorId);
				kpi.setCreateDateTime(new Date());
				kpi.setUpdateBy(editorId);
				kpi.setUpdateDateTime(new Date());
				
			} else {
				kpi = new UserKpi(
					String.valueOf(new Date().getTime())
					,user
					,currentYearMonth
					,0
					,0
					,0
					,editorId
					,new Date()
					,editorId
					,new Date()
				);
			}
			
			userKpiDAO.saveUserKpi(kpi);
		}
		
		/*
		 * Step 3. 查詢使用者近六個月的KPI紀錄
		 */
		String monthDesc = "";
		String kpiUserTarget = "";
		String kpiUserActual = "";
		String kpiVisitTarget = "";
		String kpiVisitActual = "";
		String kpiVolumeTarget = "";
		String kpiVolumeActual = "";
		Integer tmpKpiUser = 0;
		Integer tmpVisit = 0;
		Integer tmpVolume = 0;
		Integer maxKpiUser = 0;
		Integer maxVisit = 0;
		Integer maxVolume = 0;
		
		List<User> kpiList = new ArrayList<User>();
		for (int i=5; i>=0; i--) {
			int year = Integer.parseInt(currentYearMonth.substring(0, 4));
			int month = Integer.parseInt(currentYearMonth.substring(4, currentYearMonth.length())) - i;
			
			if (month <= 0) {
				year = year - 1;
				month = 12 + month;
			}
			
			monthDesc = monthDesc.concat("'").concat(monthWord[month-1]).concat("'");
			String yearMonth = String.valueOf(year).concat(StringUtils.leftPad(String.valueOf(month), 2, "0"));
			
			User userKpi = findUserAndKpiById(editorId, yearMonth);
			kpiList.add(userKpi);
			
			kpiUserTarget = kpiUserTarget.concat(String.valueOf(userKpi.getAgent_user()));
			kpiUserActual = kpiUserActual.concat(String.valueOf(userKpi.get_agent_user()));
			kpiVisitTarget = kpiVisitTarget.concat(String.valueOf(userKpi.getAgent_cust()));
			kpiVisitActual = kpiVisitActual.concat(String.valueOf(userKpi.get_agent_cust()));
			kpiVolumeTarget = kpiVolumeTarget.concat(String.valueOf(userKpi.getVolume()));
			kpiVolumeActual = kpiVolumeActual.concat(String.valueOf(userKpi.get_volume()));
			
			tmpKpiUser = userKpi.getAgent_user() > userKpi.get_agent_user() ? userKpi.getAgent_user() : userKpi.get_agent_user();
			tmpVisit = userKpi.getAgent_cust() > userKpi.get_agent_cust() ? userKpi.getAgent_cust() : userKpi.get_agent_cust();
			tmpVolume = userKpi.getVolume() > userKpi.get_volume() ? userKpi.getVolume() : userKpi.get_volume();
			
			maxKpiUser = tmpKpiUser > maxKpiUser ? tmpKpiUser : maxKpiUser;
			maxVisit = tmpVisit > maxVisit ? tmpVisit : maxVisit;
			maxVolume = tmpVolume > maxVolume ? tmpVolume : maxVolume;
			
			if (i != 0) {
				monthDesc = monthDesc.concat(",");
				kpiUserTarget= kpiUserTarget.concat(",");
				kpiUserActual = kpiUserActual.concat(",");
				kpiVisitTarget = kpiVisitTarget.concat(",");
				kpiVisitActual = kpiVisitActual.concat(",");
				kpiVolumeTarget = kpiVolumeTarget.concat(",");
				kpiVolumeActual = kpiVolumeActual.concat(",");
			}
		}
		
		retVO.setMonthDesc(monthDesc);
		retVO.setKpiUserTarget(kpiUserTarget);
		retVO.setKpiUserActual(kpiUserActual);
		retVO.setKpiVisitTarget(kpiVisitTarget);
		retVO.setKpiVisitActual(kpiVisitActual);
		retVO.setKpiVolumeTarget(kpiVolumeTarget);
		retVO.setKpiVolumeActual(kpiVolumeActual);
		
		retVO.setMaxKpiUser(String.valueOf(maxKpiUser+5));
		retVO.setMaxVisit(String.valueOf(maxVisit+5));
		retVO.setMaxVolume(String.valueOf(maxVolume+5));
		
		return retVO;
	}
	
	@Override
	public User findUserById(String id){
//		SecurityUser securityUser = SecurityUtil.getSecurityUser();
		return userDAO.findUserById(id);
	}
	
	@Override
	public User saveUserInfo(User user){
		return userDAO.saveUser(user);
	}
	
	@Override
	public List<User> findUserByChannelId(String channelId, String yearMonth, Integer start, Integer length){
		List<User> resultList = new ArrayList<User>();
		List<Object[]> objArrayList = userDAO.findUserByChannelId(channelId, yearMonth, start, length);
		for(Object[] objArray : objArrayList){
			User user = (User) objArray[0];
			user.set_agent_user(((Long) objArray[1]).intValue());
			user.set_agent_cust(((Long) objArray[2]).intValue());
			user.set_volume(((Long) objArray[3]).intValue());
			UserKpi kpi = userKpiDAO.findKpiByUserAndYearMonth(user.getId(), yearMonth);//查詢KPI設定
			if( null!=kpi){
				user.setAgent_user(kpi.getAgent_user());
				user.setAgent_cust(kpi.getAgent_cust());
				user.setVolume(kpi.getVolume());
			}
			resultList.add(user);
		}

		return resultList;
	}
	
	@Override
	public long countUserByChannelId(String channelId){
		return userDAO.countUserByChannelId(channelId);
	}
	
	@Override
	public List<User> findUser4MA(String roleName, String yearMonth, Integer start, Integer length){
		List<User> resultList = new ArrayList<User>();
		List<Object[]> objArrayList = userDAO.findUser4MA(roleName, yearMonth, start, length);
		for(Object[] objArray : objArrayList){
			User user = (User) objArray[0];
			user.set_agent_user(((Long) objArray[1]).intValue());
			user.set_agent_cust(((Long) objArray[2]).intValue());
			user.set_volume(((Long) objArray[3]).intValue());
			UserKpi kpi = userKpiDAO.findKpiByUserAndYearMonth(user.getId(), yearMonth);//查詢KPI設定
			if( null!=kpi){
				user.setAgent_user(kpi.getAgent_user());
				user.setAgent_cust(kpi.getAgent_cust());
				user.setVolume(kpi.getVolume());
			}
			resultList.add(user);
		}

		return resultList;
	}
	
	@Override
	public long countUser4MA(String roleName){
		return userDAO.countUser4MA(roleName);
	}
	
	@Override
	public void updateKpi(String userId, String yearMonth, int agent_user, int agent_cust, int volume, Date current, String remark){
		String editorId = SecurityUtil.getSecurityUser().getUser().getId();
		User user = findUserById(userId);
		user.setRemark(remark);
		user.setUpdateBy(editorId);
		user.setUpdateDateTime(new Date());
		userDAO.saveUser(user);
		UserKpi userKpi = userKpiDAO.findKpiByUserAndYearMonth(userId, yearMonth);//查詢KPI設定
		if(null!=userKpi){
			userKpi.setAgent_user(agent_user);
			userKpi.setAgent_cust(agent_cust);
			userKpi.setVolume(volume);
			userKpi.setUpdateBy(editorId);
			userKpi.setUpdateDateTime(current);
		}else{
			userKpi= new UserKpi(
				String.valueOf(current.getTime())
				,user
				,yearMonth
				,agent_user
				,agent_cust
				,volume
				,editorId
				,current
				,editorId
				,current
				);
		}
		userKpiDAO.saveUserKpi(userKpi);
	}
	
	@Override
	public void update(String userId, String userName, String password, String phone, String email, String weChat, String status, String remark){
		String editorId = SecurityUtil.getSecurityUser().getUser().getId();
		User user = findUserById(userId);
		user.setName(userName);
		user.setPassword(password);
		user.setPhone(phone);
		user.setEmail(email);
		user.setWeChat(weChat);
		user.setStatus(statusDAO.findStatus("USER", Integer.valueOf(status)));
		user.setRemark(remark);
		user.setUpdateBy(editorId);
		user.setUpdateDateTime(new Date());
		userDAO.saveUser(user);
	}

	@Override
	public User findUserByApiId(String apiId) {
		User user = null;
		try {
			List<User> userList = userDAO.findUserByApiModelId(apiId);
			
			if (userList != null && !userList.isEmpty()) {
				user = userList.get(0);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}

	
	public User closeGuide() {
		User user = SecurityUtil.getSecurityUser().getUser();
		try {
			user.setShowGuide("N");
			user = userDAO.saveUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}
