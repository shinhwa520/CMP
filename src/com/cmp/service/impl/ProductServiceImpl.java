package com.cmp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.FileDAO;
import com.cmp.dao.vo.FileDAOVO;
import com.cmp.model.FilesProduct;
import com.cmp.service.ProductService;
import com.cmp.service.vo.ProductServiceVO;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	FileDAO fileDAO;

	@Override
	public ProductServiceVO findProductInfoByDAOVO(ProductServiceVO vo) {
		
		try {
			FileDAOVO fileDAOVO	 = new FileDAOVO();
			fileDAOVO.setProductId(vo.getProductId());
			
			List<FilesProduct> productList = fileDAO.findProductFileByDAOVO(fileDAOVO);
			
			if (productList != null && !productList.isEmpty()) {
				vo.setFilesProduct(productList);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vo;
	}

}
