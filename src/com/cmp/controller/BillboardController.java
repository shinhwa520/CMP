package com.cmp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
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
import com.cmp.MenuItem;
import com.cmp.form.BillboardForm;
import com.cmp.model.Customer;
import com.cmp.service.BillboardService;
import com.cmp.service.vo.BillboardServiceVO;

@Controller
@RequestMapping("/")
public class BillboardController extends BaseController {
	private static Log log = LogFactory.getLog(BillboardController.class);
	
	BillboardService billboardService;
	
	@RequestMapping(value = { "/manage/billboard" }, method = RequestMethod.GET)
    public String billboardMain(Model model, @ModelAttribute("BillboardForm") BillboardForm form, HttpServletRequest request, HttpServletResponse response) {
		List<BillboardServiceVO> billboardList;
		try {
			billboardList = billboardService.findAllBillboardRecords(true, 0, 500);
			form.setBillboardList(billboardList);
			form.setAdminRole(true);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			setActiveMenu(model, MenuItem.MANAGE_BILLBOARD);
		}
		
		return "manage/billboard";
    }
	
	@RequestMapping(value = { "/manage/billboard/delete" }, method = RequestMethod.POST)
    public String deleteRecords(Model model, @ModelAttribute("BillboardForm") BillboardForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			billboardService.deleteBillboardRecords(form.getDelChkbox());
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			setActiveMenu(model, MenuItem.MANAGE_BILLBOARD);
		}
		
		return billboardMain(model, form, request, response);
    }
	
	@RequestMapping(value="/manage/billboard/getBillboard", method = RequestMethod.POST, produces="application/json")
	public @ResponseBody AppResponse getBillboardBySeqNo(
			@RequestParam(name="seqNo", required=true) Integer seqNo) {
		try {
			BillboardServiceVO retVO = billboardService.getBillboardBySeqNo(seqNo);
			AppResponse appResponse = new AppResponse(HttpServletResponse.SC_OK, "取得Customer資料成功");
			appResponse.putData("billboard",  retVO);
			return appResponse;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			return new AppResponse(super.getLineNumber(), e.getMessage());
		}
	}
	
	@RequestMapping(value="/manage/billboard/modify", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public AppResponse modifyBillboard(
			@RequestParam(name="seqNo", required=false) Integer seqNo,
			@RequestParam(name="title", required=true) String title,
			@RequestParam(name="onTopChkbox", required=true) String onTopChkbox,
			@RequestParam(name="isOpenedChkbox", required=true) String isOpenedChkbox,
			@RequestParam(name="beginDateStr", required=false) String beginDateStr,
			@RequestParam(name="beginTimeStr", required=false) String beginTimeStr,
			@RequestParam(name="endDateStr", required=false) String endDateStr,
			@RequestParam(name="endTimeStr", required=false) String endTimeStr,
			@RequestParam(name="contentTxt", required=true) String contentTxt) {
		
		BillboardServiceVO bsVO;
		try {
			bsVO = new BillboardServiceVO();
			bsVO.setSeqNo(seqNo);
			bsVO.setTitle(title);
			bsVO.setContent(contentTxt);
			bsVO.setIsOpenedChkbox(isOpenedChkbox);
			bsVO.setOnTopChkbox(onTopChkbox);
			bsVO.setBeginDateStr(beginDateStr);
			bsVO.setBeginTimeStr(beginTimeStr);
			bsVO.setEndDateStr(endDateStr);
			bsVO.setEndTimeStr(endTimeStr);
			billboardService.modifyBillboardRecord(bsVO);
			
			return new AppResponse(HttpServletResponse.SC_OK, seqNo != null ? "修改成功" : "新增成功");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			return new AppResponse(super.getLineNumber(), e.getMessage());
		}
		
	}
	
	@Autowired
	public void setBillboardService(BillboardService billboardService) {
		this.billboardService = billboardService;
	}
}
