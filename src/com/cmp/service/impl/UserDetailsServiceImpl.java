package com.cmp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.model.User;
import com.cmp.service.LoginService;



@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	LoginService loginService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		/*
		 * Here we are using dummy data, you need to load user data from
		 * database or other third party application
		 */
		User user = findUserByAccount(username);

		UserBuilder builder = null;
		if (user != null) {
			builder = org.springframework.security.core.userdetails.User.withUsername(username);
			builder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
			builder.roles(user.getRoles());
		} else {
			throw new UsernameNotFoundException("User not found.");
		}

		return builder.build();
	}
	
	
	private User findUserByAccount(String account) {
//		if (account.equalsIgnoreCase("admin")) {
//			return new User(account, "admin123","ADMIN");
//		}
		
		List<User> list = loginService.findUserByAccount(account);
		if(!list.isEmpty()){
			User user = list.get(0);
			return new User(user.getAccount(), user.getPassword(), user.getRole().getName());
		}
		return null;
	}
}
