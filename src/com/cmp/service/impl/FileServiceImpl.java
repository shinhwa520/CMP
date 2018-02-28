package com.cmp.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
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
import com.cmp.dao.vo.FileDAOVO;
import com.cmp.model.FilesBaseConfig;
import com.cmp.model.FilesCustomer;
import com.cmp.model.FilesPermission;
import com.cmp.model.FilesPublic;
import com.cmp.model.FilesSetting;
import com.cmp.service.FileService;
import com.cmp.service.vo.FileServiceVO;
import com.cmp.utils.DeleteObjects2Aliyun;

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
	public FileServiceVO getFileByFileTypeAndSeqNoOrFileName(String fileType, Integer seqNo, String oriFileName) {
		FileServiceVO retVO = new FileServiceVO();
		FileDAOVO fileDAOVO;
		List<Object> modelList = null;
		
		try {
			fileDAOVO = new FileDAOVO();
			fileDAOVO.setSeqNo(seqNo);
			fileDAOVO.setUpperFileName(StringUtils.isNotBlank(oriFileName) ? oriFileName.toUpperCase() : oriFileName);
			
			if (StringUtils.equals(fileType, FILE_TYPE_PUBLIC)) {
				modelList = fileDAO.findPublicFileByDAOVO(fileDAOVO);
				
			} else if (StringUtils.equals(fileType, FILE_TYPE_CUSTOMER)) {
				modelList = fileDAO.findCustomerFileByDAOVO(fileDAOVO);
				
			} else {
				
			}
			
			if (modelList != null && !modelList.isEmpty()) {
				retVO = transModel2ServiceVO(modelList).get(0);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retVO;
	}
	
	@Override
	public List<FileServiceVO> findFilesByDAOVO(FileServiceVO fileServiceVO) {
		return null;
	}
	
	@Override
	public FileServiceVO downloadFileByFileServiceVO(FileServiceVO fileServiceVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileServiceVO addFile(FileServiceVO fileServiceVO) {
		String errMsg = null;
		FileServiceVO retVO = null;
		Integer addedSeqNo = null;
		
		try {
			retVO = getFileByFileTypeAndSeqNoOrFileName(fileServiceVO.getFileType(), null, fileServiceVO.getOriginFileName());
			
			if (retVO != null && retVO.getSeqNo() != null) {
				errMsg = "相同檔案名稱資料已存在，請調整名稱或先刪除既有資料後再新增";
				retVO.setErrMsg(errMsg);
				
			} else {
				fileServiceVO.setDownloadTimes(0);
				fileServiceVO.setFileName(
						fileServiceVO.getOriginFileName().indexOf(".") != -1 ? fileServiceVO.getOriginFileName().split("\\.")[0] : fileServiceVO.getOriginFileName());
				fileServiceVO.setFileExtension(
						fileServiceVO.getOriginFileName().indexOf(".") != -1 ? fileServiceVO.getOriginFileName().split("\\.")[1] : null);
				fileServiceVO.setUpperFileName(fileServiceVO.getOriginFileName().toUpperCase());
				
				// Model >> FilesPublic / FilesCustomer
				Object entity = null;
				if (StringUtils.equals(fileServiceVO.getFileType(), FILE_TYPE_PUBLIC)) {
					entity = new FilesPublic();
				} else if (StringUtils.equals(fileServiceVO.getFileType(), FILE_TYPE_CUSTOMER)) {
					entity = new FilesCustomer();
				}
				
				if (entity != null) {
					BeanUtils.copyProperties(fileServiceVO, entity);
					org.apache.commons.beanutils.BeanUtils.setProperty(entity, "createBy", "admin");
					org.apache.commons.beanutils.BeanUtils.setProperty(entity, "createTime", new Timestamp(new Date().getTime()));
					org.apache.commons.beanutils.BeanUtils.setProperty(entity, "updateBy", "admin");
					org.apache.commons.beanutils.BeanUtils.setProperty(entity, "updateTime", new Timestamp(new Date().getTime()));
				}
				
				// Model >> FilesSetting
				FilesBaseConfig fbcModel = fileDAO.findFilesBaseConfigByConfigName(fileServiceVO.getFileType());
				if (fbcModel == null) {
					throw new Exception("[ERROR]未設定FilesBaseConfig >> configName: " + fileServiceVO.getFileType());
				}
				
				FilesSetting fsModel = new FilesSetting();
				fsModel.setOrderNum(1);
				fsModel.setOnTop(fileServiceVO.getOnTopChkbox());
				
				Date beginDate = transDateStr2DateObj(fileServiceVO.getBeginDateStr(), fileServiceVO.getBeginTimeStr());
				Date endDate = transDateStr2DateObj(fileServiceVO.getEndDateStr(), fileServiceVO.getEndTimeStr());
				
				fsModel.setActivationBegin(beginDate == null ? null : new Timestamp(beginDate.getTime()));
				fsModel.setActivationEnd(endDate == null ? null : new Timestamp(endDate.getTime()));
				fsModel.setCreateTime(new Timestamp(new Date().getTime()));
				fsModel.setCreateBy("admin");
				fsModel.setUpdateTime(new Timestamp(new Date().getTime()));
				fsModel.setUpdateBy("admin");
				fsModel.setFilesBaseConfig(fbcModel);
				
				// Model >> FilesPermission
				FilesPermission fpModel = new FilesPermission();
				fpModel.setSettingType("1");
				fpModel.setSettingLevel("*");
				fpModel.setSettingValue("*");
				fpModel.setCreateTime(new Timestamp(new Date().getTime()));
				fpModel.setCreateBy("admin");
				fpModel.setUpdateTime(new Timestamp(new Date().getTime()));
				fpModel.setUpdateBy("admin");
				
				addedSeqNo = fileDAO.addFile(entity, fsModel, Arrays.asList(fpModel));
				retVO.setAddedSeqNo(addedSeqNo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retVO;
	}

	@Override
	public void deleteFile(String fileType, Integer[] seqNos, boolean deleteBucketFile) {
		FileDAOVO fileDAOVO;
		List<Object> reList = null;
		List<Object> modelList = null;
		List<String> bucketKeys = new ArrayList<String>();
		
		try {
			FilesBaseConfig config = fileDAO.findFilesBaseConfigByConfigName(fileType);
			
			if (config == null) {
				throw new Exception("[ERROR]未設定FilesBaseConfig >> configName: " + fileType);
			}
			
			for (Integer seqNo : seqNos) {
				modelList = new ArrayList<Object>();
				fileDAOVO = new FileDAOVO();
				fileDAOVO.setSeqNo(seqNo);
				
				if (StringUtils.equals(fileType, FILE_TYPE_PUBLIC)) {
					reList = fileDAO.findPublicFileByDAOVO(fileDAOVO);
					
				} else if (StringUtils.equals(fileType, FILE_TYPE_CUSTOMER)) {
					reList = fileDAO.findCustomerFileByDAOVO(fileDAOVO);
				}
				
				if (reList != null && !reList.isEmpty()) {
					bucketKeys.add((String)reList.get(0).getClass().getMethod("getOriginFileName").invoke(reList.get(0)));
					
					FilesSetting fs = (FilesSetting)reList.get(0).getClass().getMethod("getFilesSetting").invoke(reList.get(0));
					modelList.addAll(fs.getFilesPermissions());
					modelList.add(reList.get(0));
					modelList.add(fs);
					
					fileDAO.deleteFile(modelList);
				}
			}
			
			if (deleteBucketFile) {
				// 刪除阿里雲上的檔案
				new DeleteObjects2Aliyun().deleteObjects(config, bucketKeys);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Date transDateStr2DateObj(String dateStr, String timeStr) {
		String dateTimeStr = 
				StringUtils.isNotBlank(dateStr) ? 
						dateStr.concat(" ").concat(StringUtils.isNotBlank(timeStr) ? timeStr : "00:00:00")
								: null;
		Date date = null;
		try {
			date = dateTimeStr != null ? DATE_TIME_FORMAT.parse(dateTimeStr) : null;
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return date;
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
				
				Date beginDate = transDateStr2DateObj(fileServiceVO.getBeginDateStr(), fileServiceVO.getBeginTimeStr());
				Date endDate = transDateStr2DateObj(fileServiceVO.getEndDateStr(), fileServiceVO.getEndTimeStr());
				
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
	
	@Override
	public String modifyDownloadCount(String fileType, Integer seqNo) {
		FileDAOVO fileDAOVO;
		String entity = null;
		List<Object> modelList = null;
		FileServiceVO retVO = null;
		
		try {
			fileDAOVO = new FileDAOVO();
			fileDAOVO.setSeqNo(seqNo);
			
			if (StringUtils.equals(fileType, FILE_TYPE_PUBLIC)) {
				entity = "FilesPublic";
				modelList = fileDAO.findPublicFileByDAOVO(fileDAOVO);
				
			} else if (StringUtils.equals(fileType, FILE_TYPE_CUSTOMER)) {
				entity = "FilesCustomer";
				modelList = fileDAO.findCustomerFileByDAOVO(fileDAOVO);
			}
			
			if (modelList != null && !modelList.isEmpty()) {
				retVO = transModel2ServiceVO(modelList).get(0);
				
				fileDAO.addDownloadCount(entity, seqNo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retVO != null ? retVO.getFullFileName() : null;
	}

	@Override
	public FilesBaseConfig findFilesConfig(String configName) {
		FilesBaseConfig fbc = null;
		try {
			fbc = fileDAO.findFilesBaseConfigByConfigName(configName);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fbc;
	}
	
	@Autowired
	public void setFileDAO(FileDAO fileDAO) {
		this.fileDAO = fileDAO;
	}
}
