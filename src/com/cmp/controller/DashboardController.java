package com.cmp.controller;

import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cmp.MenuItem;
import com.cmp.security.SecurityUtil;
import com.cmp.service.CustService;
import com.cmp.service.SalonService;
import com.cmp.service.StatusService;
import com.cmp.service.UserService;
import com.cmp.service.VisitService;
import com.cmp.service.vo.CustServiceVO;
import com.cmp.service.vo.SalonServiceVO;
import com.cmp.service.vo.UserServiceVO;
import com.cmp.service.vo.VisitServiceVO;

@Controller
@RequestMapping(value="/dashboard")
public class DashboardController extends BaseController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private VisitService visitService;
	
	@Autowired
	private SalonService salonService;
	
	@Autowired
	private CustService custService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model, Principal principal, HttpServletRequest request, HttpServletResponse response) {
		try {
			setActiveMenu(model, MenuItem.INDEX);
			
			/*
			 * 查詢KPI資料
			 */
			String userId = SecurityUtil.getSecurityUser().getUser().getId();
			UserServiceVO userServiceVO = userService.findUserRecentlySixMonthKpiById(userId, sdfYearMonth.format(new Date()));
			
			request.setAttribute("monthDesc", userServiceVO.getMonthDesc());
			request.setAttribute("kpiUserTarget", userServiceVO.getKpiUserTarget());
			request.setAttribute("kpiUserActual", userServiceVO.getKpiUserActual());
			request.setAttribute("kpiVisitTarget", userServiceVO.getKpiVisitTarget());
			request.setAttribute("kpiVisitActual", userServiceVO.getKpiVisitActual());
			request.setAttribute("kpiVolumeTarget", userServiceVO.getKpiVolumeTarget());
			request.setAttribute("kpiVolumeActual", userServiceVO.getKpiVolumeActual());
			
			request.setAttribute("maxKpiUser", userServiceVO.getMaxKpiUser());
			request.setAttribute("maxVisit", userServiceVO.getMaxVisit());
			request.setAttribute("maxVolume", userServiceVO.getMaxVolume());
			
			/*
			 * 查詢過去六個月客戶增加數量
			 */
			CustServiceVO custServiceVO = custService.findUserRecentlySixMonthCustCountPerMonth(userId, sdfYearMonth.format(new Date()));
			
			request.setAttribute("custMonthWord", custServiceVO.getCustMonthWord());
			request.setAttribute("custCountPerMonth", custServiceVO.getCustCountPerMonth());
			request.setAttribute("maxCustCount", custServiceVO.getMaxCustCount());
			
			/*
			 * 查詢開團中-報團(VISIT)
			 */
			VisitServiceVO vsVO = new VisitServiceVO();
			vsVO.setStatusId(StatusService.VISIT_23_JOIN);
			long openVisitCount = visitService.retriveOpenVisitCount(vsVO);
			
			request.setAttribute("openVisitCount", String.valueOf(openVisitCount));
			
			/*
			 * 查詢開團中-沙龍(SALON)
			 */
			SalonServiceVO ssVO = new SalonServiceVO();
			ssVO.setStatusId(StatusService.VISIT_23_JOIN);
			long openSalonCount = salonService.retriveOpenSalonCount(ssVO);
			
			request.setAttribute("openSalonCount", String.valueOf(openSalonCount));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "dashboard/board";
	}
	
}
