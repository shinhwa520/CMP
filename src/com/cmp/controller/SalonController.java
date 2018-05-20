package com.cmp.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmp.AppResponse;
import com.cmp.DatatableResponse;
import com.cmp.MenuItem;
import com.cmp.form.SalonForm;
import com.cmp.service.SalonService;
import com.cmp.service.vo.SalonServiceVO;

@Controller
@RequestMapping("/salon")
public class SalonController extends BaseController {
	private static Log log = LogFactory.getLog(SalonController.class);
	
	@Autowired
	private SalonService salonService;
	
	@RequestMapping(value = { "list/{statusId}","list" }, method = RequestMethod.GET)
    public String showSalonList(Model model, @ModelAttribute("SalonForm") SalonForm salonForm, HttpServletRequest request, HttpServletResponse response,
    							@PathVariable Optional<Integer> statusId) {
		SalonServiceVO reVO = null;
		try {
			reVO = new SalonServiceVO();
			reVO.setStatusId(
					statusId.isPresent() ? (statusId.get() != 23 ? null : statusId.get())
									 	 : null);
			reVO = salonService.findSalon(reVO);
			salonForm.setSalonList(reVO.getSalonList());
			
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error(e.toString(), e);
			}
			
		} finally {
			setActiveMenu(model, MenuItem.SALON_INFO);
		}
		return "salon/list";
    }
	
	@RequestMapping(value="save", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public AppResponse saveSalon(
			@RequestParam(name="salonId", required=false) Integer salonId,
			@RequestParam(name="salonName", required=true) String salonName,
			@RequestParam(name="salonDescription", required=false) String salonDescription,
			@RequestParam(name="beginDate", required=true) String beginDate,
			@RequestParam(name="endDate", required=true) String endDate,
			@RequestParam(name="minMemberCount", required=true) Integer minMemberCount,
			@RequestParam(name="maxMemberCount", required=true) Integer maxMemberCount,
			@RequestParam(name="status", required=true) Integer status,
			@RequestParam(name="remarks", required=false) String remarks) {
		
		SalonServiceVO ssVO = null;
		try {
			ssVO = new SalonServiceVO();
			ssVO.setSalonId(salonId);
			ssVO.setSalonName(salonName);
			ssVO.setDescription(salonDescription);
			ssVO.setBeginDate(beginDate);
			ssVO.setEndDate(endDate);
			ssVO.setMinMemberCount(minMemberCount);
			ssVO.setMaxMemberCount(maxMemberCount);
			ssVO.setStatus(status);
			ssVO.setRemark(remarks);
			
			salonService.saveSalon(ssVO);
			
			return new AppResponse(HttpServletResponse.SC_OK, "新增成功");
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error(e.toString(), e);
			}
			return new AppResponse(super.getLineNumber(), e.getMessage());
		}
    }
	
	@RequestMapping(value="getSalon", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public AppResponse getSalon(
			@RequestParam(name="salonId", required=true) Integer salonId) {
		
		SalonServiceVO reVO = null;
		try {
			reVO = salonService.getSalonById(salonId);
			
			AppResponse appResponse = new AppResponse(HttpServletResponse.SC_OK, "取得Salon資料成功");
			appResponse.putData("salon",  reVO);
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
	public AppResponse deleteSalon(
			@RequestParam(name="salonId", required=true) Integer salonId) {
		
		AppResponse appResponse = null;
		try {
			boolean success = salonService.deleteSalon(salonId);
			
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
			@RequestParam(name="salonId", required=false) Integer salonId,
			@RequestParam(name="deleteCustIds", required=true) String deleteCustIds,
			@RequestParam(name="addCustIds", required=true) String addCustIds) {
		
		SalonServiceVO reVO = null;
		try {
			reVO = salonService.joinMembers(salonId, deleteCustIds, addCustIds);
			
			AppResponse appResponse = new AppResponse(HttpServletResponse.SC_OK, "取得Salon資料成功");
			appResponse.putData("salon",  reVO);
			return appResponse;
			
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error(e.toString(), e);
			}
			return new AppResponse(super.getLineNumber(), e.getMessage());
		}
    }
	
	@RequestMapping(value="getSalonDetails", method = RequestMethod.POST, produces="application/json")
	public @ResponseBody DatatableResponse getSalonDetails(
			@RequestParam(name="start", required=false, defaultValue="0") Integer start,
			@RequestParam(name="length", required=false, defaultValue="25") Integer length,
			@RequestParam(name="salonId", required=true) Integer salonId) {
		
		List<SalonServiceVO> reList = salonService.findSalonDetails(salonId);
		
		long total = salonService.countSalonDetails(salonId);
		return new DatatableResponse(total, reList, total);
	}
	
	@RequestMapping(value="saveDetail", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public AppResponse saveSalon(
			@RequestParam(name="custBySalonId", required=true) Integer salonId,
			@RequestParam(name="remark", required=false) String[] remarkArray,
			@RequestParam(name="statusSort", required=false) Integer[] statusSortArray,
			@RequestParam(name="custId", required=true) Integer[] custIdArray) {
		
		SalonServiceVO ssVO = null;
		try {
			ssVO = new SalonServiceVO();
			ssVO.setSalonId(salonId);
			ssVO.setRemarkArray(remarkArray);
			ssVO.setCustIdArray(custIdArray);
			ssVO.setStatusSortArray(statusSortArray);
			
			salonService.saveSalonDetail(ssVO);
			
			return new AppResponse(HttpServletResponse.SC_OK, "新增成功");
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error(e.toString(), e);
			}
			return new AppResponse(super.getLineNumber(), e.getMessage());
		}
    }
}
