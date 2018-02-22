package com.cmp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.FileDAO;
import com.cmp.model.FilesPublic;
import com.cmp.service.FileService;
import com.cmp.service.vo.FileServiceVO;

@Service("fileService")
@Transactional
public class FileServiceImpl implements FileService {

	FileDAO fileDAO;

	@Override
	public List<FileServiceVO> findAllPublicFiles(boolean isAdmin, Integer startRow, Integer pageLength) {
		List<FileServiceVO> reList = new ArrayList<FileServiceVO>();
		List<FilesPublic> fpList;
		
		try {
			fpList = fileDAO.findAllPublicFile(isAdmin, startRow, pageLength);
			
			if (fpList != null && !fpList.isEmpty()) {
				
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

	@Override
	public List<FileServiceVO> findFilesByFileServieVO(FileServiceVO fileServiceVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFile(FileServiceVO fileServiceVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFile(FileServiceVO fileServiceVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateFile(FileServiceVO fileServiceVO) {
		// TODO Auto-generated method stub
		
	}

	@Autowired
	public void setFileDAO(FileDAO fileDAO) {
		this.fileDAO = fileDAO;
	}
}
