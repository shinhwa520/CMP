package com.cmp.service;

import java.util.List;

import com.cmp.service.vo.FileServiceVO;

public interface FileService {

	public List<FileServiceVO> findAllPublicFiles(boolean isAdmin, Integer startRow, Integer pageLength);
	
	public List<FileServiceVO> findAllCustomerFiles(boolean isAdmin, Integer startRow, Integer pageLength);
	
	public List<FileServiceVO> findFilesByFileServieVO(FileServiceVO fileServiceVO);
	
	public void addFile(FileServiceVO fileServiceVO);
	
	public void deleteFile(FileServiceVO fileServiceVO);
	
	public void updateFile(FileServiceVO fileServiceVO);
}
