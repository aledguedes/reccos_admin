package com.reccos.admin.exceptions;

public class EmailNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

//	private String msg;

	public EmailNotFoundException(String msg) {
		super(msg);
	}
}
