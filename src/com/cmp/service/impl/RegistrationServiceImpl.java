package com.cmp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

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
	
	
	public void initUser(String mailAddress, String mailContent) throws Exception {
		User user = userDao.saveUser(new User(mailAddress, roleDAO.findRoleByName("USER"), statusDAO.findStatus("USER", 1)));//登錄帳號
		Token token = tokenDAO.saveToken(new Token(RandomGenerator.getRandom(), "R", user, new Date()));
		sendSimpleMail(mailAddress, mailContent+"?tokenId="+token.getId());
	}
	
	public void sendSimpleMail(String mailAddress, String mailContent) throws MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper mailMsg = new MimeMessageHelper(mimeMessage, true, "utf-8");
		mailMsg.setFrom("cmp.message@aliyun.com");
		mailMsg.setTo(mailAddress);
		mailMsg.setSubject("Test mail");
		mailMsg.setText(mailContent, true);
		mailSender.send(mimeMessage);
		System.out.println("---Done---");
	}
	
	public User verifyToken(String tokenId) throws Exception {
		try {
			Token token = tokenDAO.findTokenById(tokenId);
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
		User channel;
		
		WebApiDetail webApiDetail = webApiDetailDAO.findWebApiDetailByParameterValues(vo.getChannelUrl());
		if(null==webApiDetail)
			channel = userDao.findUserByRoleName("ADMIN").get(0);
		else
			channel = webApiDetail.getUser();
		
		BeanUtils.copyProperties(vo, user);
		user.setStatus(statusDAO.findStatus("USER", 3));//維護個資
		user.setChannel(channel);
		userDao.saveUser(user);
	}
	
	public RegistrationUserVO initQuestList()throws MessagingException {
		RegistrationUserVO vo = new RegistrationUserVO();
		List<QuestionDetail> detailList = questionDetailDAO.listQuestionDetail();
		TreeMap<Question, ArrayList<QuestionDetail>> map = new TreeMap<Question, ArrayList<QuestionDetail>>();
		ArrayList<QuestionDetail> tmp;
		Question q;
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
		vo.setQuesMap(map);
		return vo;
	}
	
	@Override
	public List<User> findUserByAccount(String account) {
	   return userDao.findUserByAccount(account);
	}
	
	public void saveUserQues(String userId, String results) throws Exception {
		System.out.println("userId:"+userId);
		System.out.println("results:"+results);
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
		
		//註冊完成, 綁定webApiDetail
		WebApiDetail webApiDetail = webApiDetailDAO.findWebApiDetailByUserId("");
		webApiDetail.setUser(user);
		webApiDetailDAO.saveWebApiDetail(webApiDetail);
	}
}
