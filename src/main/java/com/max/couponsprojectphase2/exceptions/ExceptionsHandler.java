package com.max.couponsprojectphase2.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.max.couponsprojectphase2.entities.ErrorBean;
import com.max.couponsprojectphase2.enums.ErrorType;

// Exception handler class
@RestControllerAdvice
public class ExceptionsHandler {

	// Response - Object in Spring
	@ExceptionHandler
	@ResponseBody
	// Variable name is throwable in order to remember that it handles Exception and
	// Error
	public ErrorBean toResponse(Throwable throwable) {

		// ErrorBean errorBean;
		if (throwable instanceof ApplicationException) {
			ApplicationException appException = (ApplicationException) throwable;

			ErrorType errorType = appException.getErrorType();
			int errorNumber = errorType.getErrorNumber();
			String errorMessage = errorType.getErrorMessage();
			String errorName = errorType.getErrorName();

			ErrorBean errorBean = new ErrorBean(errorNumber, errorMessage, errorName);
			if (appException.getErrorType().isShowStackTrace()) {
				appException.printStackTrace();
			}

			return errorBean;
		}

		String errorMessage = throwable.getMessage();
		ErrorBean errorBean = new ErrorBean(601, errorMessage, "General error");
		throwable.printStackTrace();

		return errorBean;
	}

}