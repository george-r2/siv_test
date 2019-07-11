package com.sivale.test.jama_test.beans;

import java.io.Serializable;

public class TError implements Serializable{

	private static final long serialVersionUID = 2634544780364498282L;
	private String code;
	private String message;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
