package com.cmp.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.ProductInfoDAO;
import com.cmp.model.ProductInfo;

@Repository
@Transactional
public class ProductInfoDAOImpl extends BaseDaoHibernate implements ProductInfoDAO {
	
	@Override
	public ProductInfo saveCommission(ProductInfo productInfo) {
		return (ProductInfo) getHibernateTemplate().merge(productInfo);
	}
	
	@Override
	public ProductInfo findCommissionById(Integer productId) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from ProductInfo t ")
		  .append(" where 1=1 ")
		  .append(" and t.productId = ? ");
		List<ProductInfo> returnList = (List<ProductInfo>)getHibernateTemplate().find(sb.toString(), productId);
		return returnList.isEmpty() ? null : returnList.get(0);
	}
	
	public List<ProductInfo> findProductInfoAll() {
		StringBuffer sb = new StringBuffer();
		sb.append(" select p ")
		  .append(" from ProductInfo p ")
		  .append(" where 1 = 1 ");
		List<ProductInfo> returnList = (List<ProductInfo>)getHibernateTemplate().find(sb.toString());
		return returnList;
	}

}
