package com.cmp.service;

import java.util.List;

import com.cmp.model.FilesBaseConfig;
import com.cmp.service.vo.FileServiceVO;

public interface FileService {
	
	public static final String FILE_TYPE_PUBLIC = "PUBLIC";
	public static final String FILE_TYPE_CUSTOMER = "CUSTOMER";
	
	public List<FileServiceVO> findAllPublicFiles(boolean isAdmin, Integer startRow, Integer pageLength);
	
	public List<FileServiceVO> findAllCustomerFiles(boolean isAdmin, Integer startRow, Integer pageLength);
	
	public List<FileServiceVO> findCustomerFilesByCustId(Integer custId, Integer startRow, Integer pageLength);
	
	public FileServiceVO getFileByFileTypeAndSeqNoOrFileName(String fileType, Integer seqNo, String oriFileName);
	
	public List<FileServiceVO> findFilesByDAOVO(FileServiceVO fileServiceVO);
	
	public FileServiceVO downloadFileByFileServiceVO(FileServiceVO fileServiceVO);
	
	public FileServiceVO addFile(FileServiceVO fileServiceVO);
	
	public void deleteFile(String fileType, Integer[] seqNos, boolean deleteBucketFile);
	
	public void modifyFile(FileServiceVO fileServiceVO);
	
	public FileServiceVO modifyDownloadCount(String fileType, Integer seqNo);
	
	public FilesBaseConfig findFilesConfig(String configName);
}
