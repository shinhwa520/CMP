package com.cmp.service.impl;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.RandomGenerator;
import com.cmp.dao.TokenDAO;
import com.cmp.dao.UserDAO;
import com.cmp.model.Token;
import com.cmp.model.User;
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
	
	private long duration = 1000*60*60*24;
	
	public void initUser(String mailAddress, String mailContent) throws Exception {
		User user = userDao.saveUser(new User(mailAddress));
		Token token = tokenDAO.saveToken(new Token(RandomGenerator.getRandom(), "R", user, new Date()));
		sendSimpleMail(mailAddress, mailContent+"?tokenId="+token.getId());
	}
	
	public User verifyToken(String tokenId) throws Exception {
		try {
			Token token = tokenDAO.findTokenById(tokenId);
			if(token.getCreateDateTime().getTime() + duration < new Date().getTime())
				return null;
			return token.getUser();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void saveUserInfo(RegistrationUserVO vo) throws Exception {
		User user = userDao.findUserById(vo.getUserId());
		BeanUtils.copyProperties(vo, user);
		userDao.saveUser(user);
	}
	
	public void sendSimpleMail(String mailAddress, String mailContent) throws MessagingException {

		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper mailMsg = new MimeMessageHelper(mimeMessage);
		mailMsg.setFrom("hector811130@gmail.com");
		mailMsg.setTo(mailAddress);
		mailMsg.setSubject("Test mail");
		mailMsg.setText(mailContent);
		mailSender.send(mimeMessage);
		System.out.println("---Done---");
	}
}
