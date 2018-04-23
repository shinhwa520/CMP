package com.cmp.dao;

import java.util.List;

import com.cmp.dao.vo.FileDAOVO;
import com.cmp.model.FilesBaseConfig;
import com.cmp.model.FilesPermission;
import com.cmp.model.FilesSetting;
import com.cmp.model.FilesVisit;

public interface FileDAO {

	public List<Object> findAllPublicFile(boolean isAdmin, Integer startRow, Integer pageLength); 
	
	public List<Object> findAllCustomerFile(boolean isAdmin, Integer startRow, Integer pageLength); 
	
	public long countPublicFileByDAOVO(FileDAOVO fileDAOVO);
	
	public List<Object> findPublicFileByDAOVO(FileDAOVO fileDAOVO, Integer startRow, Integer pageLength);
	
	public long countCustomerFileByDAOVO(FileDAOVO fileDAOVO);
	
	public List<Object> findCustomerFileByDAOVO(FileDAOVO fileDAOVO, Integer startRow, Integer pageLength);
	
	public long countProductFileByDAOVO(FileDAOVO fileDAOVO);
	
	public List<Object[]> findProductFileByDAOVO(FileDAOVO fileDAOVO, Integer startRow, Integer pageLength);
	
	public long countVisitFileByDAOVO(FileDAOVO fileDAOVO);
	
	public List<FilesVisit> findVisitFileByDAOVO(FileDAOVO fileDAOVO);
	
	public Integer addFile(Object entity, FilesSetting fSetting, List<FilesPermission> fPermissions);
	
	public void modifyFile(List<Object> modelList);
	
	public void deleteFile(List<Object> modelList);
	
	public FilesBaseConfig findFilesBaseConfigByConfigName(String configName);
	
	public Integer addDownloadCount(String entity, Integer seqNo);
}
