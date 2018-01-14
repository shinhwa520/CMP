package com.cmp.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.cmp.service.vo.MsgServiceVO;

public class MsgForm implements Serializable {

	private static final long serialVersionUID = 703316900257078792L;

	private String webName;
	private List<MsgServiceVO> apiList = new ArrayList<MsgServiceVO>();
	
	public String getWebName() {
		return webName;
	}
	public void setWebName(String webName) {
		this.webName = webName;
	}
	public List<MsgServiceVO> getApiList() {
		return apiList;
	}
	public void setApiList(List<MsgServiceVO> apiList) {
		this.apiList = apiList;
	}
}
