package com.sivale.test.jama_test.exception;

/**
 * Exception que salta al querer registrar un mail ya registrado
 * @author george_r2
 *
 */
public class DuplicateEmailException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6300423567100563017L;

	public DuplicateEmailException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DuplicateEmailException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public DuplicateEmailException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DuplicateEmailException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DuplicateEmailException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
}
