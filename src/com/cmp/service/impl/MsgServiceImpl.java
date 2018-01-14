package com.cmp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.UserDAO;
import com.cmp.dao.WebApiMasterDAO;
import com.cmp.model.WebApiDetail;
import com.cmp.model.WebApiMaster;
import com.cmp.service.MsgService;
import com.cmp.service.vo.MsgServiceVO;

@Service("msgService")
@Transactional
public class MsgServiceImpl implements MsgService {

	private WebApiMasterDAO webApiMasterDAO;
	private UserDAO userDAO;
	
	@Override
	public List<MsgServiceVO> findMakaIds() {
		List<MsgServiceVO> returnList = null;
		List<Object[]> objList = null;
		
		try {
			returnList = new ArrayList<MsgServiceVO>();
			objList = webApiMasterDAO.findWebApiMasterAndDetailByWebName(MsgService.MAKA);
			
			WebApiMaster wamModel;
			WebApiDetail wadModel;
			MsgServiceVO msVO;
			for (Object[] objs : objList) {
				wamModel = (WebApiMaster)objs[0];
				wadModel = (WebApiDetail)objs[1];
				
				msVO = new MsgServiceVO();
				msVO.setWebName(wamModel.getWebName());
				msVO.setMasterSeqNo(wamModel.getSeqNo().toString());
				msVO.setMasterDecription(wamModel.getDescription());
				msVO.setApiMethodType(wamModel.getApiMethodType());
				
				if (StringUtils.equals(msVO.getApiMethodType(), "GET")) {
					String url = wamModel.getApiUrl();
					String[] paraName = StringUtils.split(wamModel.getParameterNames(), "@~");
					String[] paraVal = StringUtils.split(wadModel.getParameterValues(), "@~");
					
					for (int i=0; i<paraName.length; i++) {
						System.out.println("url: "+url+", paraName: "+paraName[i]+", paraVal: "+paraVal[i]);
						url = StringUtils.replace(url, paraName[i], paraVal[i]);
					}
					
					msVO.setApiUrl(url);
					
				} else {
					msVO.setApiUrl(wamModel.getApiUrl());
				}
				
				msVO.setParameterNames(wamModel.getParameterNames());
				msVO.setDataType(wamModel.getDataType());
				msVO.setDataExample(wamModel.getDataExample());
				msVO.setDetailSeqNo(wadModel.getSeqNo().toString());
				msVO.setDetailDescription(wadModel.getDescription());
				msVO.setParameterValues(wadModel.getParameterValues());
				msVO.setApiDetailId(wadModel.getApiDetailId());
				
				returnList.add(msVO);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return returnList;
	}

	@Autowired
	public void setWebApiMasterDAO(WebApiMasterDAO webApiMasterDAO) {
		this.webApiMasterDAO = webApiMasterDAO;
	}

	@Autowired
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
}
