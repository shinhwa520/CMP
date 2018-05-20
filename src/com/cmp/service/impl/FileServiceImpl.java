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
import com.cmp.model.FilesProduct;
import com.cmp.model.FilesPublic;
import com.cmp.model.FilesReference;
import com.cmp.model.FilesSetting;
import com.cmp.model.FilesVisit;
import com.cmp.model.ProductInfo;
import com.cmp.model.ReferenceInfo;
import com.cmp.security.SecurityUtil;
import com.cmp.service.FileService;
import com.cmp.service.vo.FileServiceVO;
import com.cmp.service.vo.VisitServiceVO;
import com.cmp.utils.DeleteObjects2Aliyun;

@Service("fileService")
@Transactional
public class FileServiceImpl implements FileService {

	private SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");
	
	@Autowired
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
	
	private List<FileServiceVO> transProductModel2ServiceVO(List<Object[]> modelList) {
		List<FileServiceVO> reList = new ArrayList<FileServiceVO>();
		FilesProduct filesProduct = null;
		ProductInfo productInfo = null;
		
		FileServiceVO fsVO;
		int dataSeq = 1;
		for (Object[] models : modelList) {
			filesProduct = (FilesProduct)models[0];
			productInfo = (ProductInfo)models[1];
			
			if (filesProduct != null) {
				fsVO = new FileServiceVO();
				fsVO.setDataSeq(dataSeq);
				BeanUtils.copyProperties(filesProduct, fsVO, new String[] {"updateTime"});
				fsVO.setFullFileName(filesProduct.getFileName().concat(".").concat(filesProduct.getFileExtension()));
				fsVO.setUpdateTime(filesProduct.getUpdateTime() != null ? DATE_TIME_FORMAT.format(filesProduct.getUpdateTime()) : "");
				
				fsVO.setOnTopChkbox(filesProduct.getFilesSetting().getOnTop());
				
				fsVO.setBeginDateStr(filesProduct.getFilesSetting().getActivationBegin() != null ? DATE_FORMAT.format(filesProduct.getFilesSetting().getActivationBegin()) : "");
				fsVO.setBeginTimeStr(filesProduct.getFilesSetting().getActivationBegin() != null ? TIME_FORMAT.format(filesProduct.getFilesSetting().getActivationBegin()) : "");
			
				fsVO.setEndDateStr(filesProduct.getFilesSetting().getActivationEnd() != null ? DATE_FORMAT.format(filesProduct.getFilesSetting().getActivationEnd()) : "");
				fsVO.setEndTimeStr(filesProduct.getFilesSetting().getActivationEnd() != null ? TIME_FORMAT.format(filesProduct.getFilesSetting().getActivationEnd()) : "");
				
				FilesBaseConfig config = filesProduct.getFilesSetting().getFilesBaseConfig();
				fsVO.setFileType(config != null ? config.getConfigName() : "");
				
				if (productInfo != null) {
					fsVO.setProductName(
							StringUtils.isNotBlank(productInfo.getEngName()) 
								? productInfo.getEngName().concat(" - ").concat(productInfo.getProductName())
								: productInfo.getProductName());
				}
				reList.add(fsVO);
			}
			
			dataSeq += 1;
		}
		
		return reList;
	}
	
	private List<FileServiceVO> transReferenceModel2ServiceVO(List<Object[]> modelList) {
		List<FileServiceVO> reList = new ArrayList<FileServiceVO>();
		FilesReference filesReference = null;
		ReferenceInfo referenceInfo = null;
		
		FileServiceVO fsVO;
		int dataSeq = 1;
		for (Object[] models : modelList) {
			filesReference = (FilesReference)models[0];
			referenceInfo = (ReferenceInfo)models[1];
			
			if (filesReference != null) {
				fsVO = new FileServiceVO();
				fsVO.setDataSeq(dataSeq);
				BeanUtils.copyProperties(filesReference, fsVO, new String[] {"updateTime"});
				fsVO.setFullFileName(filesReference.getFileName().concat(".").concat(filesReference.getFileExtension()));
				fsVO.setUpdateTime(filesReference.getUpdateTime() != null ? DATE_TIME_FORMAT.format(filesReference.getUpdateTime()) : "");
				
				fsVO.setOnTopChkbox(filesReference.getFilesSetting().getOnTop());
				
				fsVO.setBeginDateStr(filesReference.getFilesSetting().getActivationBegin() != null ? DATE_FORMAT.format(filesReference.getFilesSetting().getActivationBegin()) : "");
				fsVO.setBeginTimeStr(filesReference.getFilesSetting().getActivationBegin() != null ? TIME_FORMAT.format(filesReference.getFilesSetting().getActivationBegin()) : "");
			
				fsVO.setEndDateStr(filesReference.getFilesSetting().getActivationEnd() != null ? DATE_FORMAT.format(filesReference.getFilesSetting().getActivationEnd()) : "");
				fsVO.setEndTimeStr(filesReference.getFilesSetting().getActivationEnd() != null ? TIME_FORMAT.format(filesReference.getFilesSetting().getActivationEnd()) : "");
				
				FilesBaseConfig config = filesReference.getFilesSetting().getFilesBaseConfig();
				fsVO.setFileType(config != null ? config.getConfigName() : "");
				
				if (referenceInfo != null) {
					fsVO.setReferenceName(
							StringUtils.isNotBlank(referenceInfo.getEngName()) 
								? referenceInfo.getEngName().concat(" - ").concat(referenceInfo.getReferenceName())
								: referenceInfo.getReferenceName());
				}
				reList.add(fsVO);
			}
			
			dataSeq += 1;
		}
		
		return reList;
	}
	
	private List<FileServiceVO> transModel2ServiceVO(List<Object> modelList) {
		List<FileServiceVO> reList = new ArrayList<FileServiceVO>();
		FilesPublic filesPublic;
		FilesCustomer filesCustomer;
		FilesProduct filesProduct;
		FilesVisit filesVisit;
		
		FileServiceVO fsVO;
		int dataSeq = 1;
		for (Object model : modelList) {
			if (model instanceof FilesPublic) {
				filesPublic = (FilesPublic)model;
				filesCustomer = null;
				filesProduct = null;
				filesVisit = null;
				
			} else if (model instanceof FilesCustomer) {
				filesPublic = null;
				filesCustomer = (FilesCustomer)model;
				filesProduct = null;
				filesVisit = null;
				
			} else if (model instanceof FilesProduct) {
				filesPublic = null;
				filesCustomer = null;
				filesProduct = (FilesProduct)model;
				filesVisit = null;
				
			} else  {
				filesPublic = null;
				filesCustomer = null;
				filesProduct = null;
				filesVisit = (FilesVisit)model;
			}
			
			fsVO = new FileServiceVO();
			fsVO.setDataSeq(dataSeq);
			BeanUtils.copyProperties(model, fsVO, new String[] {"updateTime"});
			fsVO.setFullFileName(filesPublic != null ? filesPublic.getFileName().concat(".").concat(filesPublic.getFileExtension())
													 : filesCustomer != null ? filesCustomer.getFileName().concat(".").concat(filesCustomer.getFileExtension())
															 : filesProduct != null ? filesProduct.getFileName().concat(".").concat(filesProduct.getFileExtension())
																	 : filesVisit.getFileName().concat(".").concat(filesVisit.getFileExtension())
								);
			
			fsVO.setUpdateTime(
					DATE_TIME_FORMAT.format(
							filesPublic != null ? filesPublic.getUpdateTime() 
												: filesCustomer != null ? filesCustomer.getUpdateTime()
																		: filesProduct != null ? filesProduct.getUpdateTime()
																							   : filesVisit.getUpdateTime()));
			
			if (filesProduct == null && filesVisit == null) {
				fsVO.setOnTopChkbox(filesPublic != null ? filesPublic.getFilesSetting().getOnTop() : filesCustomer.getFilesSetting().getOnTop());
				
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
			}
			
//			BeanUtils.copyProperties(filesPublic != null ? filesPublic.getFilesSetting().getFilesBaseConfig() : filesCustomer.getFilesSetting().getFilesBaseConfig(), fsVO, new String[] {"updateTime"});
			FilesBaseConfig config = 
					filesPublic != null ? filesPublic.getFilesSetting().getFilesBaseConfig() 
										: filesCustomer != null ? filesCustomer.getFilesSetting().getFilesBaseConfig()
																: filesProduct != null ? filesProduct.getFilesSetting().getFilesBaseConfig()
																					   : filesVisit.getFilesSetting().getFilesBaseConfig();
			fsVO.setFileType(config.getConfigName());
			reList.add(fsVO);
			
			dataSeq += 1;
		}
		
		return reList;
	}

	@Override
	public FileServiceVO getFileByFileTypeAndSeqNoOrFileName(boolean isAdmin, String fileType, Integer seqNo, String oriFileName) {
		FileServiceVO retVO = new FileServiceVO();
		FileDAOVO fileDAOVO;
		List<Object> modelList = null;
		List<Object[]> model2List = null;
		
		try {
			fileDAOVO = new FileDAOVO();
			fileDAOVO.setSeqNo(seqNo);
			fileDAOVO.setUpperFileName(StringUtils.isNotBlank(oriFileName) ? oriFileName.toUpperCase() : oriFileName);
			
			if (StringUtils.equals(fileType, FILE_TYPE_PUBLIC)) {
				modelList = fileDAO.findPublicFileByDAOVO(fileDAOVO, null, null);
				
			} else if (StringUtils.equals(fileType, FILE_TYPE_CUSTOMER)) {
				modelList = fileDAO.findCustomerFileByDAOVO(fileDAOVO, null, null);
				
			} else if (StringUtils.equals(fileType, FILE_TYPE_PRODUCT)) {
				fileDAOVO.setAdmin(isAdmin);
				model2List = fileDAO.findProductFileByDAOVO(fileDAOVO, null, null);
				
			} else if (StringUtils.equals(fileType, FILE_TYPE_REFERENCE)) {
				fileDAOVO.setAdmin(isAdmin);
				model2List = fileDAO.findReferenceFileByDAOVO(fileDAOVO, null, null);
				
			} else {
				
			}
			
			if (!StringUtils.equals(fileType, FILE_TYPE_PRODUCT) && !StringUtils.equals(fileType, FILE_TYPE_REFERENCE)) {
				if (modelList != null && !modelList.isEmpty()) {
					retVO = transModel2ServiceVO(modelList).get(0);
				}
				
			} else {
				if (StringUtils.equals(fileType, FILE_TYPE_PRODUCT) && model2List != null && !model2List.isEmpty()) {
					retVO = transProductModel2ServiceVO(model2List).get(0);
					
				} else if (StringUtils.equals(fileType, FILE_TYPE_REFERENCE) && model2List != null && !model2List.isEmpty()) {
					retVO = transReferenceModel2ServiceVO(model2List).get(0);
				}
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
			String id = "";
			if (StringUtils.equals(fileServiceVO.getFileType(), FILE_TYPE_PUBLIC)) {
				id = "ALL";
			} else if (StringUtils.equals(fileServiceVO.getFileType(), FILE_TYPE_CUSTOMER)) {
				id = fileServiceVO.getCustId() != null ? fileServiceVO.getCustId().toString() : "ALL";
				
			} else if (StringUtils.equals(fileServiceVO.getFileType(), FILE_TYPE_PRODUCT)) {
				id = fileServiceVO.getProductId() != null ? fileServiceVO.getProductId().toString() : "ALL";
				
			} else if (StringUtils.equals(fileServiceVO.getFileType(), FILE_TYPE_VISIT)) {
				id = fileServiceVO.getVisitId() != null ? fileServiceVO.getVisitId().toString() : "ALL";
				
			} else if (StringUtils.equals(fileServiceVO.getFileType(), FILE_TYPE_REFERENCE)) {
				id = fileServiceVO.getReferenceId() != null ? fileServiceVO.getReferenceId().toString() : "ALL";
				
			}
			fileServiceVO.setUpperFileName(
					(fileServiceVO.getFileType()+"_"+id+"_"+fileServiceVO.getOriginFileName()).toUpperCase());
			
			retVO = getFileByFileTypeAndSeqNoOrFileName(true, fileServiceVO.getFileType(), null, fileServiceVO.getUpperFileName());
			
			if (retVO != null && retVO.getSeqNo() != null) {
				errMsg = "相同檔案名稱資料已存在，請調整名稱或先刪除既有資料後再新增";
				retVO.setErrMsg(errMsg);
				
			} else {
				fileServiceVO.setDownloadTimes(0);
				String oriName = fileServiceVO.getOriginFileName();
				fileServiceVO.setFileName(
						oriName.lastIndexOf(".") != -1 ? oriName.substring(0, oriName.lastIndexOf(".")) : oriName);
				fileServiceVO.setFileExtension(
						oriName.lastIndexOf(".") != -1 ? oriName.substring(oriName.lastIndexOf(".")+1, oriName.length()) : null);
				
				retVO.setUpperFileName(fileServiceVO.getUpperFileName());
				
				// Model >> FilesPublic / FilesCustomer
				Object entity = null;
				if (StringUtils.equals(fileServiceVO.getFileType(), FILE_TYPE_PUBLIC)) {
					entity = new FilesPublic();
					
				} else if (StringUtils.equals(fileServiceVO.getFileType(), FILE_TYPE_CUSTOMER)) {
					entity = new FilesCustomer();
					((FilesCustomer)entity).setCustId(fileServiceVO.getCustId());
					
				} else if (StringUtils.equals(fileServiceVO.getFileType(), FILE_TYPE_PRODUCT)) {
					entity = new FilesProduct();
					((FilesProduct)entity).setProductId(fileServiceVO.getProductId());
					
				} else if (StringUtils.equals(fileServiceVO.getFileType(), FILE_TYPE_VISIT)) {
					entity = new FilesVisit();
					((FilesVisit)entity).setVisitId(fileServiceVO.getVisitId());
					
				} else if (StringUtils.equals(fileServiceVO.getFileType(), FILE_TYPE_REFERENCE)) {
					entity = new FilesReference();
					((FilesReference)entity).setReferenceId(fileServiceVO.getReferenceId());
				}
				
				if (entity != null) {
					BeanUtils.copyProperties(fileServiceVO, entity);
					org.apache.commons.beanutils.BeanUtils.setProperty(entity, "createBy", SecurityUtil.getSecurityUser().getUser().getAccount());
					org.apache.commons.beanutils.BeanUtils.setProperty(entity, "createTime", new Timestamp(new Date().getTime()));
					org.apache.commons.beanutils.BeanUtils.setProperty(entity, "updateBy", SecurityUtil.getSecurityUser().getUser().getAccount());
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
				fsModel.setCreateBy(SecurityUtil.getSecurityUser().getUser().getAccount());
				fsModel.setUpdateTime(new Timestamp(new Date().getTime()));
				fsModel.setUpdateBy(SecurityUtil.getSecurityUser().getUser().getAccount());
				fsModel.setFilesBaseConfig(fbcModel);
				
				// Model >> FilesPermission
				FilesPermission fpModel = new FilesPermission();
				fpModel.setSettingType("1");
				fpModel.setSettingLevel("*");
				fpModel.setSettingValue("*");
				fpModel.setCreateTime(new Timestamp(new Date().getTime()));
				fpModel.setCreateBy(SecurityUtil.getSecurityUser().getUser().getAccount());
				fpModel.setUpdateTime(new Timestamp(new Date().getTime()));
				fpModel.setUpdateBy(SecurityUtil.getSecurityUser().getUser().getAccount());
				
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
		List<Object[]> reList2 = null;
		List<Object> modelList = null;
		List<String> bucketKeys = new ArrayList<String>();
		
		try {
			FilesBaseConfig config = fileDAO.findFilesBaseConfigByConfigName(fileType);
			
			if (config == null) {
				throw new Exception("[ERROR]未設定FilesBaseConfig >> configName: " + fileType);
			}
			
			for (Integer seqNo : seqNos) {
				if (seqNo != null) {
					modelList = new ArrayList<Object>();
					fileDAOVO = new FileDAOVO();
					fileDAOVO.setSeqNo(seqNo);
					
					if (StringUtils.equals(fileType, FILE_TYPE_PUBLIC)) {
						reList = fileDAO.findPublicFileByDAOVO(fileDAOVO, null, null);
						
					} else if (StringUtils.equals(fileType, FILE_TYPE_CUSTOMER)) {
						reList = fileDAO.findCustomerFileByDAOVO(fileDAOVO, null, null);
						
					} else if (StringUtils.equals(fileType, FILE_TYPE_PRODUCT)) {
						fileDAOVO.setAdmin(true);
						reList2 = fileDAO.findProductFileByDAOVO(fileDAOVO, null, null);
						
					} else if (StringUtils.equals(fileType, FILE_TYPE_REFERENCE)) {
						fileDAOVO.setAdmin(true);
						reList2 = fileDAO.findReferenceFileByDAOVO(fileDAOVO, null, null);
					}
					
					FilesSetting fs = null;
					Object tmpModel = null;
					if (reList != null && !reList.isEmpty()) {
						tmpModel = reList.get(0);
						
					} else if (reList2 != null && !reList2.isEmpty()) {
						tmpModel = reList2.get(0)[0];
					}
					
					if (tmpModel != null) {
						bucketKeys.add((String)tmpModel.getClass().getMethod("getUpperFileName").invoke(tmpModel));
						fs = (FilesSetting)tmpModel.getClass().getMethod("getFilesSetting").invoke(tmpModel);
						
						if (fs != null) {
							modelList.addAll(fs.getFilesPermissions());
							modelList.add(tmpModel);
							modelList.add(fs);
							
							fileDAO.deleteFile(modelList);
						}
					}
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
		List<Object[]> model2List = null;
		Object sourceModel;
		FilesSetting fsModel;
		
		try {
			
			fileDAOVO = new FileDAOVO();
			fileDAOVO.setSeqNo(fileServiceVO.getSeqNo());
			
			if (StringUtils.equals(fileServiceVO.getFileType(), FILE_TYPE_PUBLIC)) {
				modelList = fileDAO.findPublicFileByDAOVO(fileDAOVO, null, null);
				
			} else if (StringUtils.equals(fileServiceVO.getFileType(), FILE_TYPE_CUSTOMER)) {
				modelList = fileDAO.findCustomerFileByDAOVO(fileDAOVO, null, null);
				
			}  else if (StringUtils.equals(fileServiceVO.getFileType(), FILE_TYPE_PRODUCT)) {
				fileDAOVO.setAdmin(true);
				model2List = fileDAO.findProductFileByDAOVO(fileDAOVO, null, null);
				
			}  else if (StringUtils.equals(fileServiceVO.getFileType(), FILE_TYPE_REFERENCE)) {
				fileDAOVO.setAdmin(true);
				model2List = fileDAO.findReferenceFileByDAOVO(fileDAOVO, null, null);
				
			} else {
				throw new Exception("[ERROR] fileType not exists! >> " + fileServiceVO.getFileType());
			}
			
			if (((StringUtils.equals(fileServiceVO.getFileType(), FILE_TYPE_PRODUCT) || StringUtils.equals(fileServiceVO.getFileType(), FILE_TYPE_REFERENCE)) && model2List != null && !model2List.isEmpty())
				|| ((!StringUtils.equals(fileServiceVO.getFileType(), FILE_TYPE_PRODUCT) || StringUtils.equals(fileServiceVO.getFileType(), FILE_TYPE_REFERENCE)) && modelList != null && !modelList.isEmpty())) {
				
				if (StringUtils.equals(fileServiceVO.getFileType(), FILE_TYPE_PRODUCT) || StringUtils.equals(fileServiceVO.getFileType(), FILE_TYPE_REFERENCE)) {
					sourceModel = model2List.get(0)[0];
					
				} else {
					sourceModel = modelList.get(0);
				}
				
				org.apache.commons.beanutils.BeanUtils.setProperty(sourceModel, "fileDescription", fileServiceVO.getFileDescription());
				org.apache.commons.beanutils.BeanUtils.setProperty(sourceModel, "updateTime", new Timestamp(new Date().getTime()));
				org.apache.commons.beanutils.BeanUtils.setProperty(sourceModel, "updateBy", SecurityUtil.getSecurityUser().getUser().getAccount());
				
				
				fsModel = (FilesSetting)sourceModel.getClass().getMethod("getFilesSetting").invoke(sourceModel);
				fsModel.setOnTop(fileServiceVO.getOnTopChkbox());
				
				Date beginDate = transDateStr2DateObj(fileServiceVO.getBeginDateStr(), fileServiceVO.getBeginTimeStr());
				Date endDate = transDateStr2DateObj(fileServiceVO.getEndDateStr(), fileServiceVO.getEndTimeStr());
				
				fsModel.setActivationBegin(beginDate == null ? null : new Timestamp(beginDate.getTime()));
				fsModel.setActivationEnd(endDate == null ? null : new Timestamp(endDate.getTime()));
				fsModel.setUpdateTime(new Timestamp(new Date().getTime()));
				fsModel.setUpdateBy(SecurityUtil.getSecurityUser().getUser().getAccount());
				
				fileDAO.modifyFile(Arrays.asList(new Object[]{sourceModel, fsModel}));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public FileServiceVO modifyDownloadCount(String fileType, Integer seqNo) {
		FileDAOVO fileDAOVO;
		String entity = null;
		List<Object> modelList = null;
		List<Object[]> model2List = null;
		FileServiceVO retVO = null;
		
		try {
			fileDAOVO = new FileDAOVO();
			fileDAOVO.setSeqNo(seqNo);
			
			if (StringUtils.equals(fileType, FILE_TYPE_PUBLIC)) {
				entity = "FilesPublic";
				modelList = fileDAO.findPublicFileByDAOVO(fileDAOVO, null, null);
				
			} else if (StringUtils.equals(fileType, FILE_TYPE_CUSTOMER)) {
				entity = "FilesCustomer";
				modelList = fileDAO.findCustomerFileByDAOVO(fileDAOVO, null, null);
				
			} else if (StringUtils.equals(fileType, FILE_TYPE_PRODUCT)) {
				entity = "FilesProduct";
				model2List = fileDAO.findProductFileByDAOVO(fileDAOVO, null, null);
				
			} else if (StringUtils.equals(fileType, FILE_TYPE_REFERENCE)) {
				entity = "FilesReference";
				model2List = fileDAO.findReferenceFileByDAOVO(fileDAOVO, null, null);
			}
			
			if (modelList != null && !modelList.isEmpty()) {
				retVO = transModel2ServiceVO(modelList).get(0);
				
				fileDAO.addDownloadCount(entity, seqNo);
			}
			
			if (model2List != null && !model2List.isEmpty()) {
				if (StringUtils.equals(fileType, FILE_TYPE_PRODUCT)) {
					retVO = transProductModel2ServiceVO(model2List).get(0);
					
				} else if (StringUtils.equals(fileType, FILE_TYPE_REFERENCE)) {
					retVO = transReferenceModel2ServiceVO(model2List).get(0);
				}
				
				fileDAO.addDownloadCount(entity, seqNo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retVO;
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

	@Override
	public List<FileServiceVO> findCustomerFilesByCustId(Integer custId, Integer startRow, Integer pageLength) {
		List<FileServiceVO> reList = null;
		List<Object> fpList;
		FileDAOVO fileDAOVO;
		
		try {
			fileDAOVO = new FileDAOVO();
			fileDAOVO.setCustId(custId);
			fpList = fileDAO.findCustomerFileByDAOVO(fileDAOVO, startRow, pageLength);
			
			if (fpList != null && !fpList.isEmpty()) {
				reList = transModel2ServiceVO(fpList);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return reList;
	}
	
	@Override
	public List<FileServiceVO> findProductFilesByProductId(boolean isAdmin, Integer productId, Integer startRow, Integer pageLength) {
		List<FileServiceVO> reList = null;
		List<Object[]> fpList;
		FileDAOVO fileDAOVO;
		
		try {
			fileDAOVO = new FileDAOVO();
			fileDAOVO.setProductId(productId);
			fileDAOVO.setAdmin(isAdmin);
			fpList = fileDAO.findProductFileByDAOVO(fileDAOVO, startRow, pageLength);
			
			if (fpList != null && !fpList.isEmpty()) {
				reList = transProductModel2ServiceVO(fpList);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return reList;
	}
	
	@Override
	public List<FileServiceVO> findReferenceFilesByReferenceId(boolean isAdmin, Integer referenceId, Integer startRow, Integer pageLength) {
		List<FileServiceVO> reList = null;
		List<Object[]> fpList;
		FileDAOVO fileDAOVO;
		
		try {
			fileDAOVO = new FileDAOVO();
			fileDAOVO.setReferenceId(referenceId);
			fileDAOVO.setAdmin(isAdmin);
			fpList = fileDAO.findReferenceFileByDAOVO(fileDAOVO, startRow, pageLength);
			
			if (fpList != null && !fpList.isEmpty()) {
				reList = transReferenceModel2ServiceVO(fpList);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return reList;
	}

	@Override
	public VisitServiceVO findVisitInfoByDAOVO(VisitServiceVO vo) {
		try {
			FileDAOVO fileDAOVO	 = new FileDAOVO();
			fileDAOVO.setVisitId(vo.getVisitId());
			fileDAOVO.setFileCategory(vo.getFileCategory());
			
			List<FilesVisit> visitList = fileDAO.findVisitFileByDAOVO(fileDAOVO);
			
			if (visitList != null && !visitList.isEmpty()) {
				vo.setFilesVisit(visitList);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vo;
	}

	@Override
	public long countProductFilesByProductId(boolean isAdmin, Integer productId) {
		long count = 0;
		FileDAOVO fileDAOVO;
		
		try {
			fileDAOVO = new FileDAOVO();
			fileDAOVO.setProductId(productId);
			fileDAOVO.setAdmin(isAdmin);
			count = fileDAO.countProductFileByDAOVO(fileDAOVO);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	@Override
	public long countReferenceFilesByReferenceId(boolean isAdmin, Integer referenceId) {
		long count = 0;
		FileDAOVO fileDAOVO;
		
		try {
			fileDAOVO = new FileDAOVO();
			fileDAOVO.setReferenceId(referenceId);
			fileDAOVO.setAdmin(isAdmin);
			count = fileDAO.countReferenceFileByDAOVO(fileDAOVO);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}

	@Override
	public long countCustomerFilesByCustId(Integer custId) {
		long count = 0;
		FileDAOVO fileDAOVO;
		
		try {
			fileDAOVO = new FileDAOVO();
			fileDAOVO.setCustId(custId);
			count = fileDAO.countCustomerFileByDAOVO(fileDAOVO);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}

	@Override
	public long countVisitInfoByDAOVO(VisitServiceVO vo) {
		long count = 0;
		try {
			FileDAOVO fileDAOVO	 = new FileDAOVO();
			fileDAOVO.setVisitId(vo.getVisitId());
			
			count = fileDAO.countVisitFileByDAOVO(fileDAOVO);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}

}
