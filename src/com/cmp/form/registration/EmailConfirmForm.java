package com.cmp.form.registration;

import java.io.Serializable;

public class EmailConfirmForm implements Serializable {
	private static final long serialVersionUID = -6819364280867771481L;
	private String mailAddress;
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	
}
