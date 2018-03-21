package com.cmp.service.vo;

import java.util.ArrayList;
import java.util.TreeMap;

import com.cmp.model.Question;
import com.cmp.model.QuestionDetail;
import com.cmp.model.User;

public class RegistrationUserVO {
	

	private String userId;
	private String name;
	private String account;
	private String password;
	private String phone;
	private String channelUrl;
	private TreeMap<Question, ArrayList<QuestionDetail>> quesMap;
	private String ans;
	private String weChat;
	private User channel;


	public RegistrationUserVO() {
	}
	
	public RegistrationUserVO(String userId, String name, String account, String password, String phone, String weChat, User channel) {
		super();
		this.userId = userId;
		this.name = name;
		this.account = account;
		this.password = password;
		this.phone = phone;
		this.weChat = weChat;
		this.channel = channel;
	}

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

	public String getAns() {
		return ans;
	}

	public void setAns(String ans) {
		this.ans = ans;
	}

	public String getWeChat() {
		return weChat;
	}

	public void setWeChat(String weChat) {
		this.weChat = weChat;
	}

	public User getChannel() {
		return channel;
	}

	public void setChannel(User channel) {
		this.channel = channel;
	}
	

}
