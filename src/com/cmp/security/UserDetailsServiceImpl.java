package com.cmp.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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

//		UserBuilder builder = null;
//		if (user != null) {
//			builder = org.springframework.security.core.userdetails.User.withUsername(username);
//			builder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
//			builder.roles(user.getRoles());
//		} else {
//			throw new UsernameNotFoundException("User not found.");
//		}
//		return builder.build();
		
		if(null == user) throw new UsernameNotFoundException("User not found.");
		boolean accountEnabled = true;
		boolean accountNonExpired = true;
	    boolean credentialsNonExpired = true;
	    boolean accountNonLocked = true;
	    
		return new SecurityUser(
				user,
				user.getAccount(),
				new BCryptPasswordEncoder().encode(user.getPassword()),
				accountEnabled,
				accountNonExpired,
				credentialsNonExpired,
				accountNonLocked,
				getAuthorities(user.getRole().getName())
			);
	}
	
	
	private User findUserByAccount(String account) {
//		if (account.equalsIgnoreCase("admin")) {
//			return new User(account, "admin123","ADMIN");
//		}
		
		List<User> list = loginService.findUserByAccount(account);
		if(!list.isEmpty()){
//			User user = list.get(0);
			return list.get(0);
		}
		return null;
	}
	
	public ArrayList<GrantedAuthority> getAuthorities(String... roles) {
		ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(roles.length);
		for (String role : roles) {
			Assert.isTrue(!role.startsWith("ROLE_"), role + " cannot start with ROLE_ (it is automatically added)");
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
		}
		return authorities;
	}
	
}
