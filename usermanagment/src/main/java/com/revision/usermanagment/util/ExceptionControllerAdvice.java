package com.revision.usermanagment.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.revision.usermanagment.exception.UserException;

@RestControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorInfo> meetingSchedulerExceptionHandler(UserException exception){
		ErrorInfo errorInfo=new ErrorInfo();
		errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
		errorInfo.setErrorMessage(exception.getMessage());
		return new ResponseEntity<ErrorInfo>(errorInfo,HttpStatus.BAD_REQUEST);
	}
}
