package com.cmp;

import javax.servlet.http.HttpServletResponse;

public enum Response {
	REGISTRATION_SUCCESS(HttpServletResponse.SC_OK, "registration success!! redirect to login page..."),
	CREATE_SUCCESS(HttpServletResponse.SC_OK, "新增資料成功"),
	UPDATE_SUCCESS(HttpServletResponse.SC_OK, "維護資料成功"),
	PARAMETER_ERROR(HttpServletResponse.SC_BAD_REQUEST, "參數錯誤");
	
    private final int status;
    private final String message;
    private final String key;
    
    
    Response(int status, String message) {
        this(status, message, "data");
    }
	Response(int status, String message, String key) {
		this.status = status;
		this.message = message;
		this.key = key;
	}
	public int getStatus() {
		return status;
	}
	public String getMessage() {
		return message;
	}
	public String getKey() {
		return key;
	}
}
