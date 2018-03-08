package com.cmp.dao;

import java.util.List;

import com.cmp.model.ProductInfo;

public interface ProductInfoDAO {
	public List<ProductInfo> findProductInfoAll();
	public ProductInfo saveCommission(ProductInfo productInfo);
	public ProductInfo findCommissionById(Integer productId);
}
