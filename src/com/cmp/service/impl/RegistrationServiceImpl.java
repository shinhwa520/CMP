package com.cmp.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.RandomGenerator;
import com.cmp.dao.QuestionDetailDAO;
import com.cmp.dao.RoleDAO;
import com.cmp.dao.StatusDAO;
import com.cmp.dao.TokenDAO;
import com.cmp.dao.UserDAO;
import com.cmp.dao.UserQuesDAO;
import com.cmp.dao.WebApiDetailDAO;
import com.cmp.model.Question;
import com.cmp.model.QuestionDetail;
import com.cmp.model.Token;
import com.cmp.model.User;
import com.cmp.model.UserQues;
import com.cmp.model.WebApiDetail;
import com.cmp.service.RegistrationService;
import com.cmp.service.vo.RegistrationUserVO;

@Service("registrationService")
@Transactional
public class RegistrationServiceImpl implements RegistrationService {
	@Autowired
	private JavaMailSenderImpl mailSender;
	@Autowired
	private UserDAO userDao;
	@Autowired
	private TokenDAO tokenDAO;
	@Autowired
	private QuestionDetailDAO questionDetailDAO;
	@Autowired
	private UserQuesDAO userQuesDAO;
	@Autowired
	private RoleDAO roleDAO;
	@Autowired
	private StatusDAO statusDAO;
	@Autowired
	private WebApiDetailDAO webApiDetailDAO;
	
	
	private long duration = 1000*60*60*24;
	
	
	public boolean checkEmailAvailable(String mailAddress) throws Exception{
		return null==userDao.findUserByEmail(mailAddress);//null =>可使用
	}
	
	
	public User initUser(String mailAddress, String mailContent) throws Exception {
		User user = userDao.saveUser(new User(mailAddress, roleDAO.findRoleByName("USER"), statusDAO.findStatus("USER", 1)));//登錄帳號
		Date current = new Date();
		Token token = tokenDAO.saveToken(new Token(RandomGenerator.getRandom(), "R", user, current));
		String mailbody = "请於页面输入下列验证码。<br>";
		mailbody += "Please enter the following verification code on the page.<br>";
		mailbody += "验证码:"+token.getId().substring(0, 4);
		mailbody += "<br><br><br>";
		mailbody += "温馨提示，请确认您和其他人输入的信息无误，以避免后台管理处理客户登记或签证票务信息失误影响您的权益。<br>";
		mailbody += "Reminder: To ensure our management service provides the correct details to secure your customers and airflight details,  Please ensure the information filled in accurate.<br>";
		mailbody += "祝您有好的一天！<br>";
		mailbody += "Have a nice day!<br><br>";
		mailbody += "CMP 信息服务网联<br>";
		
		String subject = "CMP信息服务网联 帐号申请验证通知";
		sendSimpleMail(mailAddress, subject, mailbody);
		return user;
	}
	
	public void sendSimpleMail(String mailAddress, String subject, String mailContent) throws MessagingException, UnsupportedEncodingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper mailMsg = new MimeMessageHelper(mimeMessage, true, "UTF-8");
		mailMsg.setFrom("cmp.message@aliyun.com");
		mailMsg.setTo(mailAddress);
		mailMsg.setSubject(MimeUtility.encodeText(subject, "UTF-8", "B"));
		mailMsg.setText(mailContent, true);
		mailSender.send(mimeMessage);
	}
	
	public User verifyToken(String userId, String tokenId) throws Exception {
		try {
			Token token = tokenDAO.findTokenByUserAndId(userId, tokenId);
			if(token.getCreateDateTime().getTime() + duration < new Date().getTime())
				return null;
			User user = token.getUser();
			user.setStatus(statusDAO.findStatus("USER", 2));//確認email
			return userDao.saveUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void saveUserInfo(RegistrationUserVO vo) throws Exception {
		User user = userDao.findUserById(vo.getUserId());
//		User channel;
//		
//		WebApiDetail webApiDetail = webApiDetailDAO.findWebApiDetailByParameterValues(vo.getChannelUrl());
//		if(null==webApiDetail)
//			channel = userDao.findUserByRoleName("ADMIN").get(0);
//		else
//			channel = webApiDetail.getUser();
		
		BeanUtils.copyProperties(vo, user);
		user.setStatus(statusDAO.findStatus("USER", 3));//維護個資
//		user.setChannel(channel);
		userDao.saveUser(user);
	}
	
	public RegistrationUserVO initQuestList()throws MessagingException {
		RegistrationUserVO vo = new RegistrationUserVO();
		List<QuestionDetail> detailList = questionDetailDAO.listQuestionDetail();
		TreeMap<Question, ArrayList<QuestionDetail>> map = new TreeMap<Question, ArrayList<QuestionDetail>>();
		ArrayList<QuestionDetail> tmp;
		Question q;
		String ans = "";
		for(QuestionDetail detail : detailList){
			q = detail.getQuestion();
			if(map.containsKey(q)){
				tmp = map.get(q);
				tmp.add(detail);
				map.put(detail.getQuestion(), tmp);
			}else{
				tmp = new ArrayList<QuestionDetail>();
				tmp.add(detail);
				map.put(detail.getQuestion(), tmp);
			}
		}
		for(Question ques : map.keySet()){
			ans += ques.getAns() + ",";
		}
		ans = ans.substring(0, ans.length()-1);
		vo.setQuesMap(map);
		vo.setAns(ans);
		return vo;
	}
	
	public List<User> findUserByAccount(String account) {
	   return userDao.findUserByAccount(account);
	}
	
	public User findUserByUserId(String userId) {
		   return userDao.findUserById(userId);
		}
	
	public void saveUserQues(String userId, String results) throws Exception {
		Date date = new Date();
		User user = userDao.findUserById(userId);
		String[] resultArray = results.split(",");
		for(int i=0; i<resultArray.length; i++){
			QuestionDetail qd = questionDetailDAO.findQuestionDetailById(resultArray[i]);
			UserQues uq = new UserQues(String.valueOf(date.getTime()+qd.getQuestion().getSort()), qd, user, date);
			userQuesDAO.saveUserQues(uq);
		}
		user.setStatus(statusDAO.findStatus("USER", 4));//提交提問
		userDao.saveUser(user);
	}
	
	
	//user 輸入上游 user.account
	public boolean upstream(String userId, String upstreamAccount) throws Exception {
		boolean findUpstream;
		User user = userDao.findUserById(userId);
		user.setStatus(statusDAO.findStatus("USER", 5));//輸入上游
		List<User> upstreamList = findUserByAccount(upstreamAccount);
		if(null!=upstreamList && !upstreamList.isEmpty()){
			user.setChannel(upstreamList.get(0));
			findUpstream = true;
		}else{
			user.setChannel(userDao.findUserByRoleName("MA").get(0));
			findUpstream = false;
		}
		userDao.saveUser(user);
		return findUpstream;
	}
	
	//user 簽署合同
	public void agreement(String userId) throws Exception {
		User user = userDao.findUserById(userId);
		//綁定webApiDetail
		WebApiDetail webApiDetail = webApiDetailDAO.findWebApiDetailByUserId("");
		if(null==webApiDetail){
			user.setStatus(statusDAO.findStatus("USER", 6));//同意條款
		}else{
			user.setStatus(statusDAO.findStatus("USER", 7));//註冊完成
			webApiDetail.setUser(user);
			webApiDetailDAO.saveWebApiDetail(webApiDetail);
		}
		userDao.saveUser(user);
		//
		String mailbody = "恭喜您成功注册！<br>";
		mailbody += "Congratulations you have registered success!<br><br>";
		mailbody += "祝您有好的一天！<br>";
		mailbody += "Have a nice day!<br><br>";
		mailbody += "CMP 信息服务网联<br>";
		
		String subject = "CMP信息服务网联 平台帐号注册成功通知";
		sendSimpleMail(user.getEmail(), subject, mailbody);
	}
}
