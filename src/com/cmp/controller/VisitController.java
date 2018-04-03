package com.cmp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmp.AppResponse;
import com.cmp.DatatableResponse;
import com.cmp.MenuItem;
import com.cmp.form.VisitForm;
import com.cmp.service.VisitService;
import com.cmp.service.vo.VisitServiceVO;

@Controller
@RequestMapping("/visit")
public class VisitController extends BaseController {
	private static Log log = LogFactory.getLog(VisitController.class);
	
	@Autowired
	private VisitService visitService;
	
	@RequestMapping(value = { "list" }, method = RequestMethod.GET)
    public String showVisitInfoList(Model model, HttpServletRequest request, HttpServletResponse response) {
		setActiveMenu(model, MenuItem.VISIT_INFO);
		return "visit/list";
    }
	
	@RequestMapping(value = { "tour" }, method = RequestMethod.GET)
    public String showTourList(Model model, @ModelAttribute("VisitForm") VisitForm visitForm, HttpServletRequest request, HttpServletResponse response) {
		VisitServiceVO reVO = null;
		try {
			reVO = visitService.findVisit(new VisitServiceVO());
			visitForm.setVisitList(reVO.getVisitList());
			visitForm.setCanAdd(reVO.getCanAdd());
			
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error(e.toString(), e);
			}
			
		} finally {
			setActiveMenu(model, MenuItem.VISIT_INFO);
		}
		return "visit/visit_tour";
    }
	
	@RequestMapping(value="save", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public AppResponse saveVisit(
			@RequestParam(name="visitId", required=false) Integer visitId,
			@RequestParam(name="visitName", required=true) String visitName,
			@RequestParam(name="visitDescription", required=false) String visitDescription,
			@RequestParam(name="beginDate", required=true) String beginDate,
			@RequestParam(name="endDate", required=true) String endDate,
			@RequestParam(name="minMemberCount", required=true) Integer minMemberCount,
			@RequestParam(name="maxMemberCount", required=true) Integer maxMemberCount,
			@RequestParam(name="departureDate", required=false) String departureDate,
			@RequestParam(name="departureTime", required=false) String departureTime,
			@RequestParam(name="departureCity", required=true) String departureCity,
			@RequestParam(name="arrivalDate", required=false) String arrivalDate,
			@RequestParam(name="arrivalTime", required=false) String arrivalTime,
			@RequestParam(name="arrivalAirportTerminal", required=false) String arrivalAirportTerminal,
			@RequestParam(name="returnDate", required=false) String returnDate,
			@RequestParam(name="returnTime", required=false) String returnTime,
			@RequestParam(name="returnAirportTerminal", required=false) String returnAirportTerminal,
			@RequestParam(name="status", required=true) Integer status,
			@RequestParam(name="remarks", required=false) String remarks) {
		
		VisitServiceVO vsVO = null;
		try {
			vsVO = new VisitServiceVO();
			vsVO.setVisitId(visitId);
			vsVO.setVisitName(visitName);
			vsVO.setDescription(visitDescription);
			vsVO.setBeginDate(beginDate);
			vsVO.setEndDate(endDate);
			vsVO.setMinMemberCount(minMemberCount);
			vsVO.setMaxMemberCount(maxMemberCount);
			vsVO.setDepartureDate(departureDate);
			vsVO.setDepartureTime(departureTime);
			vsVO.setDepartureCity(departureCity);
			vsVO.setArrivalDate(arrivalDate);
			vsVO.setArrivalTime(arrivalTime);
			vsVO.setArrivalAirportTerminal(arrivalAirportTerminal);
			vsVO.setReturnDate(returnDate);
			vsVO.setReturnTime(returnTime);
			vsVO.setReturnAirportTerminal(returnAirportTerminal);
			vsVO.setStatus(status);
			vsVO.setRemark(remarks);
			
			visitService.saveVisit(vsVO);
			
			return new AppResponse(HttpServletResponse.SC_OK, "新增成功");
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error(e.toString(), e);
			}
			return new AppResponse(super.getLineNumber(), e.getMessage());
		}
    }
	
	@RequestMapping(value="getVisit", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public AppResponse getVisit(
			@RequestParam(name="visitId", required=true) Integer visitId) {
		
		VisitServiceVO reVO = null;
		try {
			reVO = visitService.getVisitById(visitId);
			
			AppResponse appResponse = new AppResponse(HttpServletResponse.SC_OK, "取得Visit資料成功");
			appResponse.putData("visit",  reVO);
			return appResponse;
			
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error(e.toString(), e);
			}
			return new AppResponse(super.getLineNumber(), e.getMessage());
		}
    }
	
	@RequestMapping(value="delete", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public AppResponse deleteVisit(
			@RequestParam(name="visitId", required=true) Integer visitId) {
		
		AppResponse appResponse = null;
		try {
			boolean success = visitService.deleteVisit(visitId);
			
			if (success) {
				appResponse = new AppResponse(HttpServletResponse.SC_OK, "SUCCESS");
			} else {
				appResponse = new AppResponse(HttpServletResponse.SC_NOT_MODIFIED, "FAILED");
			}
			
			return appResponse;
			
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error(e.toString(), e);
			}
			return new AppResponse(super.getLineNumber(), e.getMessage());
		}
    }
	
	@RequestMapping(value="join", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public AppResponse joinMember(
			@RequestParam(name="visitId", required=false) Integer visitId,
			@RequestParam(name="deleteCustIds", required=true) String deleteCustIds,
			@RequestParam(name="addCustIds", required=true) String addCustIds) {
		
		VisitServiceVO reVO = null;
		try {
			reVO = visitService.joinMembers(visitId, deleteCustIds, addCustIds);
			
			AppResponse appResponse = new AppResponse(HttpServletResponse.SC_OK, "取得Visit資料成功");
			appResponse.putData("visit",  reVO);
			return appResponse;
			
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error(e.toString(), e);
			}
			return new AppResponse(super.getLineNumber(), e.getMessage());
		}
    }
	
	@RequestMapping(value="getVisitDetails", method = RequestMethod.POST, produces="application/json")
	public @ResponseBody DatatableResponse getVisitDetails(
			@RequestParam(name="start", required=false, defaultValue="0") Integer start,
			@RequestParam(name="length", required=false, defaultValue="25") Integer length,
			@RequestParam(name="visitId", required=true) Integer visitId) {
		
		List<VisitServiceVO> reList = visitService.findVisitDetails(visitId);
		
		long total = visitService.countVisitDetails(visitId);
		return new DatatableResponse(total, reList, total);
	}
}
