package com.cmp.dao;

import java.util.List;

import com.cmp.dao.vo.FileDAOVO;
import com.cmp.model.FilesBaseConfig;
import com.cmp.model.FilesPermission;
import com.cmp.model.FilesProduct;
import com.cmp.model.FilesSetting;

public interface FileDAO {

	public List<Object> findAllPublicFile(boolean isAdmin, Integer startRow, Integer pageLength); 
	
	public List<Object> findAllCustomerFile(boolean isAdmin, Integer startRow, Integer pageLength); 
	
	public List<Object> findPublicFileByDAOVO(FileDAOVO fileDAOVO);
	
	public List<Object> findCustomerFileByDAOVO(FileDAOVO fileDAOVO, Integer startRow, Integer pageLength);
	
	public List<FilesProduct> findProductFileByDAOVO(FileDAOVO fileDAOVO);
	
	public Integer addFile(Object entity, FilesSetting fSetting, List<FilesPermission> fPermissions);
	
	public void modifyFile(List<Object> modelList);
	
	public void deleteFile(List<Object> modelList);
	
	public FilesBaseConfig findFilesBaseConfigByConfigName(String configName);
	
	public Integer addDownloadCount(String entity, Integer seqNo);
}
