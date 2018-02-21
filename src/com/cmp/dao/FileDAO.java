package com.cmp.dao;

import java.util.List;

import com.cmp.dao.vo.FileDAOVO;
import com.cmp.model.FilesCustomer;
import com.cmp.model.FilesPermission;
import com.cmp.model.FilesPublic;
import com.cmp.model.FilesSetting;

public interface FileDAO {

	public List<FilesPublic> findAllPublicFile(boolean isAdmin, Integer startRow, Integer pageLength); 
	
	public List<FilesPublic> findAllCustomerFile(boolean isAdmin, Integer startRow, Integer pageLength); 
	
	public List<FilesPublic> findPublicFileByDAOVO(FileDAOVO fileDAOVO);
	
	public List<FilesCustomer> findCustomerFileByDAOVO(FileDAOVO fileDAOVO);
	
	public void addPublicFile(FilesPublic fPublic, FilesSetting fSetting, FilesPermission fPermission);
	
	public void addCustomerFile(FilesCustomer fCustomer, FilesSetting fSetting, FilesPermission fPermission);
	
	public void deletePublicFile(FilesPublic fPublic, FilesSetting fSetting, FilesPermission fPermission);
	
	public void deleteCustomerFile(FilesCustomer fCustomer, FilesSetting fSetting, FilesPermission fPermission);
}
