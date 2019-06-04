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
	public void validateInput(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		logger.info("===================Input Validatiton started =====================");
		testInputData(proceedingJoinPoint);
		logger.info("===================Input Validatiton started END=====================");
	}
	private void testInputData(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		
		HooverInput hooverInput = (HooverInput) proceedingJoinPoint.getArgs()[0];
		
		try {
			HooverInputValidationUtils.validateHooverInput(hooverInput);
			proceedingJoinPoint.proceed();
		}
		catch(Throwable ex) {
			throw ex;
		}
		
	}
}
