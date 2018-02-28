package com.cmp.security;

import java.util.Collection;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.cmp.model.User;

@Component
@Scope("session")
public class SecurityUser  extends org.springframework.security.core.userdetails.User {
	private static final long serialVersionUID = 1L;

	private User user;

	public SecurityUser(
			User user,
			String username, 
			String password, 
			boolean enabled, 
			boolean accountNonExpired,
			boolean credentialsNonExpired, 
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.user = user;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}
