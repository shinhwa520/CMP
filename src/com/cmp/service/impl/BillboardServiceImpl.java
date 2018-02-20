package com.cmp.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.BillboardDAO;
import com.cmp.dao.vo.BillboardDAOVO;
import com.cmp.model.BillboardContent;
import com.cmp.model.BillboardPermission;
import com.cmp.model.BillboardSetting;
import com.cmp.service.BillboardService;
import com.cmp.service.vo.BillboardServiceVO;

@Service("billboardService")
@Transactional
public class BillboardServiceImpl implements BillboardService {
	
	private SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");
	
	private BillboardDAO billboardDAO;

	@Override
	public List<BillboardServiceVO> findAllBillboardRecords(boolean isAdmin, Integer startRow, Integer pageLength) {
		List<BillboardServiceVO> resulteList = new ArrayList<BillboardServiceVO>();
		List<BillboardContent> bcList;
		BillboardServiceVO bsVO;
		
		try {
			bcList = billboardDAO.findAllBillboardRecords(isAdmin, startRow, pageLength);
			if (bcList != null && !bcList.isEmpty()) {
				for (BillboardContent bc : bcList) {
					bsVO = new BillboardServiceVO();
					bsVO.setSeqNo(bc.getSeqNo());
					bsVO.setTitle(bc.getTitle());
					bsVO.setContent(bc.getContent());
					bsVO.setIsOpenedChkbox(bc.getBillboardSetting().getIsOpened());
					bsVO.setOnTopChkbox(bc.getBillboardSetting().getOnTop());
					bsVO.setUpdateTime(DATE_TIME_FORMAT.format(bc.getUpdateTime()));
					
					if (bc.getBillboardSetting().getActivationBegin() != null) {
						bsVO.setBeginDateStr(DATE_FORMAT.format(bc.getBillboardSetting().getActivationBegin()));
						bsVO.setBeginTimeStr(TIME_FORMAT.format(bc.getBillboardSetting().getActivationBegin()));
					}
					
					if (bc.getBillboardSetting().getActivationEnd() != null) {
						bsVO.setEndDateStr(DATE_FORMAT.format(bc.getBillboardSetting().getActivationEnd()));
						bsVO.setEndTimeStr(TIME_FORMAT.format(bc.getBillboardSetting().getActivationEnd()));
					}
					
					resulteList.add(bsVO);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resulteList;
	}
	
	public void modifyBillboardRecord(BillboardServiceVO bsVO) {
		List<BillboardContent> reList;
		BillboardDAOVO billDAOVO;
		BillboardContent billContent = null;
		BillboardSetting billSetting = null;
		List<BillboardPermission> oldBillPermissions = null;
		List<BillboardPermission> newBillPermissions = null;
		
		boolean isAdd = true;
		try {
			if (bsVO.getSeqNo() != null) {
				billDAOVO = new BillboardDAOVO();
				billDAOVO.setSeqNo(bsVO.getSeqNo());
				
				reList = billboardDAO.findBillboardRecordsByDAOVO(billDAOVO);
				
				if (reList != null && !reList.isEmpty()) {
					billContent = reList.get(0);
					billSetting = reList.get(0).getBillboardSetting();
					oldBillPermissions = reList.get(0).getBillboardPermissions();
					isAdd = false;
				}
				
			} else {
				billContent = new BillboardContent();
				billSetting = new BillboardSetting();
				newBillPermissions = new ArrayList<BillboardPermission>();
			}
			
			billContent.setContent(bsVO.getContent());
			billContent.setTitle(bsVO.getTitle());
			billContent.setCreateBy(isAdd ? "admin" : billContent.getCreateBy());
			billContent.setCreateTime(isAdd ? new Timestamp(new Date().getTime()) : billContent.getCreateTime());
			billContent.setUpdateBy("admin");
			billContent.setUpdateTime(new Timestamp(new Date().getTime()));
			
			Date beginDate = 
					(StringUtils.isNotBlank(bsVO.getBeginDateStr()) && StringUtils.isNotBlank(bsVO.getBeginTimeStr()))
						? DATE_TIME_FORMAT.parse(bsVO.getBeginDateStr().concat(" ").concat(bsVO.getBeginTimeStr()))
						: null;
			Date endDate = 
					(StringUtils.isNotBlank(bsVO.getEndDateStr()) && StringUtils.isNotBlank(bsVO.getEndTimeStr()))
						? DATE_TIME_FORMAT.parse(bsVO.getEndDateStr().concat(" ").concat(bsVO.getEndTimeStr()))
						: null;
			billSetting.setId(isAdd ? UUID.randomUUID().toString().replace("-", "") : billSetting.getId());
			billSetting.setOrderNum(1);
			billSetting.setIsOpened(StringUtils.isNotBlank(bsVO.getIsOpenedChkbox()) ? bsVO.getIsOpenedChkbox() : "N");
			billSetting.setOnTop(StringUtils.isNotBlank(bsVO.getOnTopChkbox()) ? bsVO.getOnTopChkbox() : "N");
			billSetting.setActivationBegin(beginDate == null ? null : new Timestamp(beginDate.getTime()));
			billSetting.setActivationEnd(endDate == null ? null : new Timestamp(endDate.getTime()));
			billSetting.setCreateBy(isAdd ? "admin" : billContent.getCreateBy());
			billContent.setCreateTime(isAdd ? new Timestamp(new Date().getTime()) : billSetting.getCreateTime());
			billSetting.setUpdateBy("admin");
			billContent.setUpdateTime(new Timestamp(new Date().getTime()));
			
			if (isAdd) {
				BillboardPermission newBillPermission = new BillboardPermission();
				newBillPermission.setId(UUID.randomUUID().toString().replace("-", ""));
				newBillPermission.setBillboardContent(billContent);
				newBillPermission.setSettingType("1");
				newBillPermission.setSettingLevel("*");
				newBillPermission.setSettingValue("*");
				newBillPermission.setCreateBy("admin");
				newBillPermission.setCreateTime(isAdd ? new Timestamp(new Date().getTime()) : newBillPermission.getCreateTime());
				newBillPermission.setUpdateBy("admin");
				billContent.setUpdateTime(new Timestamp(new Date().getTime()));
				newBillPermissions.add(newBillPermission);
			}
			
			billboardDAO.modifyBillboardRecord(isAdd, billContent, billSetting, oldBillPermissions, newBillPermissions);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public BillboardServiceVO getBillboardBySeqNo(Integer seqNo) {
		BillboardDAOVO billboardDAOVO;
		BillboardServiceVO retVO = new BillboardServiceVO();
		try {
			billboardDAOVO = new BillboardDAOVO();
			billboardDAOVO.setSeqNo(seqNo);
			
			BillboardContent bc;
			List<BillboardContent> reList = billboardDAO.findBillboardRecordsByDAOVO(billboardDAOVO);
			if (reList != null && !reList.isEmpty()) {
				bc = reList.get(0);
				
				retVO.setTitle(bc.getTitle());
				retVO.setContent(bc.getContent());
				retVO.setIsOpenedChkbox(bc.getBillboardSetting().getIsOpened());
				retVO.setOnTopChkbox(bc.getBillboardSetting().getOnTop());
				retVO.setBeginDateStr(bc.getBillboardSetting().getActivationBegin() == null ? null : DATE_FORMAT.format(bc.getBillboardSetting().getActivationBegin()));
				retVO.setBeginTimeStr(bc.getBillboardSetting().getActivationBegin() == null ? null : TIME_FORMAT.format(bc.getBillboardSetting().getActivationBegin()));
				retVO.setEndDateStr(bc.getBillboardSetting().getActivationEnd() == null ? null : DATE_FORMAT.format(bc.getBillboardSetting().getActivationEnd()));
				retVO.setEndTimeStr(bc.getBillboardSetting().getActivationEnd() == null ? null : TIME_FORMAT.format(bc.getBillboardSetting().getActivationEnd()));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retVO;
	}
	
	@Override
	public void deleteBillboardRecords(Integer[] seqNos) {
		BillboardDAOVO billboardDAOVO;
		List<BillboardContent> reList;
		List<Object> modelList = new ArrayList<Object>();
		try {
			for (Integer seqNo : seqNos) {
				billboardDAOVO = new BillboardDAOVO();
				billboardDAOVO.setSeqNo(seqNo);
				reList = billboardDAO.findBillboardRecordsByDAOVO(billboardDAOVO);
				
				if (reList != null && !reList.isEmpty()) {
					modelList.addAll(reList.get(0).getBillboardPermissions());
					modelList.add(reList.get(0));
					modelList.add(reList.get(0).getBillboardSetting());
				}
			}
			
			billboardDAO.deleteBillboardRecords(modelList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Autowired
	public void setBillboardContentDAO(BillboardDAO billboardDAO) {
		this.billboardDAO = billboardDAO;
	}
}
