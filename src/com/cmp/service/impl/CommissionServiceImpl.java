package com.cmp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.CommissionDAO;
import com.cmp.dao.ProductInfoDAO;
import com.cmp.dao.UserDAO;
import com.cmp.model.Commission;
import com.cmp.model.ProductInfo;
import com.cmp.model.User;
import com.cmp.security.SecurityUtil;
import com.cmp.service.CommissionService;

@Service("CommissionService")
@Transactional
public class CommissionServiceImpl implements CommissionService {
	@Autowired
	private CommissionDAO commissionDAO;
	@Autowired
	private ProductInfoDAO productInfoDAO;
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public List<Commission> initCommissionList(String userId){
		User editor = SecurityUtil.getSecurityUser().getUser();
		User user = userDAO.findUserById(userId);
		List<ProductInfo> productInfoList = productInfoDAO.findProductInfoAll();
		List<Commission> commissionList = commissionDAO.findCommissionByUserId(userId);
		Set<ProductInfo> productSet = new HashSet<ProductInfo>();
		for(Commission c : commissionList){	//上游已對User 設定該Product 之Commission
			productSet.add(c.getProductInfo());
		}
		for(ProductInfo p : productInfoList){	
			if(!productSet.contains(p)){	//上游尚未對User 設定該Product 之Commission
				Commission commission = commissionDAO.saveCommission(new Commission(user, p, editor.getReward(), editor.getAccount()));
				commissionList.add(commission);
			}
		}
		
		return commissionList;
	}
	
	@Override
	public void updateCommission(String ori, String results){
		User user = SecurityUtil.getSecurityUser().getUser();
		String[] oriArr = ori.split(",");
		String[] resArr = results.split(",");
		String[] tmp;
		for(int i=0; i<resArr.length; i++){
			if(!StringUtils.equals(oriArr[i], resArr[i])){
				tmp = resArr[i].split("-");
				Commission commission = commissionDAO.findCommissionById(tmp[0]);
				commission.setCommissionPercent(Integer.valueOf(tmp[1]));
				commission.setUpdateBy(user.getName());
				commission.setUpdateTime(new Date());
				commissionDAO.saveCommission(commission);
			}
		}
	}
}
