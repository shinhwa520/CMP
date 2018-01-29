package com.cmp.form.registration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

import com.cmp.model.Question;
import com.cmp.model.QuestionDetail;

public class UserInfoForm implements Serializable {
	
	private static final long serialVersionUID = -2816290246438172322L;
	private String userId;
	private String name;
	private String account;
	private String password;
	private String phone;
	private String channelUrl;
	private TreeMap<Question, ArrayList<QuestionDetail>> quesMap;
	private int quesMapkeySize;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getChannelUrl() {
		return channelUrl;
	}
	public void setChannelUrl(String channelUrl) {
		this.channelUrl = channelUrl;
	}
	public TreeMap<Question, ArrayList<QuestionDetail>> getQuesMap() {
		return quesMap;
	}
	public void setQuesMap(TreeMap<Question, ArrayList<QuestionDetail>> quesMap) {
		this.quesMap = quesMap;
	}
	public int getQuesMapkeySize() {
		if(null!=this.quesMap){
			quesMapkeySize = quesMap.keySet().size();
		}else
			quesMapkeySize = 0;
		return quesMapkeySize;
	}
	public void setQuesMapkeySize(int quesMapkeySize) {
		this.quesMapkeySize = quesMapkeySize;
	}

}
