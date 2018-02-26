package com.cmp.dao;

import java.util.List;

import com.cmp.dao.vo.FileDAOVO;
import com.cmp.model.FilesCustomer;
import com.cmp.model.FilesPermission;
import com.cmp.model.FilesPublic;
import com.cmp.model.FilesSetting;

public interface FileDAO {

	public List<Object> findAllPublicFile(boolean isAdmin, Integer startRow, Integer pageLength); 
	
	public List<Object> findAllCustomerFile(boolean isAdmin, Integer startRow, Integer pageLength); 
	
	public List<Object> findPublicFileByDAOVO(FileDAOVO fileDAOVO);
	
	public List<Object> findCustomerFileByDAOVO(FileDAOVO fileDAOVO);
	
	public void addFile(Object entity, FilesSetting fSetting, List<FilesPermission> fPermissions);
	
	public void modifyFile(List<Object> modelList);
	
	public void deleteFile(List<Object> modelList);
}
