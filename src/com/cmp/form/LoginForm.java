package com.cmp.form;

import java.io.Serializable;

public class LoginForm implements Serializable {

	private static final long serialVersionUID = 7866421824889888844L;

	private String account;
	private String password;
	
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
}
