package com.cmp.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.FileDAO;
import com.cmp.dao.vo.BillboardDAOVO;
import com.cmp.dao.vo.FileDAOVO;
import com.cmp.model.BillboardContent;
import com.cmp.model.FilesCustomer;
import com.cmp.model.FilesPermission;
import com.cmp.model.FilesPublic;
import com.cmp.model.FilesSetting;
import com.cmp.service.FileService;
import com.cmp.service.vo.FileServiceVO;

@Service("fileService")
@Transactional
public class FileServiceImpl implements FileService {

	private SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");
	
	FileDAO fileDAO;

	@Override
	public List<FileServiceVO> findAllPublicFiles(boolean isAdmin, Integer startRow, Integer pageLength) {
		List<FileServiceVO> reList = null;
		List<Object> fpList;
		
		try {
			fpList = fileDAO.findAllPublicFile(isAdmin, startRow, pageLength);
			
			if (fpList != null && !fpList.isEmpty()) {
				reList = transModel2ServiceVO(fpList);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return reList;
	}
	
	@Override
	public List<FileServiceVO> findAllCustomerFiles(boolean isAdmin, Integer startRow, Integer pageLength) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private List<FileServiceVO> transModel2ServiceVO(List<Object> modelList) {
		List<FileServiceVO> reList = new ArrayList<FileServiceVO>();
		FilesPublic filesPublic;
		FilesCustomer filesCustomer;
		
		FileServiceVO fsVO;
		int dataSeq = 1;
		for (Object model : modelList) {
			if (model instanceof FilesPublic) {
				filesPublic = (FilesPublic)model;
				filesCustomer = null;
				
			} else {
				filesPublic = null;
				filesCustomer = (FilesCustomer)model;
			}
			
			fsVO = new FileServiceVO();
			fsVO.setDataSeq(dataSeq);
			BeanUtils.copyProperties(model, fsVO, new String[] {"updateTime"});
			fsVO.setFullFileName(filesPublic != null ? filesPublic.getFileName().concat(".").concat(filesPublic.getFileExtension())
													 : filesCustomer.getFileName().concat(".").concat(filesCustomer.getFileExtension())
								);
			fsVO.setOnTopChkbox(filesPublic != null ? filesPublic.getFilesSetting().getOnTop() : filesCustomer.getFilesSetting().getOnTop());
			fsVO.setUpdateTime(
					DATE_TIME_FORMAT.format(filesPublic != null ? filesPublic.getUpdateTime() : filesCustomer.getUpdateTime()));
			
			if (filesPublic != null ? filesPublic.getFilesSetting().getActivationBegin() != null
									: filesCustomer.getFilesSetting().getActivationBegin() != null) {
				fsVO.setBeginDateStr(DATE_FORMAT.format(filesPublic != null ? filesPublic.getFilesSetting().getActivationBegin() : filesCustomer.getFilesSetting().getActivationBegin()));
				fsVO.setBeginTimeStr(TIME_FORMAT.format(filesPublic != null ? filesPublic.getFilesSetting().getActivationBegin() : filesCustomer.getFilesSetting().getActivationBegin()));
			}
			
			if (filesPublic != null ? filesPublic.getFilesSetting().getActivationEnd() != null
									: filesCustomer.getFilesSetting().getActivationEnd() != null) {
				fsVO.setEndDateStr(DATE_FORMAT.format(filesPublic != null ? filesPublic.getFilesSetting().getActivationEnd() : filesCustomer.getFilesSetting().getActivationEnd()));
				fsVO.setEndTimeStr(TIME_FORMAT.format(filesPublic != null ? filesPublic.getFilesSetting().getActivationEnd() : filesCustomer.getFilesSetting().getActivationEnd()));
			}
			
			BeanUtils.copyProperties(filesPublic != null ? filesPublic.getFilesSetting().getFilesBaseConfig() : filesCustomer.getFilesSetting().getFilesBaseConfig(), fsVO, new String[] {"updateTime"});
			fsVO.setFileType(FILE_TYPE_PUBLIC);
			reList.add(fsVO);
			
			dataSeq += 1;
		}
		
		return reList;
	}

	@Override
	public FileServiceVO getFileByFileTypeAndSeqNo(String fileType, Integer seqNo) {
		FileServiceVO retVO = new FileServiceVO();
		FileDAOVO fileDAOVO;
		List<Object> modelList = null;
		
		try {
			fileDAOVO = new FileDAOVO();
			fileDAOVO.setSeqNo(seqNo);
			
			if (StringUtils.equals(fileType, FILE_TYPE_PUBLIC)) {
				modelList = fileDAO.findPublicFileByDAOVO(fileDAOVO);
				
			} else if (StringUtils.equals(fileType, FILE_TYPE_CUSTOMER)) {
				modelList = fileDAO.findCustomerFileByDAOVO(fileDAOVO);
				
			} else {
				
			}
			
			if (modelList != null) {
				retVO = transModel2ServiceVO(modelList).get(0);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retVO;
	}
	
	@Override
	public FileServiceVO downloadFileByFileServiceVO(FileServiceVO fileServiceVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFile(FileServiceVO fileServiceVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFile(String fileType, Integer[] seqNos) {
		FileDAOVO fileDAOVO;
		List<Object> reList = null;
		List<Object> modelList = new ArrayList<Object>();
		try {
			for (Integer seqNo : seqNos) {
				fileDAOVO = new FileDAOVO();
				fileDAOVO.setSeqNo(seqNo);
				
				if (StringUtils.equals(fileType, FILE_TYPE_PUBLIC)) {
					reList = fileDAO.findPublicFileByDAOVO(fileDAOVO);
					
				} else if (StringUtils.equals(fileType, FILE_TYPE_CUSTOMER)) {
					reList = fileDAO.findCustomerFileByDAOVO(fileDAOVO);
				}
				
				if (reList != null && !reList.isEmpty()) {
					FilesSetting fs = (FilesSetting)reList.get(0).getClass().getMethod("getFilesSetting").invoke(reList.get(0));
					modelList.addAll(fs.getFilesPermissions());
					modelList.add(reList.get(0));
					modelList.add(fs);
					
					fileDAO.deleteFile(modelList);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void modifyFile(FileServiceVO fileServiceVO) {
		FileDAOVO fileDAOVO;
		List<Object> modelList = null;
		Object sourceModel;
		FilesSetting fsModel;
		
		try {
			
			fileDAOVO = new FileDAOVO();
			fileDAOVO.setSeqNo(fileServiceVO.getSeqNo());
			
			if (StringUtils.equals(fileServiceVO.getFileType(), FILE_TYPE_PUBLIC)) {
				modelList = fileDAO.findPublicFileByDAOVO(fileDAOVO);
				
			} else if (StringUtils.equals(fileServiceVO.getFileType(), FILE_TYPE_CUSTOMER)) {
				modelList = fileDAO.findCustomerFileByDAOVO(fileDAOVO);
				
			} else {
				throw new Exception("[ERROR] fileType not exists! >> " + fileServiceVO.getFileType());
			}
			
			if (modelList != null && !modelList.isEmpty()) {
				sourceModel = modelList.get(0);
				org.apache.commons.beanutils.BeanUtils.setProperty(sourceModel, "fileDescription", fileServiceVO.getFileDescription());
				org.apache.commons.beanutils.BeanUtils.setProperty(sourceModel, "updateTime", new Timestamp(new Date().getTime()));
				org.apache.commons.beanutils.BeanUtils.setProperty(sourceModel, "updateBy", "admin");
				
				
				fsModel = (FilesSetting)sourceModel.getClass().getMethod("getFilesSetting").invoke(sourceModel);
				fsModel.setOnTop(fileServiceVO.getOnTopChkbox());
				
				String beginDateStr = 
						StringUtils.isNotBlank(fileServiceVO.getBeginDateStr()) ? 
								fileServiceVO.getBeginDateStr().concat(" ").concat(StringUtils.isNotBlank(fileServiceVO.getBeginTimeStr()) ? fileServiceVO.getBeginTimeStr() : "00:00:00")
										: null;
				Date beginDate = beginDateStr != null ? DATE_TIME_FORMAT.parse(beginDateStr) : null;
				
				String endDateStr = 
						StringUtils.isNotBlank(fileServiceVO.getEndDateStr()) ? 
								fileServiceVO.getEndDateStr().concat(" ").concat(StringUtils.isNotBlank(fileServiceVO.getEndTimeStr()) ? fileServiceVO.getEndTimeStr() : "00:00:00")
										: null;
				Date endDate = endDateStr != null ? DATE_TIME_FORMAT.parse(endDateStr) : null;
				
				fsModel.setActivationBegin(beginDate == null ? null : new Timestamp(beginDate.getTime()));
				fsModel.setActivationEnd(endDate == null ? null : new Timestamp(endDate.getTime()));
				fsModel.setUpdateTime(new Timestamp(new Date().getTime()));
				fsModel.setUpdateBy("admin");
				
				fileDAO.modifyFile(Arrays.asList(new Object[]{sourceModel, fsModel}));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Autowired
	public void setFileDAO(FileDAO fileDAO) {
		this.fileDAO = fileDAO;
	}
}
