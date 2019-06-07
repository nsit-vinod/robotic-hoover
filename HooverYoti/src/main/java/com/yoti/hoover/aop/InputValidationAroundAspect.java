package com.yoti.hoover.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.yoti.hoover.model.HooverInput;
import com.yoti.hoover.utils.HooverInputValidationUtils;

@Aspect
@Component
public class InputValidationAroundAspect {

	private final Logger logger = LoggerFactory.getLogger(InputValidationAroundAspect.class);
	@Around("execution(public * com.yoti.hoover.service.impl.HooverServiceImpl.moveHooverAndCleanPatches(*))")
	public Object validateInput(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		logger.info("===================Input Validatiton started =====================");
		return testInputData(proceedingJoinPoint);
		
	}
	private Object testInputData(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		
		HooverInput hooverInput = (HooverInput) proceedingJoinPoint.getArgs()[0];
		
		try {
			HooverInputValidationUtils.validateHooverInput(hooverInput);
			logger.info("===================Input Validatiton END=====================");
			return proceedingJoinPoint.proceed();
		}
		catch(Throwable ex) {
			throw ex;
		}
		
	}
}
