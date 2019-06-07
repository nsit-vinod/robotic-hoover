package com.yoti.hoover.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.yoti.hoover.model.HooverInput;
import com.yoti.hoover.utils.HooverInputValidationUtils;

@Aspect
@Component
public class InputValidationAroundAspect {

	private final Logger logger = LoggerFactory.getLogger(InputValidationAroundAspect.class);
	
	@Before("execution(public * com.yoti.hoover.service.impl.HooverServiceImpl.moveHooverAndCleanPatches(*))")
	public void validateInputBeforeExecuting(JoinPoint joinPoint) throws Throwable {
		logger.info("===================Input Validatiton started =====================");
		
		HooverInput hooverInput = (HooverInput) joinPoint.getArgs()[0];
		HooverInputValidationUtils.validateHooverInput(hooverInput);
		logger.info("===================Input Validatiton started =====================");
	}
	
}
