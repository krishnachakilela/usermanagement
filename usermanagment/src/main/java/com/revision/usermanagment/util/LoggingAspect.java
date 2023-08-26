package com.revision.usermanagment.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.revision.usermanagment.exception.UserException;

@Component
@Aspect
public class LoggingAspect {

	private static final Log LOGGER = LogFactory.getLog(LoggingAspect.class);
	
	@AfterThrowing(pointcut = "execution(* com.revision.usermanagment.service.*Impl.*(..))",throwing="exception")
	public void logServiceException(UserException exception) {
		log(exception);
	}

	private void log(UserException exception) {

		if (exception.getMessage() != null) {
			LOGGER.error(exception.getMessage());
		} else {
			LOGGER.error(exception.getMessage(), exception);
		}
	}

}
