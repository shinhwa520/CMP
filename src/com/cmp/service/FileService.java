package com.cmp.service;

import java.util.List;

import com.cmp.model.FilesBaseConfig;
import com.cmp.service.vo.FileServiceVO;
import com.cmp.service.vo.VisitServiceVO;

public interface FileService {
	
	public static final String FILE_TYPE_PUBLIC = "PUBLIC";
	public static final String FILE_TYPE_CUSTOMER = "CUSTOMER";
	public static final String FILE_TYPE_PRODUCT = "PRODUCT";
	public static final String FILE_TYPE_VISIT = "VISIT";
	
	public static final String FILE_CATEGORY_JPG = "JPG";
	public static final String FILE_CATEGORY_PDF = "PDF";
	
	public List<FileServiceVO> findAllPublicFiles(boolean isAdmin, Integer startRow, Integer pageLength);
	
	public List<FileServiceVO> findAllCustomerFiles(boolean isAdmin, Integer startRow, Integer pageLength);
	
	public long countProductFilesByProductId(Integer productId);
	
	public List<FileServiceVO> findProductFilesByProductId(Integer productId, Integer startRow, Integer pageLength);
	
	public long countCustomerFilesByCustId(Integer custId);
	
	public List<FileServiceVO> findCustomerFilesByCustId(Integer custId, Integer startRow, Integer pageLength);
	
	public FileServiceVO getFileByFileTypeAndSeqNoOrFileName(String fileType, Integer seqNo, String oriFileName);
	
	public List<FileServiceVO> findFilesByDAOVO(FileServiceVO fileServiceVO);
	
	public FileServiceVO downloadFileByFileServiceVO(FileServiceVO fileServiceVO);
	
	public FileServiceVO addFile(FileServiceVO fileServiceVO);
	
	public void deleteFile(String fileType, Integer[] seqNos, boolean deleteBucketFile);
	
	public void modifyFile(FileServiceVO fileServiceVO);
	
	public FileServiceVO modifyDownloadCount(String fileType, Integer seqNo);
	
	public FilesBaseConfig findFilesConfig(String configName);
	
	//public ProductServiceVO findProductInfoByDAOVO(ProductServiceVO vo);
	
	public long countVisitInfoByDAOVO(VisitServiceVO vo);
	
	public VisitServiceVO findVisitInfoByDAOVO(VisitServiceVO vo);
}
