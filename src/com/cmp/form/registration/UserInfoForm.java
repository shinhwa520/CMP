package com.cmp.form.registration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

import org.springframework.stereotype.Component;

import com.cmp.model.Question;
import com.cmp.model.QuestionDetail;

@Component("UserInfoForm")
public class UserInfoForm implements Serializable {
	
	private static final long serialVersionUID = -2816290246438172322L;
	private String userId;
	private String name;
	private String account;
	private String password;
	private String phone;
	private String email;
	private String weChat;
	private String channelUrl;
	private String channelAccount;
	private String statusName;
	private TreeMap<Question, ArrayList<QuestionDetail>> quesMap;
	private int quesMapkeySize;
	private int agent_user=0;	//預計仲介user數
	private int agent_cust=0;	//預計仲介cust數
	private int volume=0;		//預計成交量
	private int _agent_user=0;	//實際仲介user數
	private int _agent_cust=0;	//實際仲介cust數
	private int _volume=0;		//實際成交量
	private String verificationCode;
	private Integer reward=0;
	
	private String agreement;
	
	private String ans;
	
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
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public int getAgent_user() {
		return agent_user;
	}
	public void setAgent_user(int agent_user) {
		this.agent_user = agent_user;
	}
	public int getAgent_cust() {
		return agent_cust;
	}
	public void setAgent_cust(int agent_cust) {
		this.agent_cust = agent_cust;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public int get_agent_user() {
		return _agent_user;
	}
	public void set_agent_user(int _agent_user) {
		this._agent_user = _agent_user;
	}
	public int get_agent_cust() {
		return _agent_cust;
	}
	public void set_agent_cust(int _agent_cust) {
		this._agent_cust = _agent_cust;
	}
	public int get_volume() {
		return _volume;
	}
	public void set_volume(int _volume) {
		this._volume = _volume;
	}
	public String getChannelAccount() {
		return channelAccount;
	}
	public void setChannelAccount(String channelAccount) {
		this.channelAccount = channelAccount;
	}
	public String getAns() {
		return ans;
	}
	public void setAns(String ans) {
		this.ans = ans;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWeChat() {
		return weChat;
	}
	public void setWeChat(String weChat) {
		this.weChat = weChat;
	}
	public String getAgreement() {
		return agreement;
	}
	public void setAgreement(String agreement) {
		this.agreement = agreement;
	}
	public String getVerificationCode() {
		return verificationCode;
	}
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	public Integer getReward() {
		return reward;
	}
	public void setReward(Integer reward) {
		this.reward = reward;
	}

}
