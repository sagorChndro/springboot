package com.sagor.blog.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import com.sagor.blog.payloadordto.Response;
import com.sagor.blog.utils.ResponseBuilder;

@Aspect
@Configuration
public class DataValidationAspect {
	private static final Logger logger = LogManager.getLogger(DataValidationAspect.class);

	@Around("@annotation(com.sagor.blog.annotations.ValidateData) && args(..)")
	public Response validateData(ProceedingJoinPoint joinPoint) {
		Object[] signatures = joinPoint.getArgs();
		BindingResult result = null;
		for (int i = 0; i < signatures.length; i++) {
			if (signatures[i] instanceof BindingResult) {
				result = (BindingResult) signatures[i];
				break;
			}
		}
		if (result.hasErrors()) {
			return ResponseBuilder.getFailureResponse(result, "Bean binding error");
		}
		try {
			return (Response) joinPoint.proceed();
		} catch (Throwable throwable) {
			logger.error(throwable.getMessage());
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR,
					"Internal server error occurred");
		}
	}

}
