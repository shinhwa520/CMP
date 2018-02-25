package com.cmp.service;

import java.util.List;

import com.cmp.service.vo.FileServiceVO;

public interface FileService {
	
	public static final String FILE_TYPE_PUBLIC = "PUBLIC";
	public static final String FILE_TYPE_CUSTOMER = "CUSTOMER";
	
	public List<FileServiceVO> findAllPublicFiles(boolean isAdmin, Integer startRow, Integer pageLength);
	
	public List<FileServiceVO> findAllCustomerFiles(boolean isAdmin, Integer startRow, Integer pageLength);
	
	public FileServiceVO getFileByFileTypeAndSeqNo(String fileType, Integer seqNo);
	
	public FileServiceVO downloadFileByFileServiceVO(FileServiceVO fileServiceVO);
	
	public void addFile(FileServiceVO fileServiceVO);
	
	public void deleteFile(String fileType, Integer[] seqNos);
	
	public void modifyFile(FileServiceVO fileServiceVO);
}
