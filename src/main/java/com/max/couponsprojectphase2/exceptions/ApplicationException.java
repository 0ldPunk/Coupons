package com.max.couponsprojectphase2.exceptions;

import com.max.couponsprojectphase2.enums.ErrorType;

public class ApplicationException extends Exception {

	private static final long serialVersionUID = 1L;
	private ErrorType errorType;

	// use when we are the ones who initiate the exception
	public ApplicationException(ErrorType errorType, String message) {
		super(message);
		this.errorType = errorType;
	}

	public ApplicationException(Exception innerException, ErrorType errorType, String message) {
		super(message, innerException);
		this.errorType = errorType;
	}
	
	public ApplicationException(ErrorType errorType, String message, Exception e) {
		super(message, e);
		this.errorType = errorType;
	}

	public ErrorType getErrorType() {
		return errorType;
	}
}